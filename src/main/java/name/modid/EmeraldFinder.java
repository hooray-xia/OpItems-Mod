package name.modid;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class EmeraldFinder {

    public static final Holder<MobEffect> EFFECT = Registry.registerForHolder(
        BuiltInRegistries.MOB_EFFECT,
        Identifier.fromNamespaceAndPath("opitems", "emerald_finder"),
        new EmeraldFinderEffect()
    );

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (world.isClientSide()) {
                return;
            }

            MobEffectInstance effect = player.getEffect(EFFECT);

            if (effect == null) {
                return;
            }

            if (world.getRandom().nextFloat() < 0.10f) {
                ItemStack emerald = new ItemStack(Items.EMERALD);

                if (!player.getInventory().add(emerald)) {
                    player.drop(emerald, false);
                }
            }
        });
    }

    public static class EmeraldFinderEffect extends MobEffect {

        public EmeraldFinderEffect() {
            super(MobEffectCategory.BENEFICIAL, 0x50C878);
        }
    }
}