package name.modid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BedrockApple extends Item {

    public BedrockApple(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);

        // Make sure potion effects only apply on the server side
        if (!level.isClientSide()) {
            // ALL positive buffs at LEVEL 10 (amplifier 9)
            // Duration: 13 minutes (15600 ticks)
            // 1 minute = 1200 ticks, so 13 * 1200 = 15600
            
            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 15600, 9));           // Absorption X
            user.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 15600, 9));        // Conduit Power X
            user.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 15600, 9));     // Resistance X
            user.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 15600, 9));       // Dolphins Grace X
            user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 15600, 9));       // Fire Resistance X
            user.addEffect(new MobEffectInstance(MobEffects.HASTE, 15600, 9));                // Haste X
            user.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 15600, 9));          // Health Boost X
            user.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 15600, 9));   // Hero of the Village X
            user.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 15600, 9));          // Invisibility X
            user.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 15600, 9));            // Jump Boost X
            user.addEffect(new MobEffectInstance(MobEffects.LUCK, 15600, 9));                  // Luck X
            user.addEffect(new MobEffectInstance(MobEffects.SPEED, 15600, 9));        // Speed X
            user.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 15600, 9));          // Night Vision X
            user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 15600, 9));          // Regeneration X
            user.addEffect(new MobEffectInstance(MobEffects.SATURATION, 15600, 9));            // Saturation X
            user.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 15600, 9));          // Slow Falling X
            user.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 15600, 9));       // Water Breathing X
            
            // Instant Health - instant effect, level X
            user.addEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 9));            // Instant Health X
        }   

        return result;
    }

    // Make the item glow like an Enchanted Golden Apple
    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }
}