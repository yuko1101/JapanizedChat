package io.github.yuko1101.japanizedchat.forge;

import io.github.yuko1101.japanizedchat.JapanizedChat;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JapanizedChat.MOD_ID)
public class JapanizedChatForge {
    public JapanizedChatForge() {
        JapanizedChat.init();
    }
}