package name.modid;
import net.minecraft.world.item.Items; // For MCP
import net.minecraft.world.item.alchemy.Potions; // For MCP


import java.util.List;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.FoodProperties;
import name.modid.ModItemTags;
import name.modid.recipe.StewSpikingCraftingRecipe;
import name.modid.recipe.PotionMixingRecipe;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.MapColor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpItems implements ModInitializer {

    public static final String MOD_ID = "opitems";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // =========================================================
    // FOOD PROPERTIES (These are safe - they don't register anything!)
    // =========================================================
public static final FoodProperties DIAMOND_APPLE_FOOD =
        new FoodProperties.Builder()
                .nutrition(8)
                .saturationModifier(1.2F)
                .alwaysEdible()
                .build();

public static final FoodProperties ENCHANTED_DIAMOND_APPLE_FOOD =
        new FoodProperties.Builder()
                .nutrition(12)
                .saturationModifier(2.0F)
                .alwaysEdible()
                .build();

public static final FoodProperties NETHERITE_APPLE_FOOD =
        new FoodProperties.Builder()
                .nutrition(14)
                .saturationModifier(2.2F)
                .alwaysEdible()
                .build();

public static final FoodProperties ENCHANTED_NETHERITE_APPLE_FOOD =
        new FoodProperties.Builder()
                .nutrition(18)
                .saturationModifier(3.0F)
                .alwaysEdible()
                .build();

public static final FoodProperties EMERALD_APPLE_FOOD =
        new FoodProperties.Builder()
                .nutrition(10)
                .saturationModifier(1.5F)
                .alwaysEdible()
                .build();

public static final FoodProperties ENCHANTED_EMERALD_APPLE_FOOD =
        new FoodProperties.Builder()
                .nutrition(14)
                .saturationModifier(2.2F)
                .alwaysEdible()
                .build();

public static final FoodProperties OBSIDIAN_APPLE_FOOD =
        new FoodProperties.Builder()
                .nutrition(12)
                .saturationModifier(2.0F)
                .alwaysEdible()
                .build();

public static final FoodProperties BEDROCK_APPLE_FOOD =
        new FoodProperties.Builder()
                .nutrition(30)
                .saturationModifier(8.0F)
                .alwaysEdible()
                .build();

public static final FoodProperties ENCHANTED_OBSIDIAN_APPLE_FOOD =
        new FoodProperties.Builder()
                .nutrition(16)
                .saturationModifier(2.5F)
                .alwaysEdible()
                .build();

public static final FoodProperties GOLDEN_BREAD_FOOD =
        new FoodProperties.Builder()
                .nutrition(8)
                .saturationModifier(1.2F)
                .alwaysEdible()
                .build();

public static final FoodProperties ENCHANTED_GOLDEN_BREAD_FOOD =
        new FoodProperties.Builder()
                .nutrition(12)
                .saturationModifier(2.0F)
                .alwaysEdible()
                .build();
    
    // =========================================================
    // DECLARE ALL BLOCKS & ITEMS (NO INITIALIZATION!)
    // =========================================================
        // 1. Register the STRUCTURE TYPE first

    public static Block RAINBOW_BLOCK;
    public static Item RAINBOW_BLOCK_ITEM;
    
    public static Block COMPRESSED_OBSIDIAN_BLOCK;
    public static Item COMPRESSED_OBSIDIAN_ITEM;
    
    public static Block RAINBOW_ORE;
    public static Item RAINBOW_ORE_ITEM;

    public static Block DEEPSLATE_RAINBOW_ORE;
    public static Item DEEPSLATE_RAINBOW_ORE_ITEM;

    public static Block ECHO_PRISM_BLOCK;
    public static Item ECHO_PRISM_ITEM;

    public static Item DIAMOND_APPLE;
    public static Item ENCHANTED_DIAMOND_APPLE;
    public static Item EMERALD_APPLE;
    public static Item ENCHANTED_EMERALD_APPLE;
    public static Item OBSIDIAN_APPLE;
    public static Item BEDROCK_APPLE;
    public static Item ENCHANTED_OBSIDIAN_APPLE;

    // TOOLS & WEAPONS
    public static Item DRAGON_BLADE;
    public static Item ENDER_SPEAR;
    public static Item RAINBOW_BLADE;
    public static Item WITHER_BLADE;
    public static Item RAINBOW_PICKAXE;
    public static Item RAINBOW_AXE;
    public static Item RAINBOW_HOE;

    // SPECIAL ITEMS
    public static Item ENCHANT_STAR;
    public static Item BLOOD_TOTEM;
    public static Item RAINBOW_SHOVEL;
    public static Item SUPER_ENCHANT_STAR;
    public static Item RAINBOW_INGOT;
    public static Item ENDER_POWDER;
    public static Item RAW_RAINBOW_ORE;  // ✅ DECLARED HERE (NO initialization!)
    public static Item LAPIS_HEART;
    public static Item NETHERITE_APPLE;
    public static Item ENCHANTED_NETHERITE_APPLE;
    public static Item ENCHANTED_GOLDEN_BREAD;
    public static Item GOLDEN_BREAD;



    // TOOL MATERIALS (These are safe - they don't register anything!)
    public static ToolMaterial DRAGON_BLADE_MATERIAL;
    public static ToolMaterial ENDER_SPEAR_MATERIAL;
    public static ToolMaterial RAINBOW_TOOL_MATERIAL;
    public static ToolMaterial WITHER_BLADE_MATERIAL;
    public static final TagKey<Item> REPAIRS_RAINBOW_TOOL = TagKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath("opitems", "repairs_rainbow_tool")
    );
    // =========================================================
    // WORLD GENERATION (Safe - just a ResourceKey)
    // =========================================================
    @SuppressWarnings("null")
    public static final ResourceKey<PlacedFeature> RAINBOW_ORE_PLACED_KEY = 
            ResourceKey.create(Registries.PLACED_FEATURE, id("deepslate_rainbow_ore"));

    // =========================================================
    // INITIALIZATION - ALL REGISTRATION HAPPENS HERE!
    // =========================================================
    
    @Override
    public void onInitialize() {
        LOGGER.info("Starting OpItems initialization...");
        EmeraldFinder.register();
        ModEntities.registerModEntityTypes();
        
        Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                id("stew_spiking"),
                StewSpikingCraftingRecipe.SERIALIZER
        );
        Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                id("potion_mixing_recipe"),
                PotionMixingRecipe.SERIALIZER
        );
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.addMix(
                Potions.LEAPING,
                Items.GOLD_INGOT,
                Potions.LUCK // Make sure this is defined somewhere
            );
        });
        DRAGON_BLADE_MATERIAL = new ToolMaterial(
                BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                2800,
                8.0F,
                18.0F,
                20,
                ItemTags.NETHERITE_TOOL_MATERIALS
        );

        ENDER_SPEAR_MATERIAL = new ToolMaterial(
                BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                2500,
                8.0F,
                10.0F,
                17,
                ItemTags.NETHERITE_TOOL_MATERIALS
        );

        RAINBOW_TOOL_MATERIAL = new ToolMaterial(
                BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                3200,
                48.0F,
                12.0F,
                16,
                ModItemTags.REPAIRS_RAINBOW_TOOL
        );

        WITHER_BLADE_MATERIAL = new ToolMaterial(
                BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                3000,
                9.0F,
                25.0F,
                18,
                ItemTags.NETHERITE_TOOL_MATERIALS
        );

        // =========================================================
        // REGISTER BLOCKS
        // =========================================================

        RAINBOW_BLOCK = registerBlock(
                "rainbow_block",
                Block::new,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_PINK)
                        .strength(10.0F, 6.0F)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops()
        );

        RAINBOW_BLOCK_ITEM = register(
                "rainbow_block",
                properties -> new BlockItem(RAINBOW_BLOCK, properties),
                new Item.Properties()
                        .rarity(Rarity.RARE)
        );

        COMPRESSED_OBSIDIAN_BLOCK = registerBlock(
                "compressed_obsidian",
                Block::new,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_BLACK)
                        .strength(100.0F, 1200.0F)
                        .sound(SoundType.STONE)
                        .requiresCorrectToolForDrops()
        );

        COMPRESSED_OBSIDIAN_ITEM = register(
                "compressed_obsidian",
                properties -> new BlockItem(COMPRESSED_OBSIDIAN_BLOCK, properties),
                new Item.Properties()
                        .rarity(Rarity.RARE)
        );

        RAINBOW_ORE = registerBlock(
                "rainbow_ore",
                Block::new,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_PINK)
                        .strength(4.0F, 4.0F)
                        .sound(SoundType.STONE)
                        .requiresCorrectToolForDrops()
        );

        RAINBOW_ORE_ITEM = register(
                "rainbow_ore",
                properties -> new BlockItem(RAINBOW_ORE, properties),
                new Item.Properties()
                        .rarity(Rarity.RARE)
        );

        DEEPSLATE_RAINBOW_ORE = registerBlock(
                "deepslate_rainbow_ore",
                Block::new,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_PINK)
                        .strength(5.0F, 6.0F)
                        .sound(SoundType.METAL)
                        .requiresCorrectToolForDrops()
        );

        DEEPSLATE_RAINBOW_ORE_ITEM = register(
                "deepslate_rainbow_ore",
                properties -> new BlockItem(DEEPSLATE_RAINBOW_ORE, properties),
                new Item.Properties()
                        .rarity(Rarity.RARE)
        );

        ECHO_PRISM_BLOCK = registerBlock(
                "echo_prism",
                EchoPrism::new,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_PURPLE)
                        .strength(3.0F, 6.0F)
                        .sound(SoundType.AMETHYST)
                        .requiresCorrectToolForDrops()
        );

        ECHO_PRISM_ITEM = register(
                "echo_prism",
                properties -> new BlockItem(ECHO_PRISM_BLOCK, properties),
                new Item.Properties()
                        .rarity(Rarity.RARE)
        );

        // =========================================================
        // REGISTER APPLES
        // =========================================================

        DIAMOND_APPLE = register(
                "diamond_apple",
                DiamondApple::new,
                new Item.Properties()
                        .food(DIAMOND_APPLE_FOOD)
                        .rarity(Rarity.RARE)
        );
        GOLDEN_BREAD = register(
                "golden_bread",
                GoldenBread::new,
                new Item.Properties()
                        .food(GOLDEN_BREAD_FOOD)
                        .rarity(Rarity.RARE)
        );
        ENCHANTED_GOLDEN_BREAD = register(
                "enchanted_golden_bread",
                EnchantedGoldenBread::new,
                new Item.Properties()
                        .food(ENCHANTED_GOLDEN_BREAD_FOOD)
                        .rarity(Rarity.EPIC)
        );
        ENCHANTED_DIAMOND_APPLE = register(
                "enchanted_diamond_apple",
                EnchantedDiamondApple::new,
                new Item.Properties()
                        .food(ENCHANTED_DIAMOND_APPLE_FOOD)
                        .rarity(Rarity.EPIC)
        );
        NETHERITE_APPLE = register(
                "netherite_apple",
                NetheriteApple::new,
                new Item.Properties()
                        .food(NETHERITE_APPLE_FOOD)
                        .rarity(Rarity.EPIC)
        );
        ENCHANTED_NETHERITE_APPLE = register(
                "enchanted_netherite_apple",
                EnchantedNetheriteApple::new,
                new Item.Properties()
                        .food(ENCHANTED_NETHERITE_APPLE_FOOD)
                        .rarity(Rarity.EPIC)
        );
        EMERALD_APPLE = register(
                "emerald_apple",
                EmeraldApple::new,
                new Item.Properties()
                        .food(EMERALD_APPLE_FOOD)
                        .rarity(Rarity.RARE)
        );

        ENCHANTED_EMERALD_APPLE = register(
                "enchanted_emerald_apple",
                EnchantedEmeraldApple::new,
                new Item.Properties()
                        .food(ENCHANTED_EMERALD_APPLE_FOOD)
                        .rarity(Rarity.EPIC)
        );
        BEDROCK_APPLE = register(
                "bedrock_apple",
                BedrockApple::new,
                new Item.Properties()
                        .food(BEDROCK_APPLE_FOOD)
                        .rarity(Rarity.EPIC)
        );
        
        OBSIDIAN_APPLE = register(
                "obsidian_apple",
                ObsidianApple::new,
                new Item.Properties()
                        .food(OBSIDIAN_APPLE_FOOD)
                        .rarity(Rarity.EPIC)
                        .fireResistant()
        );

        ENCHANTED_OBSIDIAN_APPLE = register(
                "enchanted_obsidian_apple",
                EnchantedObsidianApple::new,
                new Item.Properties()
                        .food(ENCHANTED_OBSIDIAN_APPLE_FOOD)
                        .rarity(Rarity.EPIC)
                        .fireResistant()
        );

        // =========================================================
        // REGISTER WEAPONS
        // =========================================================

        DRAGON_BLADE = register(
                "dragon_blade",
                DragonBlade::new,
                new Item.Properties()
                        .sword(DRAGON_BLADE_MATERIAL, 3.0F, -2.4F)
                        .fireResistant()
                        .rarity(Rarity.EPIC)
                        .attributes(
                                ItemAttributeModifiers.builder()
                                        .add(
                                                Attributes.ATTACK_DAMAGE,
                                                new AttributeModifier(
                                                        Identifier.fromNamespaceAndPath(MOD_ID, "attack_damage"),
                                                        25.0,
                                                        AttributeModifier.Operation.ADD_VALUE
                                                ),
                                                EquipmentSlotGroup.MAINHAND
                                        )
                                        .add(
                                                Attributes.ATTACK_SPEED,
                                                new AttributeModifier(
                                                        Identifier.fromNamespaceAndPath(MOD_ID, "attack_speed"),
                                                        1.6,
                                                        AttributeModifier.Operation.ADD_VALUE
                                                ),
                                                EquipmentSlotGroup.MAINHAND
                                        )
                                        .add(
                                                Attributes.MAX_HEALTH,
                                                new AttributeModifier(
                                                        Identifier.fromNamespaceAndPath(MOD_ID, "dragon_blade.max_health"),
                                                        10.0,
                                                        AttributeModifier.Operation.ADD_VALUE
                                                ),
                                                EquipmentSlotGroup.MAINHAND
                                        )
                                        .build()
                        )
        );
RAINBOW_HOE = register(
    "rainbow_hoe",
    properties -> new RainbowHoe(RAINBOW_TOOL_MATERIAL, 12.0F, -2.4F, properties),
    new Item.Properties()
        .rarity(Rarity.EPIC)
        .fireResistant()
        .enchantable(22)
        .durability(3200)
        .component(
            DataComponents.TOOL,
            new Tool(
                List.of(),
                48.0F,
                1,
                true
            )
        )
        .component(
            DataComponents.ATTRIBUTE_MODIFIERS,
            ItemAttributeModifiers.builder()
                .add(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                        Identifier.fromNamespaceAndPath(
                            "opitems",
                            "rainbow_hoe_damage"
                        ),
                        8.0,
                        AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
                )
                .add(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(
                        Identifier.fromNamespaceAndPath(
                            "opitems",
                            "rainbow_hoe_speed"
                        ),
                        -1.0,
                        AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
                )
                .build()
        )
);
        ENDER_SPEAR = register(
                "ender_spear",
                EnderSpear::new,
                new Item.Properties()
                        .rarity(Rarity.EPIC)
                        .fireResistant()
                        .durability(2500)
                        .sword(ENDER_SPEAR_MATERIAL, 4.0F, -2.0F)
        );

        RAINBOW_BLADE = register(
                "rainbow_blade",
                RainbowBladeItem::new,
                new Item.Properties()
                        .sword(RAINBOW_TOOL_MATERIAL, 12.0F, -2.4F)
                        .rarity(Rarity.EPIC)
                        .enchantable(22)
        );
     // In the REGISTER WEAPONS section, replace the current RAINBOW_PICKAXE registration with:
RAINBOW_SHOVEL = register(
    "rainbow_shovel",
    properties -> new RainbowShovel(RAINBOW_TOOL_MATERIAL, 12.0F, -2.4F, properties),
    new Item.Properties()
        .rarity(Rarity.EPIC)
        .fireResistant()
        .enchantable(22)
        .durability(3200)
        .component(
            DataComponents.TOOL,
            new Tool(
                List.of(),
                48.0F,
                1,
                true
            )
        )
        .component(
            DataComponents.ATTRIBUTE_MODIFIERS,
            ItemAttributeModifiers.builder()
                .add(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                        Identifier.fromNamespaceAndPath(
                            "opitems",
                            "rainbow_shovel_damage"
                        ),
                        10.0,
                        AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
                )
                .add(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(
                        Identifier.fromNamespaceAndPath(
                            "opitems",
                            "rainbow_shovel_speed"
                        ),
                        3.0,
                        AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
                )
                .build()
        )
);
RAINBOW_PICKAXE = register(
    "rainbow_pickaxe",
    properties -> new RainbowPickaxe(properties),
    new Item.Properties()
        .rarity(Rarity.EPIC)
        .fireResistant()
        .enchantable(22)
        .durability(3200)
        .component(
            DataComponents.TOOL,
            new Tool(
                List.of(),
                48.0F,
                1,
                true
            )
        )
        .component(
            DataComponents.ATTRIBUTE_MODIFIERS,
            ItemAttributeModifiers.builder()
                .add(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                        Identifier.fromNamespaceAndPath(
                            "opitems",
                            "rainbow_pickaxe_damage"
                        ),
                        12.0,
                        AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
                )
                .add(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(
                        Identifier.fromNamespaceAndPath(
                            "opitems",
                            "rainbow_pickaxe_speed"
                        ),
                        3.0,
                        AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
                )
                .build()
        )
);

RAINBOW_AXE = register(
    "rainbow_axe",
    properties -> new RainbowAxe(RAINBOW_TOOL_MATERIAL, 15.0F, -3.0F, properties),
    new Item.Properties()
        .rarity(Rarity.EPIC)
        .fireResistant()
        .enchantable(22)
        .durability(3200)
        .component(
            DataComponents.TOOL,
            new Tool(
                List.of(),
                48.0F,
                1,
                true
            )
        )
        .component(
            DataComponents.ATTRIBUTE_MODIFIERS,
            ItemAttributeModifiers.builder()
                .add(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                        Identifier.fromNamespaceAndPath(
                            "opitems",
                            "rainbow_axe_damage"
                        ),
                        20.0,
                        AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
                )
                .add(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(
                        Identifier.fromNamespaceAndPath(
                            "opitems",
                            "rainbow_axe_speed"
                        ),
                        3.0,
                        AttributeModifier.Operation.ADD_VALUE
                    ),
                    EquipmentSlotGroup.MAINHAND
                )
                .build()
        )
);
        WITHER_BLADE = register(
                "wither_blade",
                WitherBlade::new,
                new Item.Properties()
                        .sword(WITHER_BLADE_MATERIAL, 3.0F, -2.4F)
                        .fireResistant()
                        .rarity(Rarity.EPIC)
        );

        // =========================================================
        // REGISTER SPECIAL ITEMS
        // =========================================================

        ENCHANT_STAR = register(
                "enchant_star",
                EnchantStarItem::new,
                new Item.Properties()
                        .rarity(Rarity.EPIC)
                        .fireResistant()
                        .stacksTo(1)
        );

        BLOOD_TOTEM = register(
                "blood_totem",
                BloodTotemItem::new,
                new Item.Properties()
                        .rarity(Rarity.EPIC)
                        .fireResistant()
                        .stacksTo(1)
        );

        SUPER_ENCHANT_STAR = register(
                "super_enchant_star",
                SuperEnchantStar::new,
                new Item.Properties()
                        .rarity(Rarity.EPIC)
                        .fireResistant()
                        .stacksTo(1)
        );

        Holder<TrimMaterial> rainbowTrimMaterial = Holder.direct(
                new TrimMaterial(
                        MaterialAssetGroup.create("rainbow"),
                        Component.translatable("trim_material.opitems.rainbow").withColor(0xFF00FF)
                )
        );

        RAINBOW_INGOT = register(
        "rainbow_ingot",
        new Item.Properties()
                .rarity(Rarity.RARE)
                .fireResistant()
                .stacksTo(64)
                .component(
                        DataComponents.PROVIDES_TRIM_MATERIAL,
                        rainbowTrimMaterial
                )
);

        ENDER_POWDER = register(
                "ender_powder",
                EnderPowder::new,
                new Item.Properties()
                        .rarity(Rarity.EPIC)
                        .stacksTo(64)
        );

        // ✅ FIXED: RAW_RAINBOW_ORE is now registered HERE inside onInitialize()
        RAW_RAINBOW_ORE = register(
                "raw_rainbow_ore",
                new Item.Properties()
                        .stacksTo(64)
                        .rarity(Rarity.RARE)
                        .fireResistant()
        );

        LAPIS_HEART = register(
                "lapis_heart",
                LapisHeart::new,
                new Item.Properties()
                        .rarity(Rarity.RARE)
                        .stacksTo(64)
        );

        // =========================================================
        // REGISTER ARMOR TRIM
        // =========================================================

        ArmorTrimX.register();

        // =========================================================
        // REGISTER WORLD GENERATION
        // =========================================================

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                RAINBOW_ORE_PLACED_KEY
        );

        // =========================================================
        // CREATIVE MODE TABS
        // =========================================================

        // COMBAT TAB
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
                .register(output -> {
                    output.accept(DRAGON_BLADE.asItem());
                    output.accept(WITHER_BLADE.asItem());
                    output.accept(ENDER_SPEAR.asItem());
                    output.accept(RAINBOW_BLADE.asItem());
                    output.accept(BLOOD_TOTEM.asItem());
                });

        // FOOD TAB
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register(output -> {
                    output.accept(DIAMOND_APPLE.asItem());
                    output.accept(EMERALD_APPLE.asItem());
                    output.accept(ENCHANTED_DIAMOND_APPLE.asItem());
                    output.accept(ENCHANTED_EMERALD_APPLE.asItem());
                    output.accept(OBSIDIAN_APPLE.asItem());
                    output.accept(ENCHANTED_OBSIDIAN_APPLE.asItem());
                    output.accept(NETHERITE_APPLE.asItem());
                    output.accept(ENCHANTED_NETHERITE_APPLE.asItem());
                    output.accept(BEDROCK_APPLE.asItem());
                    output.accept(GOLDEN_BREAD.asItem());
                    output.accept(ENCHANTED_GOLDEN_BREAD.asItem());
                });

        // BUILDING BLOCKS TAB
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS)
                .register(output -> {
                    output.accept(RAINBOW_BLOCK.asItem());
                    output.accept(COMPRESSED_OBSIDIAN_ITEM.asItem());
                    output.accept(RAINBOW_ORE.asItem());
                    output.accept(DEEPSLATE_RAINBOW_ORE.asItem());
                    output.accept(ECHO_PRISM_ITEM.asItem());
                });

        // INGREDIENTS TAB
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(output -> {
                    output.accept(RAW_RAINBOW_ORE.asItem());
                    output.accept(ENDER_POWDER.asItem());
                    output.accept(ENCHANT_STAR.asItem());
                    output.accept(SUPER_ENCHANT_STAR.asItem());
                    output.accept(ArmorTrimX.ARMOR_TRIM_X.asItem());
                    output.accept(RAINBOW_INGOT.asItem());
                    output.accept(LAPIS_HEART.asItem());
                });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(output -> {
                        output.accept(RAINBOW_PICKAXE.asItem());
                        output.accept(RAINBOW_SHOVEL.asItem());
                        output.accept(RAINBOW_AXE.asItem());
                        output.accept(RAINBOW_HOE.asItem());
                });
        // =========================================================
        // FUEL VALUES
        // =========================================================

        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ENDER_POWDER.asItem(), 180 * 20);
        });

        LOGGER.info("✅ OpItems initialized! All custom items, blocks, and fuel properties registered successfully!mADE BY HOORAY X.");
    }

    // =========================================================
    // BLOCK REGISTRATION HELPER
    // =========================================================

    private static <T extends Block> T registerBlock(
            String name,
            java.util.function.Function<BlockBehaviour.Properties, T> factory,
            BlockBehaviour.Properties properties
    ) {
        Identifier identifier = Identifier.fromNamespaceAndPath(MOD_ID, name);
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, identifier);
        T block = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, identifier, block);
    }

    // =========================================================
    // ITEM REGISTRATION HELPERS
    // =========================================================

    private static Item register(
            String name,
            Item.Properties properties
    ) {
        return register(name, Item::new, properties);
    }

    private static <T extends Item> T register(
            String name,
            java.util.function.Function<Item.Properties, T> factory,
            Item.Properties properties
    ) {
        Identifier identifier = Identifier.fromNamespaceAndPath(MOD_ID, name);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, identifier);
        T item = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, identifier, item);
    }

    // =========================================================
    // ID HELPER

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}