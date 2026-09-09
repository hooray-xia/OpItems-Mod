package name.modid;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import java.util.List;

public class ArmorTrimX {

    public static SmithingTemplateItem ARMOR_TRIM_X;

    public static void register() {

        Identifier identifier = Identifier.fromNamespaceAndPath(
                "opitems",
                "armor_trim_x"
        );

        ResourceKey<Item> key = ResourceKey.create(
                Registries.ITEM,
                identifier
        );

        Item.Properties properties = new Item.Properties()
                .setId(key);

        ARMOR_TRIM_X = new SmithingTemplateItem(
                Component.translatable(
                        "item.minecraft.smithing_template.armor_trim.applies_to"
                ),
                Component.translatable(
                        "item.minecraft.smithing_template.armor_trim.ingredients"
                ),
                Component.translatable(
                        "item.minecraft.smithing_template.armor_trim.base_slot_description"
                ),
                Component.translatable(
                        "item.minecraft.smithing_template.armor_trim.additions_slot_description"
                ),

                List.of(
                        Identifier.fromNamespaceAndPath(
                                "minecraft",
                                "container/slot/helmet"
                        ),
                        Identifier.fromNamespaceAndPath(
                                "minecraft",
                                "container/slot/chestplate"
                        ),
                        Identifier.fromNamespaceAndPath(
                                "minecraft",
                                "container/slot/leggings"
                        ),
                        Identifier.fromNamespaceAndPath(
                                "minecraft",
                                "container/slot/boots"
                        )
                ),

                List.of(
                        Identifier.fromNamespaceAndPath(
                                "minecraft",
                                "container/slot/ingot"
                        )
                ),

                properties
        );

        Registry.register(
                BuiltInRegistries.ITEM,
                identifier,
                ARMOR_TRIM_X
        );
    }
}