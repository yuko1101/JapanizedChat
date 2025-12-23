package io.github.yuko1101.japanizedchat;

import io.github.yuko1101.japanizedchat.util.ConfigFile;
import io.github.yuko1101.japanizedchat.util.Japanizer;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class JapanizedChat {
	public static final String MOD_ID = "japanizedchat";

	public static boolean japanizedInput = true;
	public static boolean japanizedInputWithOriginal = true;

	public static final ConfigFile MESSAGE_REGEX_CONFIG = new ConfigFile("message_regex.txt");
	public static List<Pattern> messagePatterns = new ArrayList<>();
	public static HashMap<ChatHudLine, Text> replaceQueue = new HashMap<>();

	public static void init() {
		MESSAGE_REGEX_CONFIG.ensureExists();
		try (var reader = new FileInputStream(MESSAGE_REGEX_CONFIG.file)) {
            var lineStream = new BufferedReader(new InputStreamReader(reader, StandardCharsets.UTF_8)).lines();
            lineStream.forEach(line -> messagePatterns.add(Pattern.compile(line)));
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("Failed to load message regex config", e);
        }
	}
}
