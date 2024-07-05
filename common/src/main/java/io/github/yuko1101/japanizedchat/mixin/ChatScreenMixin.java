package io.github.yuko1101.japanizedchat.mixin;

import io.github.yuko1101.japanizedchat.JapanizedChat;
import io.github.yuko1101.japanizedchat.util.Japanizer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.concurrent.CompletableFuture;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Redirect(method = "sendMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendChatMessage(Ljava/lang/String;)V"))
    private void modifyChatInput(ClientPlayNetworkHandler handler, String content) {
        if (!JapanizedChat.japanizedInput) {
            handler.sendChatMessage(content);
            return;
        }

        CompletableFuture.runAsync(() -> {
            var japanized = Japanizer.convert(content);
            handler.sendChatMessage(japanized == null ? content : japanized);
        });
    }
}
