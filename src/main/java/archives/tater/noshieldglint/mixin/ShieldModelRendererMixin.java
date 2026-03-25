package archives.tater.noshieldglint.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.world.item.ItemDisplayContext;

@Mixin(ShieldSpecialRenderer.class)
public class ShieldModelRendererMixin {
    @ModifyVariable(
            method = "submit(Lnet/minecraft/core/component/DataComponentMap;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IIZI)V",
            at = @At("HEAD"),
            argsOnly = true
    )
    private boolean disableGlint(boolean original, @Local(argsOnly = true) ItemDisplayContext mode) {
        return switch (mode) {
            case NONE, GUI, GROUND, FIXED, ON_SHELF -> original;
            default -> false;
        };
    }
}