package name.modid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class EnchantedGoldenBread extends Item {
    
    public EnchantedGoldenBread(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);
        
        if (!level.isClientSide()) {
            // SUPER POWERFUL EFFECTS - Better than Enchanted Golden Apple!
            user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5600, 1));        // 30s Regen II
             user.addEffect(new MobEffectInstance(MobEffects.SPEED, 5600, 0));        // 30s Speed II
            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 4));         // 2min Absorption IV
            user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0));    // 5min Fire Res
            user.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 6000, 1));  // 5min Resistance
            user.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1200, 0));       // 1min Strength (EXTRA!)
        }
        
        return result;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true; // Enchanted shimmer effect!
    }
}