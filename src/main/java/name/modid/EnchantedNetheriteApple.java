package name.modid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class EnchantedNetheriteApple extends Item {

    public EnchantedNetheriteApple(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);

        // Only apply effects on the server side
        if (!level.isClientSide()) {
            // ====== MAJOR BUFFS ======
            
            // Regeneration IV for 30 seconds (600 ticks) - Fast healing
            user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5600, 4));
            
            // Absorption IV for 26 minutes (26400 ticks) - 8 extra hearts
            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 26400, 8));
            
            // Resistance III for 30 seconds (600 ticks) - 60% damage reduction
            user.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 5600, 3));
            
            // Fire Resistance for 10 minutes (12000 ticks) - Nether immunity
            user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000, 0));
            
            // Strength III for 30 seconds (600 ticks) - +6 attack damage
            user.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 5600, 3));
            
            // ====== SECONDARY BUFFS ======
            
            // Speed II for 45 seconds (900 ticks) - Quick movement
            user.addEffect(new MobEffectInstance(MobEffects.SPEED, 4900, 3));
            
            // Haste II for 45 seconds (900 ticks) - Fast mining
            user.addEffect(new MobEffectInstance(MobEffects.HASTE, 4900, 3));
            
            // Jump Boost II for 45 seconds (900 ticks) - Enhanced mobility
            user.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 4900, 1));
            
            // Night Vision for 10 minutes (12000 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 12000, 0));
            
            // Saturation - Instant food fill
            user.addEffect(new MobEffectInstance(MobEffects.SATURATION, 12000, 0));
            
            // ====== NETHERITE EXCLUSIVE ======
            // Conduit Power - Underwater + Nether equivalent (mining + vision)
            user.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 5600, 0));
        }

        return result;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true; // Glowing effect
    }
}