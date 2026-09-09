package name.modid.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PotionMixingRecipe extends CustomRecipe {
	public static final PotionMixingRecipe INSTANCE = new PotionMixingRecipe();
	public static final MapCodec<PotionMixingRecipe> CODEC = MapCodec.unit(INSTANCE);
	public static final StreamCodec<RegistryFriendlyByteBuf, PotionMixingRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);
	public static final RecipeSerializer<PotionMixingRecipe> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

	@Override
	public boolean matches(CraftingInput input, Level level) {
		return getItemsFromInput(input) != null;
	}

	@Override
	public ItemStack assemble(CraftingInput input) {
		PotionAndIngredients ingredients = getItemsFromInput(input);

		if (ingredients == null) {
			return ItemStack.EMPTY;
		}

		ItemStack basePotion = ingredients.basePotion().copy();
		PotionContents originalContents = basePotion.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

		// We use a TreeMap to hopefully correctly compare Holder<MobEffect>s.
		Map<Holder<MobEffect>, Integer> effects = new TreeMap<>(
				(one, two) -> {
					//noinspection deprecation
					if (one == two || one.is(two)) {
						return 0;
					}

					return one.toString().compareTo(two.toString());
				}
		);

		// Add original effects
		originalContents.getAllEffects().forEach(instance -> 
			effects.put(instance.getEffect(), instance.getDuration())
		);

		// Process each ingredient potion
		for (ItemStack potion : ingredients.ingredients()) {
			float durationScale = potion.getOrDefault(DataComponents.POTION_DURATION_SCALE, 1F);
			PotionContents potionContents = potion.get(DataComponents.POTION_CONTENTS);

			// This is fine because we checked for the presence of the component in getItemsFromInput.
			//noinspection DataFlowIssue
			potionContents.getAllEffects().forEach(instance -> {
				Holder<MobEffect> effect = instance.getEffect();
				int duration = effects.getOrDefault(effect, 0);

				if (duration == MobEffectInstance.INFINITE_DURATION) {
					return;
				}

				duration = Math.max(Math.round(instance.getDuration() * (instance.getAmplifier() + 1) * durationScale), duration);
				effects.put(effect, duration);
			});
		}

		// Convert effects map back to PotionContents using the correct constructor
		List<MobEffectInstance> effectInstances = new ArrayList<>();
		effects.forEach((effect, duration) -> {
			if (duration > 0) {
				// Find the highest amplifier for this effect
				int amplifier = 0;
				for (ItemStack potion : ingredients.ingredients()) {
					PotionContents contents = potion.get(DataComponents.POTION_CONTENTS);
					if (contents != null) {
						for (MobEffectInstance instance : contents.getAllEffects()) {
							if (instance.getEffect().equals(effect)) {
								amplifier = Math.max(amplifier, instance.getAmplifier());
							}
						}
					}
				}
				effectInstances.add(new MobEffectInstance(effect, duration, amplifier));
			}
		});

		// Use the correct PotionContents constructor:
		// PotionContents(Optional<Holder<Potion>> potion, Optional<Integer> customColor, List<MobEffectInstance> customEffects, Optional<String> customName)
		PotionContents newContents = new PotionContents(
			originalContents.potion(),
			originalContents.customColor(),
			effectInstances,
			originalContents.customName()
		);

		basePotion.set(DataComponents.POTION_CONTENTS, newContents);
		return basePotion;
	}

	@Nullable
	public static PotionAndIngredients getItemsFromInput(CraftingInput input) {
		List<ItemStack> items = input.items();

		if (items.size() <= 1) {
			return null;
		}

		ItemStack basePotion = ItemStack.EMPTY;
		List<ItemStack> ingredients = new ArrayList<>();

		for (ItemStack stack : input.items()) {
			if (stack.has(DataComponents.POTION_CONTENTS)) {
				if (basePotion.isEmpty()) {
					// First potion becomes the base
					basePotion = stack;
				} else {
					// Additional potions become ingredients
					ingredients.add(stack);
				}
			} else {
				return null;
			}
		}

		if (basePotion.isEmpty() || ingredients.isEmpty()) {
			return null;
		}

		return new PotionAndIngredients(basePotion, ingredients);
	}

	@Override
	public RecipeSerializer<? extends CustomRecipe> getSerializer() {
		return SERIALIZER;
	}

	public record PotionAndIngredients(ItemStack basePotion, List<ItemStack> ingredients) {
	}
}