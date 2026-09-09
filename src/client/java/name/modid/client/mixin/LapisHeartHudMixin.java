package name.modid.client.mixin;

import name.modid.LapisHeart;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Gui.class)
public class LapisHeartHudMixin {

    @ModifyArg(
            method = "extractHeart",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"
            ),
            index = 1
    )
    private static Identifier opitems$replaceHeartSprite(Identifier original) {
        Player player = Minecraft.getInstance().player;
        if (player == null || !LapisHeart.hasHeartBonus(player)) {
            return original;
        }

        if (!original.getNamespace().equals("minecraft")) {
            return original;
        }

        String path = original.getPath();
        if (!path.startsWith("hud/heart/")) {
            return original;
        }

        String name = path.substring("hud/heart/".length());
        if (name.startsWith("full") || name.startsWith("half") || name.startsWith("hardcore_")) {
            return Identifier.fromNamespaceAndPath("opitems", "hud/heart/lapis_" + name);
        }

        return original;
    }
}
