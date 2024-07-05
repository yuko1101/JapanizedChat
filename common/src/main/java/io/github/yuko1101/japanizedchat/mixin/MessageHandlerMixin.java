package io.github.yuko1101.japanizedchat.mixin;

import io.github.yuko1101.japanizedchat.util.Japanizer;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

@Mixin(MessageHandler.class)
public class MessageHandlerMixin {

    @Unique
    private static final String MESSAGE_REGEX = "^<.+> (.+?)$";

    @Redirect(method = "processChatMessageInternal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"))
    private void modifyChatMessage(ChatHud chatHud, Text originalText, MessageSignatureData signatureData, MessageIndicator indicator) {
        var matched = Pattern.compile(MESSAGE_REGEX).matcher(originalText.getString());
        if (!matched.matches()) {
            chatHud.addMessage(originalText, signatureData, indicator);
            return;
        }
        var message = matched.group(1);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> Japanizer.convert(message));
        future.thenAccept(japanizedMessage -> {
            if (japanizedMessage == null) {
                chatHud.addMessage(originalText, signatureData, indicator);
                return;
            }

            var text = Text.empty();
            text.append(originalText);
            text.append(Text.literal(" (" + japanizedMessage + ")").setStyle(originalText.getStyle().withColor(Formatting.GOLD)));

            chatHud.addMessage(text, signatureData, indicator);
        });
    }
}
