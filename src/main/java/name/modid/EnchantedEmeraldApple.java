package name.modid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnchantedEmeraldApple extends Item {

    public EnchantedEmeraldApple(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);

        // Make sure potion effects only apply on the server side
        if (!level.isClientSide()) {
            // More trade/wealth focused
            user.addEffect(new MobEffectInstance(MobEffects.LUCK, 18000, 3)); // Luck III for 10 min
            user.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 16000, 0)); // 5 min
            user.addEffect(new MobEffectInstance(EmeraldFinder.EFFECT, 1600, 0)); // 5 min
            // More defensive
            user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2300, 3)); // Regen III for 15s
            user.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 1800, 2)); // Resistance II for 1.5min
            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1800, 5)); // Absorption VI for 1.5min
            user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1800, 0)); // Fire Res for 1.5min
            user.addEffect(new MobEffectInstance(MobEffects.SPEED, 2800, 1)); // Speed for 1.5min
            user.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 2800, 1)); // Water Breath for 1.5min
            user.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 5800, 0)); // Night Vision for 1.5min
            user.addEffect(new MobEffectInstance(MobEffects.HASTE, 2000, 2));
        }   
        
        return result;
    }

    // Optional: Make the item glow like an Enchanted Golden Apple
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}