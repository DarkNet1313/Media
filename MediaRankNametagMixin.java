package net.mediarank.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public abstract class MediaRankNametagMixin {

    @Inject(method = "shouldShowName", at = @At("RETURN"), cancellable = true)
    private void mediarank$showOwnName(LivingEntity entity, double distance, CallbackInfoReturnable<Boolean> cir) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || entity != client.player) return;
        if (client.options.getCameraType().isFirstPerson() || client.options.hideGui) return;
        cir.setReturnValue(true);
    }
}
