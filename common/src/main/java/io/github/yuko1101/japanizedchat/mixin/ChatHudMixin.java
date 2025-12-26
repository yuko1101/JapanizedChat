package io.github.yuko1101.japanizedchat.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.yuko1101.japanizedchat.JapanizedChat;
import io.github.yuko1101.japanizedchat.util.Japanizer;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {

    @Shadow @Final private List<ChatHudLine> messages;

    @Shadow protected abstract void refresh();

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", at = @At("TAIL"))
    private void japanizeMessage(Text message, MessageSignatureData signatureData, MessageIndicator indicator, CallbackInfo ci, @Local ChatHudLine chatHudLine) {
        Matcher matched = null;
        for (var pattern : JapanizedChat.messagePatterns) {
            matched = pattern.matcher(message.getString());
            if (matched.matches()) break;
        }
        if (matched == null || !matched.matches()) return;
        var content = matched.group("content");
        if (Japanizer.hasFullWidth(content)) return;

        CompletableFuture.runAsync(() -> {
            var japanized = Japanizer.convert(content);
            if (japanized == null) return;

            var text = message.copy();
            text.append(Text.literal(" (" + japanized + ")").setStyle(message.getStyle().withColor(Formatting.GOLD)));
            JapanizedChat.replaceQueue.put(chatHudLine, text);
        });
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(CallbackInfo ci) {
        if (JapanizedChat.replaceQueue.isEmpty()) return;
        var shouldRefresh = false;
        var entrySet = JapanizedChat.replaceQueue.entrySet().stream().toList();
        for (var entry : entrySet) {
            var chatHudLine = entry.getKey();
            var text = entry.getValue();
            if (messages.contains(chatHudLine)) {
                this.messages.set(this.messages.indexOf(chatHudLine), new ChatHudLine(chatHudLine.creationTick(), text, chatHudLine.signature(), chatHudLine.indicator()));
                shouldRefresh = true;
            }
            JapanizedChat.replaceQueue.remove(chatHudLine);
        }

        if (shouldRefresh) this.refresh();
    }
}
