package io.github.yuko1101.japanizedchat.neoforge;

import io.github.yuko1101.japanizedchat.JapanizedChat;
import net.neoforged.fml.common.Mod;

@Mod(JapanizedChat.MOD_ID)
public final class JapanizedChatNeoForge {
    public JapanizedChatNeoForge() {
        JapanizedChat.init();
    }
}
