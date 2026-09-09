package name.modid.client;

import com.mojang.blaze3d.platform.Window;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

import name.modid.ModEntities;

public class OpItemsClient implements ClientModInitializer {

    // Individual heart textures for different states
    private static final Identifier HEART_FULL =
    Identifier.fromNamespaceAndPath(
        "opitems",
        "textures/gui/sprites/hud/heart/lapis_full.png"
    );
    private static final Identifier HEART_HALF = Identifier.fromNamespaceAndPath(
            "opitems", "textures/gui/sprites/hud/heart/lapis_half.png"
    );
    private static final Identifier HEART_FULL_BLINKING = Identifier.fromNamespaceAndPath(
            "opitems", "textures/gui/sprites/hud/heart/lapis_full_blinking.png"
    );
    private static final Identifier HEART_HALF_BLINKING = Identifier.fromNamespaceAndPath(
            "opitems", "textures/gui/sprites/hud/heart/lapis_half_blinking.png"
    );
    private static final Identifier HEART_FULL_HARDCORE = Identifier.fromNamespaceAndPath(
            "opitems", "textures/gui/sprites/hud/heart/lapis_full_hardcore.png"
    );
    private static final Identifier HEART_HALF_HARDCORE = Identifier.fromNamespaceAndPath(
            "opitems", "textures/gui/sprites/hud/heart/lapis_half_hardcore.png"
    );
    private static final Identifier HEART_FULL_BLINKING_HARDCORE = Identifier.fromNamespaceAndPath(
            "opitems", "textures/gui/sprites/hud/heart/lapis_full_blinking_hardcore.png"
    );
    private static final Identifier HEART_HALF_BLINKING_HARDCORE = Identifier.fromNamespaceAndPath(
            "opitems", "textures/gui/sprites/hud/heart/lapis_half_blinking_hardcore.png"
    );

    // Alternative: Use a sprite sheet with UV coordinates
    // private static final Identifier HEART_SPRITESHEET = Identifier.fromNamespaceAndPath(
    //         "opitems", "textures/item/gui/hearts.png"
    // );

    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HEALTH_BAR,
                Identifier.fromNamespaceAndPath("opitems", "custom_hearts"),
                OpItemsClient::renderCustomHearts
        );

        EntityRendererRegistry.register(
                ModEntities.RAINBOW_BLADE_PROJECTILE,
                context -> new ThrownItemRenderer<>(context)
        );
    }

    private static void renderCustomHearts(
            GuiGraphicsExtractor graphics,
            DeltaTracker deltaTracker
    ) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player == null) {
            return;
        }

        float maxHealth = player.getMaxHealth();
        float currentHealth = player.getHealth();

        if (maxHealth <= 20.0F) {
            return;
        }

        // Calculate bonus hearts (same as vanilla: 1 heart = 2 health)
        int bonusHealth = (int) maxHealth - 20;
        int bonusHearts = (bonusHealth + 1) / 2;
        
        // Calculate how much health is in the bonus hearts (health above 20)
        float healthInBonus = Math.max(0, currentHealth - 20);
        int fullBonusHearts = (int) (healthInBonus / 2);
        boolean hasHalfHeart = (healthInBonus % 2) >= 1;

        // Check game mode and difficulty for hardcore hearts
        boolean isHardcore = Minecraft.getInstance().level.getLevelData().isHardcore();
        
        // Blinking animation based on damage tick
        boolean shouldBlink = false;
        if (player.invulnerableTime > 0 && (player.invulnerableTime / 3) % 2 == 0) {
            shouldBlink = true;
        }

        Window window = minecraft.getWindow();
        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();

        // Position hearts to the right of vanilla hearts
        int x = screenWidth / 2 + 91;
        int y = screenHeight - 39;

        for (int i = 0; i < bonusHearts; i++) {
            int heartX = x + i * 8;
            int heartY = y;

            Identifier heartTexture;
            
            // Determine if this heart should be full or half
            boolean isFull;
            if (i < fullBonusHearts) {
                isFull = true;
            } else if (i == fullBonusHearts && hasHalfHeart) {
                isFull = false;
            } else {
                // This heart is empty (should be transparent or hidden)
                continue;
            }

            // Select the appropriate texture based on state
            if (isHardcore) {
                if (shouldBlink) {
                    heartTexture = isFull ? HEART_FULL_BLINKING_HARDCORE : HEART_HALF_BLINKING_HARDCORE;
                } else {
                    heartTexture = isFull ? HEART_FULL_HARDCORE : HEART_HALF_HARDCORE;
                }
            } else {
                if (shouldBlink) {
                    heartTexture = isFull ? HEART_FULL_BLINKING : HEART_HALF_BLINKING;
                } else {
                    heartTexture = isFull ? HEART_FULL : HEART_HALF;
                }
            }

            graphics.blit(
                    heartTexture,
                    heartX,
                    heartY,
                    0,  // texture X offset
                    0,  // texture Y offset
                    9,  // width
                    9,  // height
                    9,  // texture width
                    9   // texture height
            );
        }
    }
}