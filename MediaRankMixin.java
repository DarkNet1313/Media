package net.mediarank.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MediaRankMixin {

    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true)
    private void mediarank$addRank(CallbackInfoReturnable<Component> cir) {
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.player == null) return;
        // Uniquement TON joueur, et uniquement chez toi (client)
        if ((Object) this != client.player) return;

        MutableComponent prefix = Component.literal("[MEDIA] ")
                .withStyle(ChatFormatting.RED, ChatFormatting.BOLD);
        cir.setReturnValue(prefix.append(cir.getReturnValue()));
    }
}
