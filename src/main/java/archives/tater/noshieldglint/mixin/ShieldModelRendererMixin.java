package archives.tater.noshieldglint.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.model.special.ShieldModelRenderer;
import net.minecraft.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ShieldModelRenderer.class)
public class ShieldModelRendererMixin {
    @ModifyVariable(
            method = "render(Lnet/minecraft/component/ComponentMap;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;IIZI)V",
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