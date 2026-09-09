package name.modid;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class SuperEnchantStar extends Item {

    public SuperEnchantStar(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack starStack = player.getItemInHand(hand);
        ItemStack otherHandStack = hand == InteractionHand.MAIN_HAND
                ? player.getItemInHand(InteractionHand.OFF_HAND)
                : player.getItemInHand(InteractionHand.MAIN_HAND);

        if (otherHandStack.isEmpty()) {
            return InteractionResult.FAIL;
        }

        if (level instanceof ServerLevel serverLevel) {
            Registry<Enchantment> enchantmentRegistry = serverLevel.registryAccess()
                    .lookupOrThrow(Registries.ENCHANTMENT);

            int count = 0;

            for (Holder.Reference<Enchantment> ref : enchantmentRegistry.listElements().toList()) {
                if (!ref.is(EnchantmentTags.CURSE)
                        && ref.value().canEnchant(otherHandStack)) {

                    otherHandStack.enchant(ref, 20);
                    count++;
                }
            }

            if (count > 0) {
                level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.ENCHANTMENT_TABLE_USE,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );

                if (!player.getAbilities().instabuild) {
                    starStack.shrink(1);
                }

                player.awardStat(Stats.ITEM_USED.get(this));

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}