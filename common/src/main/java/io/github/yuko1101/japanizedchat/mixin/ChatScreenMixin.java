package io.github.yuko1101.japanizedchat.mixin;

import io.github.yuko1101.japanizedchat.JapanizedChat;
import io.github.yuko1101.japanizedchat.util.Japanizer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Unique
    private static final ButtonWidget JAPANIZE_ENABLED_BUTTON = getJapanizeButton(Text.literal("日本語化: ").append(Text.literal("ON").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
    @Unique
    private static final ButtonWidget JAPANIZE_DISABLED_BUTTON = getJapanizeButton(Text.literal("日本語化: ").append(Text.literal("OFF").setStyle(Style.EMPTY.withColor(Formatting.RED))));

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        var button = JapanizedChat.japanizedInput ? JAPANIZE_ENABLED_BUTTON : JAPANIZE_DISABLED_BUTTON;
        button.setPosition(context.getScaledWindowWidth() - button.getWidth() - 10, context.getScaledWindowHeight() - button.getHeight() - 20);
        button.render(context, mouseX, mouseY, delta);
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        var buttonWidget = JapanizedChat.japanizedInput ? JAPANIZE_ENABLED_BUTTON : JAPANIZE_DISABLED_BUTTON;
        if (buttonWidget.mouseClicked(mouseX, mouseY, button)) {
            cir.setReturnValue(true);
        }
    }

    @Unique
    private static ButtonWidget getJapanizeButton(Text text) {
        return ButtonWidget.builder(text, button -> toggleJapanize()).size(80, 20).build();
    }

    @Unique
    private static void toggleJapanize() {
        JapanizedChat.japanizedInput = !JapanizedChat.japanizedInput;
    }
}
