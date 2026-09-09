package name.modid;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntities {

    public static EntityType<RainbowBladeProjectile> RAINBOW_BLADE_PROJECTILE;

    private ModEntities() {
    }

    public static void registerModEntityTypes() {
        Identifier id = Identifier.fromNamespaceAndPath(
                OpItems.MOD_ID,
                "rainbow_blade_projectile"
        );

        ResourceKey<EntityType<?>> key = ResourceKey.create(
                Registries.ENTITY_TYPE,
                id
        );

        RAINBOW_BLADE_PROJECTILE = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                id,
                EntityType.Builder
                        .<RainbowBladeProjectile>of(RainbowBladeProjectile::new, MobCategory.MISC)
                        .sized(0.5F, 0.5F)
                        .noSave()
                        .noSummon()
                        .build(key)
        );
    }
}
