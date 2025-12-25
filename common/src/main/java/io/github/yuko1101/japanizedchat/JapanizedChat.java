package io.github.yuko1101.japanizedchat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.yuko1101.japanizedchat.util.ConfigFile;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class JapanizedChat {
	public static final String MOD_ID = "japanizedchat";

	public static final ConfigFile STATE_CONFIG = new ConfigFile("state.json");
	private static JsonObject state = new JsonObject();

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

		STATE_CONFIG.ensureExists();
		try (var reader = new FileInputStream(STATE_CONFIG.file)) {
			state = JsonParser.parseReader(new InputStreamReader(reader, StandardCharsets.UTF_8)).getAsJsonObject();
		} catch (RuntimeException | IOException e) {
			throw new RuntimeException("Failed to load state config", e);
		}
	}

	public static void toggleJapanizeInput() {
		state.addProperty("japanizedInput", !isJapanizedInputEnabled());
		saveStateConfig();
	}

	public static boolean isJapanizedInputEnabled() {
		return state.has("japanizedInput") && state.get("japanizedInput").getAsBoolean();
	}

	public static boolean isJapanizedInputWithOriginalEnabled() {
		return state.has("japanizedInputWithOriginal") && state.get("japanizedInputWithOriginal").getAsBoolean();
	}

	public static void saveStateConfig() {
		try {
			Files.writeString(STATE_CONFIG.file.toPath(), state.toString(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Failed to save state config", e);
		}
	}
}
