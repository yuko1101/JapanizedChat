package io.github.yuko1101.japanizedchat.fabric;

import io.github.yuko1101.japanizedchat.JapanizedChat;
import net.fabricmc.api.ModInitializer;

public class JapanizedChatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        JapanizedChat.init();
    }
}