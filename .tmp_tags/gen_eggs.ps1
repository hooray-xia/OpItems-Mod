# Spawn egg to crafting material mapping
$eggMaterials = @{
    # Hostile - Undead
    "zombie_spawn_egg" = "minecraft:rotten_flesh"
    "skeleton_spawn_egg" = "minecraft:bone"
    "drowned_spawn_egg" = "minecraft:rotten_flesh"
    "husk_spawn_egg" = "minecraft:rotten_flesh"
    "stray_spawn_egg" = "minecraft:bone"
    "wither_skeleton_spawn_egg" = "minecraft:wither_skeleton_skull"
    "zombified_piglin_spawn_egg" = "minecraft:rotten_flesh"
    "zoglin_spawn_egg" = "minecraft:rotten_flesh"
    "phantom_spawn_egg" = "minecraft:phantom_membrane"
    "bogged_spawn_egg" = "minecraft:bone"
    
    # Hostile - Arthropods
    "spider_spawn_egg" = "minecraft:string"
    "cave_spider_spawn_egg" = "minecraft:string"
    "silverfish_spawn_egg" = "minecraft:stone"
    "endermite_spawn_egg" = "minecraft:ender_pearl"
    "bee_spawn_egg" = "minecraft:honeycomb"
    
    # Hostile - Creeper
    "creeper_spawn_egg" = "minecraft:gunpowder"
    
    # Hostile - End
    "enderman_spawn_egg" = "minecraft:ender_pearl"
    "shulker_spawn_egg" = "minecraft:shulker_shell"
    "ender_dragon_spawn_egg" = "minecraft:dragon_egg"
    
    # Hostile - Nether
    "blaze_spawn_egg" = "minecraft:blaze_rod"
    "ghast_spawn_egg" = "minecraft:ghast_tear"
    "magma_cube_spawn_egg" = "minecraft:magma_cream"
    "wither_spawn_egg" = "minecraft:nether_star"
    "piglin_spawn_egg" = "minecraft:gold_ingot"
    "piglin_brute_spawn_egg" = "minecraft:golden_axe"
    "hoglin_spawn_egg" = "minecraft:porkchop"
    "strider_spawn_egg" = "minecraft:warped_fungus"
    
    # Hostile - Illagers
    "witch_spawn_egg" = "minecraft:glass_bottle"
    "vindicator_spawn_egg" = "minecraft:iron_axe"
    "evoker_spawn_egg" = "minecraft:totem_of_undying"
    "pillager_spawn_egg" = "minecraft:crossbow"
    "ravager_spawn_egg" = "minecraft:saddle"
    "vex_spawn_egg" = "minecraft:iron_sword"
    
    # Hostile - Ocean
    "guardian_spawn_egg" = "minecraft:prismarine_shard"
    "elder_guardian_spawn_egg" = "minecraft:prismarine_crystals"
    
    # Hostile - Misc
    "slime_spawn_egg" = "minecraft:slime_ball"
    "warden_spawn_egg" = "minecraft:sculk_catalyst"
    "breeze_spawn_egg" = "minecraft:breeze_rod"
    
    # Passive - Farm Animals
    "cow_spawn_egg" = "minecraft:beef"
    "pig_spawn_egg" = "minecraft:porkchop"
    "sheep_spawn_egg" = "minecraft:white_wool"
    "chicken_spawn_egg" = "minecraft:feather"
    "rabbit_spawn_egg" = "minecraft:rabbit_hide"
    "mooshroom_spawn_egg" = "minecraft:red_mushroom"
    
    # Passive - Tameable
    "wolf_spawn_egg" = "minecraft:bone"
    "cat_spawn_egg" = "minecraft:cod"
    "ocelot_spawn_egg" = "minecraft:salmon"
    "parrot_spawn_egg" = "minecraft:cookie"
    "fox_spawn_egg" = "minecraft:sweet_berries"
    
    # Passive - Farm/Utility
    "horse_spawn_egg" = "minecraft:saddle"
    "donkey_spawn_egg" = "minecraft:chest"
    "mule_spawn_egg" = "minecraft:leather"
    "skeleton_horse_spawn_egg" = "minecraft:bone"
    "zombie_horse_spawn_egg" = "minecraft:rotten_flesh"
    "llama_spawn_egg" = "minecraft:white_carpet"
    "trader_llama_spawn_egg" = "minecraft:lead"
    
    # Passive - Misc
    "bat_spawn_egg" = "minecraft:coal"
    "panda_spawn_egg" = "minecraft:bamboo"
    "polar_bear_spawn_egg" = "minecraft:cod"
    "dolphin_spawn_egg" = "minecraft:cod"
    "squid_spawn_egg" = "minecraft:ink_sac"
    "glow_squid_spawn_egg" = "minecraft:glow_ink_sac"
    "turtle_spawn_egg" = "minecraft:seagrass"
    "goat_spawn_egg" = "minecraft:wheat"
    "frog_spawn_egg" = "minecraft:slime_ball"
    "tadpole_spawn_egg" = "minecraft:slime_ball"
    "axolotl_spawn_egg" = "minecraft:tropical_fish_bucket"
    "camel_spawn_egg" = "minecraft:cactus"
    "sniffer_spawn_egg" = "minecraft:torchflower_seeds"
    "armadillo_spawn_egg" = "minecraft:spider_eye"
    "allay_spawn_egg" = "minecraft:amethyst_shard"
    
    # Golems
    "iron_golem_spawn_egg" = "minecraft:iron_block"
    "snow_golem_spawn_egg" = "minecraft:snow_block"
    
    # Villagers
    "villager_spawn_egg" = "minecraft:emerald"
    "wandering_trader_spawn_egg" = "minecraft:emerald"
}

$recipeDir = "c:\Users\xiaobao\Documents\MyFirstMod\src\main\resources\data\opitems\recipe"
New-Item -ItemType Directory -Force -Path $recipeDir | Out-Null

$advancementItems = @()

foreach ($egg in $eggMaterials.Keys) {
    $material = $eggMaterials[$egg]
    $eggId = "minecraft:" + $egg
    
    # Recipe JSON
    $recipe = @{
        type = "minecraft:crafting_shaped"
        pattern = @("MMM", "MEM", "MMM")
        key = @{
            M = $material
            E = "minecraft:egg"
        }
        result = @{
            id = $eggId
            count = 1
        }
    }
    
    $recipePath = Join-Path $recipeDir ($egg + "_recipe.json")
    $recipe | ConvertTo-Json -Depth 4 | Out-File -FilePath $recipePath -Encoding UTF8 -NoNewline
    
    $advancementItems += $eggId
}

# Write advancement items list for reference
$advancementItems | ConvertTo-Json | Out-File -FilePath "c:\Users\xiaobao\Documents\MyFirstMod\.tmp_tags\egg_list.json" -Encoding UTF8

Write-Output "Generated $($eggMaterials.Count) spawn egg recipes"
Write-Output "Advancement items: $($advancementItems.Count)"
