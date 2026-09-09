package name.modid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EmeraldApple extends Item {

    public EmeraldApple(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);

        // Make sure potion effects only apply on the server side
        if (!level.isClientSide()) {
            // More trade/wealth focused
            user.addEffect(new MobEffectInstance(MobEffects.LUCK, 18000, 1)); // Luck II for 10 min
            user.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 16000, 0)); // 5 min
            // More defensive
            user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 2)); // Regen III for 15s
            user.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 1800, 1)); // Resistance II for 1.5min
            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1800, 3)); // Absorption IV for 1.5min
            user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1800, 0)); // Fire Res for 1.5min
            user.addEffect(new MobEffectInstance(MobEffects.SPEED, 1800, 0)); // Speed for 1.5min
        }   

        return result;
    }

    // Optional: Make the item glow like an Enchanted Golden Apple
    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }
}