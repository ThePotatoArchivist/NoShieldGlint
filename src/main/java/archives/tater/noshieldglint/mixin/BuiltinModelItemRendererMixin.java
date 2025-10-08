package archives.tater.noshieldglint.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(BuiltinModelItemRenderer.class)
public class BuiltinModelItemRendererMixin {
    @ModifyExpressionValue(
            method = "render",
            slice = @Slice(
                    from = @At(value = "FIELD", target = "Lnet/minecraft/item/Items;SHIELD:Lnet/minecraft/item/Item;"),
                    to = @At(value = "FIELD", target = "Lnet/minecraft/item/Items;TRIDENT:Lnet/minecraft/item/Item;")
            ),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasGlint()Z")
    )
	private boolean disableGlint(boolean original, @Local(argsOnly = true) ModelTransformationMode mode) {
        return switch (mode) {
            case NONE, GUI, GROUND, FIXED -> original;
            default -> false;
        };
    }
}