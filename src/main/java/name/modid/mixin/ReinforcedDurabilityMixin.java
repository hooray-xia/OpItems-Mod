package name.modid.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ReinforcedDurabilityMixin {

    @Inject(method = "getMaxDamage", at = @At("RETURN"), cancellable = true)
    private void opitems$reinforcedDurability(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack) (Object) this;

        ItemEnchantments enchantments = stack.getOrDefault(
                DataComponents.ENCHANTMENTS,
                ItemEnchantments.EMPTY
        );

        ResourceKey<Enchantment> reinforcedKey = ResourceKey.create(
                Registries.ENCHANTMENT,
                Identifier.fromNamespaceAndPath("opitems", "reinforced")
        );

        int level = 0;

        for (Holder<Enchantment> enchantment : enchantments.keySet()) {
            if (enchantment.unwrapKey().isPresent()
                    && enchantment.unwrapKey().get().equals(reinforcedKey)) {
                level = enchantments.getLevel(enchantment);
                break;
            }
        }

        if (level > 0) {
            cir.setReturnValue(cir.getReturnValue() + (level * 200));
        }
    }
}