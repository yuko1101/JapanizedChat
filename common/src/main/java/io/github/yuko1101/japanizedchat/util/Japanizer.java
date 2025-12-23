package io.github.yuko1101.japanizedchat.util;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;

public class Japanizer {

    private static final ConfigFile ROMANTABLE = new ConfigFile("romantable.txt");
    private static final HashMap<String, KanaResult> ROMAJI_TO_KANA = new HashMap<>();
    private static final HashSet<Character> N_CONNECTABLE = new HashSet<>();

    record KanaResult(String output, String nextInput) { }

    static {
        ROMANTABLE.ensureExists();
        try (var reader = new FileInputStream(ROMANTABLE.file)) {
            var lineStream = new BufferedReader(new InputStreamReader(reader, StandardCharsets.UTF_8)).lines();
            lineStream.forEach(line -> {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String romaji = parts[0];
                    String kana = parts[1];
                    ROMAJI_TO_KANA.put(romaji, new KanaResult(kana, ""));
                } else if (parts.length == 3) {
                    String romaji = parts[0];
                    String kana = parts[1];
                    String nextInput = parts[2];
                    ROMAJI_TO_KANA.put(romaji, new KanaResult(kana, nextInput));
                } else {
                    System.err.println("Invalid line in romaji table: " + line);
                }
            });
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("Failed to load romaji table", e);
        }

        for (var key : ROMAJI_TO_KANA.keySet()) {
            if (key.startsWith("n") && key.length() > 1) {
                N_CONNECTABLE.add(key.charAt(1));
            }
        }
    }

    @Nullable
    public static String convert(String msg) {
        return conv(romaToKana(msg));
    }

    public static boolean hasFullWidth(String msg) {
        return Arrays.stream(msg.split("")).anyMatch(c -> c.getBytes().length > 1);
    }

    public static String romaToKana(String romaji) {
        StringBuilder result = new StringBuilder();
        StringBuilder buffer = new StringBuilder();

        var romajiPatterns = ROMAJI_TO_KANA.keySet();

        Consumer<String> endFormatter = (str) -> {
            if (romajiPatterns.contains(str)) {
                KanaResult kanaResult = ROMAJI_TO_KANA.get(str);
                result.append(kanaResult.output());
                result.append(kanaResult.nextInput());
            } else {
                result.append(str);
            }
        };

        for (char c : romaji.toCharArray()) {
            buffer.append(c);
            String bufferString = buffer.toString();

            if (bufferString.equals("n")) {
                continue; // wait for next char
            }

            if (romajiPatterns.contains(bufferString)) {
                KanaResult kanaResult = ROMAJI_TO_KANA.get(bufferString);
                result.append(kanaResult.output());
                buffer.setLength(0); // Clear the buffer
                buffer.append(kanaResult.nextInput());
                continue;
            }

            if (bufferString.length() == 2 && bufferString.startsWith("n") && !N_CONNECTABLE.contains(bufferString.charAt(1))) {
                KanaResult kanaResult = ROMAJI_TO_KANA.get("" + bufferString.charAt(0));
                if (kanaResult != null) {
                    result.append(kanaResult.output());
                    buffer.deleteCharAt(0);
                    buffer.insert(0, kanaResult.nextInput());
                    continue;
                }
            }

            while (romajiPatterns.stream()
                    .filter(pattern -> pattern.startsWith(buffer.toString()))
                    .findFirst().isEmpty()) {
                result.append(buffer.charAt(0));
                buffer.deleteCharAt(0);
            }
        }

        endFormatter.accept(buffer.toString());

        return result.toString();
    }

    @Nullable
    private static String conv(String text) {
        if (!text.isEmpty()) {
            HttpURLConnection urlconn = null;
            BufferedReader reader = null;

            try {
                String baseurl = "https://www.google.com/transliterate?langpair=ja-Hira%7Cja&text=" + URLEncoder.encode(text, StandardCharsets.UTF_8);

                URL url = URI.create(baseurl).toURL();
                urlconn = (HttpURLConnection) url.openConnection();
                urlconn.setRequestMethod("GET");
                urlconn.setInstanceFollowRedirects(false);
                urlconn.connect();
                reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), StandardCharsets.UTF_8));
                String line;
                StringBuilder result = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    result.append(parseIMEResponse(line));
                }

                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlconn != null) {
                    urlconn.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ignored) {
                    }
                }

            }

        }
        return null;
    }

    private static String parseIMEResponse(String result) {
        StringBuilder buf = new StringBuilder();
        int level = 0;
        int index = 0;

        while(index < result.length()) {
            int nextStart;
            int nextEnd;
            if (level >= 3) {
                nextStart = result.indexOf("\"", index);
                nextEnd = result.indexOf("\"", nextStart + 1);
                if (nextStart == -1 || nextEnd == -1) {
                    return buf.toString();
                }

                buf.append(result, nextStart + 1, nextEnd);
                int next = result.indexOf("]", nextEnd);
                if (next == -1) {
                    return buf.toString();
                }

                --level;
                index = next + 1;
            } else {
                nextStart = result.indexOf("[", index);
                nextEnd = result.indexOf("]", index);
                if (nextStart == -1) {
                    return buf.toString();
                }

                if (nextStart < nextEnd) {
                    ++level;
                    index = nextStart + 1;
                } else {
                    --level;
                    index = nextEnd + 1;
                }
            }
        }

        return buf.toString();
    }
}