package name.modid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class NetheriteApple extends Item {

    public NetheriteApple(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);

        // Only apply effects on the server side
        if (!level.isClientSide()) {
            // Regeneration III for 30 seconds (600 ticks) - Strong but not overpowered
            user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5600, 2));
            
            // Resistance III for 8 seconds (160 ticks) - Brief but powerful protection
            user.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 5160, 2));
            
            // Fire Resistance for 8 minutes (9600 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9600, 0));
            
            // Absorption IV for 2 minutes (2400 ticks) - Extra health buffer
            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3));
            
            // Strength II for 30 seconds (600 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 5600, 1));
            
            // Speed II for 30 seconds (600 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.SPEED, 5600, 1));;
            
            // Speed II for 30 seconds (600 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.SPEED, 5600, 1));
            
            // Saturation - Instant food replenishment
            user.addEffect(new MobEffectInstance(MobEffects.SATURATION, 5100, 0));
            
            // Night Vision for 5 minutes (6000 ticks) - Useful for nether exploration
            user.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 56000, 0));
            
            // Haste II for 30 seconds (600 ticks) - Netherite-themed mining boost
            user.addEffect(new MobEffectInstance(MobEffects.HASTE, 5600, 1));
            
            // Damage Resistance against fire/lava - Fire Resistance already covers this
        }

        return result;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return false; // Make it glow like Enchanted Golden Apple
    }
}