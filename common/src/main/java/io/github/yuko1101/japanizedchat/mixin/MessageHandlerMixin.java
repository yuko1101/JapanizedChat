package io.github.yuko1101.japanizedchat.mixin;

import io.github.yuko1101.japanizedchat.util.Japanizer;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MessageHandler.class)
public class MessageHandlerMixin {
    @Redirect(method = "processChatMessageInternal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"))
    private void modifyChatMessage(ChatHud chatHud, Text message, MessageSignatureData signatureData, MessageIndicator indicator) {
        new Thread(new Japanizer(chatHud, message, signatureData, indicator)).start();
    }
}
