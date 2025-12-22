package io.github.yuko1101.japanizedchat;

import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;

import java.util.HashMap;

public class JapanizedChat {
	public static final String MOD_ID = "japanizedchat";

	public static boolean japanizedInput = true;
	public static boolean japanizedInputWithOriginal = true;

	public static HashMap<ChatHudLine, Text> replaceQueue = new HashMap<>();

	public static void init() {
		
	}
}
