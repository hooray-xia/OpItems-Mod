package name.modid.mixin;

import name.modid.LapisHeart;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public class LapisHeartMixin {

    @Inject(
            method = "hurtServer",
            at = @At("RETURN")
    )
    private void onHurt(
            ServerLevel level,
            DamageSource source,
            float amount,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!cir.getReturnValue()) {
            return;
        }

        LivingEntity self = (LivingEntity) (Object) this;

        if (!(self instanceof Player player)) {
            return;
        }

        if (!LapisHeart.hasHeartBonus(player)) {
            return;
        }

        ItemStack held = player.getMainHandItem();
        if (held.isEmpty()) {
            return;
        }

        var enchantmentRegistry =
                level.registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT);

        List<Holder.Reference<Enchantment>> valid = new ArrayList<>();

        for (Holder.Reference<Enchantment> holder
                : enchantmentRegistry.listElements().toList()) {
            if (holder.value().isPrimaryItem(held)) {
                valid.add(holder);
            }
        }

        if (valid.isEmpty()) {
            return;
        }

        Holder.Reference<Enchantment> chosen =
                valid.get(player.getRandom().nextInt(valid.size()));

        int existingLevel = held.getEnchantments().getLevel(chosen);

        int newLevel = Math.min(
                existingLevel + 1,
                chosen.value().getMaxLevel()
        );

        held.enchant(chosen, newLevel);
    }
}
