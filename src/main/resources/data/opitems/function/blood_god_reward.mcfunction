############################################
# BLOOD GOD REWARD - Technoblade + ItzRealMe
############################################

# ==========================================
# 1. CROWN OF THE BLOOD GOD
# ==========================================
give @s netherite_helmet{
  display:{
    Name:'{"text":"👑 Crown of the Blood God","color":"gold","bold":true,"italic":false}',
    Lore:[
      '{"text":"","color":"white"}',
      '{"text":"Technoblade never dies.","color":"red","italic":false}',
      '{"text":"The quieter you become, the more you can hear.","color":"dark_purple","italic":false}',
      '{"text":"","color":"white"}',
      '{"text":"☠ Grants immunity to knockback","color":"gray","italic":false}',
      '{"text":"☠ +50% damage resistance","color":"gray","italic":false}'
    ]
  },
  Enchantments:[
    {id:"minecraft:protection",lvl:10},
    {id:"minecraft:unbreaking",lvl:10},
    {id:"minecraft:mending",lvl:1},
    {id:"minecraft:respiration",lvl:5},
    {id:"minecraft:aqua_affinity",lvl:1},
    {id:"minecraft:thorns",lvl:5}
  ],
  Unbreakable:1,
  RepairCost:0,
  HideFlags:2
}

# ==========================================
# 2. SWORD OF THE BLOOD GOD
# ==========================================
give @s netherite_sword{
  display:{
    Name:'{"text":"⚔️ Sword of the Blood God","color":"red","bold":true,"italic":false}',
    Lore:[
      '{"text":"","color":"white"}',
      '{"text":"Forged in potato blood.","color":"gold","italic":false}',
      '{"text":"Wielded by legends.","color":"gold","italic":false}',
      '{"text":"","color":"white"}',
      '{"text":"☠ Deals +50 damage","color":"gray","italic":false}',
      '{"text":"☠ Sets enemies on fire","color":"gray","italic":false}',
      '{"text":"☠ Steals enemy health","color":"gray","italic":false}'
    ]
  },
  Enchantments:[
    {id:"minecraft:sharpness",lvl:10},
    {id:"minecraft:fire_aspect",lvl:5},
    {id:"minecraft:looting",lvl:5},
    {id:"minecraft:knockback",lvl:3},
    {id:"minecraft:unbreaking",lvl:10},
    {id:"minecraft:mending",lvl:1}
  ],
  Unbreakable:1,
  RepairCost:0,
  HideFlags:2,
  AttributeModifiers:[
    {
      AttributeName:"minecraft:generic.attack_damage",
      Name:"generic.attack_damage",
      Amount:50,
      Operation:0,
      UUID:[I;12345678,12345678,12345678,12345678],
      Slot:"mainhand"
    },
    {
      AttributeName:"minecraft:generic.attack_speed",
      Name:"generic.attack_speed",
      Amount:2.0,
      Operation:0,
      UUID:[I;87654321,87654321,87654321,87654321],
      Slot:"mainhand"
    }
  ]
}

# ==========================================
# 3. TECHNO'S SACRED POTATO
# ==========================================
give @s potato{
  Count:64,
  display:{
    Name:'{"text":"🥔 Techno\'s Sacred Potato","color":"gold","bold":true,"italic":false}',
    Lore:[
      '{"text":"","color":"white"}',
      '{"text":"Farmed in the great potato war.","color":"dark_red","italic":false}',
      '{"text":"Eat this to honor the Blood God.","color":"dark_red","italic":false}',
      '{"text":"","color":"white"}',
      '{"text":"☠ Restores all hunger + saturation","color":"gray","italic":false}',
      '{"text":"☠ Grants 30 seconds of strength","color":"gray","italic":false}'
    ]
  },
  AttributeModifiers:[
    {
      AttributeName:"minecraft:generic.max_health",
      Name:"generic.max_health",
      Amount:20,
      Operation:0,
      UUID:[I;11111111,11111111,11111111,11111111],
      Slot:"offhand"
    }
  ]
}

# ==========================================
# 4. THE ZONE - PERMANENT BUFFS (10 min)
# ==========================================
effect give @s minecraft:strength 600 3 true
effect give @s minecraft:resistance 600 2 true
effect give @s minecraft:speed 600 2 true
effect give @s minecraft:haste 600 3 true
effect give @s minecraft:jump_boost 600 2 true
effect give @s minecraft:night_vision 600 1 true
effect give @s minecraft:regeneration 600 2 true
effect give @s minecraft:absorption 600 10 true
effect give @s minecraft:fire_resistance 600 1 true
effect give @s minecraft:water_breathing 600 1 true
effect give @s minecraft:health_boost 600 10 true

# ==========================================
# 5. BLOOD GOD TITLE - CHAT & BOSS BAR
# ==========================================
# Boss bar announcement
bossbar add technoblade_announce "👑 THE BLOOD GOD HAS RISEN 👑"
bossbar set technoblade_announce players @a
bossbar set technoblade_announce visible true
bossbar set technoblade_announce color purple
bossbar set technoblade_announce style progress
bossbar set technoblade_announce value 100
schedule function technoblade:remove_bossbar 10s

# Chat announcement
tellraw @a [
  {"text":"\n═══════════════════════════════════════\n","color":"dark_purple"},
  {"text":"  👑 THE BLOOD GOD HAS RISEN 👑  \n","color":"gold","bold":true},
  {"text":"═══════════════════════════════════════\n","color":"dark_purple"},
  {"text":"  ","color":"white"},
  {"text":"@p","color":"yellow","clickEvent":{"action":"suggest_command","value":"/msg @p "}},
  {"text":" has completed the ultimate trial.\n","color":"white"},
  {"text":"  ","color":"white"},
  {"text":"Technoblade never dies.","color":"red","bold":true},
  {"text":"\n  ","color":"white"},
  {"text":"The quieter you become, the more you can hear.","color":"dark_purple","italic":true},
  {"text":"\n═══════════════════════════════════════\n","color":"dark_purple"}
]

# ==========================================
# 6. EPIC SOUND EFFECTS
# ==========================================
playsound minecraft:entity.wither.spawn master @s ~ ~ ~ 1 0.5
playsound minecraft:entity.ender_dragon.death master @s ~ ~ ~ 1 1
playsound minecraft:entity.lightning_bolt.thunder master @s ~ ~ ~ 1 1
playsound minecraft:ui.toast.challenge_complete master @s ~ ~ ~ 1 1
playsound minecraft:entity.enderman.scream master @a ~ ~ ~ 1 0.8

# ==========================================
# 7. PARTICLE EFFECTS - EPIC ENTRANCE
# ==========================================
particle minecraft:end_rod ~ ~1 ~ 2 2 2 0 200 force @a
particle minecraft:flash ~ ~1 ~ 0 0 0 0 1 force @a
particle minecraft:firework ~ ~1 ~ 3 3 3 0 100 force @a
particle minecraft:dragon_breath ~ ~1 ~ 2 2 2 0 50 force @a
particle minecraft:soul_fire_flame ~ ~1 ~ 1 1 1 0 50 force @a
particle minecraft:totem_of_undying ~ ~1 ~ 2 2 2 0 50 force @a

# ==========================================
# 8. SCOREBOARD - PERMANENT BRAG
# ==========================================
scoreboard objectives add BloodGod "Blood God"
scoreboard players set @s BloodGod 1
scoreboard objectives setdisplay sidebar BloodGod

# ==========================================
# 9. TITLE ANIMATION
# ==========================================
title @s title {"text":"TECHNOBLADE","color":"red","bold":true}
title @s subtitle {"text":"NEVER DIES","color":"gold","bold":true}

# ==========================================
# 10. SECRET - NAME TAG DROP
# ==========================================
give @s name_tag{
  display:{
    Name:'{"text":"🐷 Technoblade","color":"red","bold":true}',
    Lore:[
      '{"text":"","color":"white"}',
      '{"text":"Place this on a pig.","color":"gray","italic":false}',
      '{"text":"He will never die.","color":"gray","italic":false}'
    ]
  }
} 1

# ==========================================
# 11. FINAL TOAST
# ==========================================
tellraw @s [
  {"text":"\n§8[§6§l!§8] §6You have proven yourself worthy.\n","color":"gold"},
  {"text":"§8[§6§l!§8] §cThe Blood God smiles upon you.\n","color":"red"},
  {"text":"§8[§6§l!§8] §dThe quieter you become, the more you can hear.\n","color":"dark_purple"},
  {"text":"§8[§6§l!§8] §a+1000 XP\n","color":"green"}
]

# ==========================================
# 12. BONUS - SAFETY SYSTEM
# ==========================================
# These keep you alive like Techno
attribute @s minecraft:generic.max_health base set 40
attribute @s minecraft:generic.knockback_resistance base set 1
attribute @s minecraft:generic.movement_speed base set 0.15

# ==========================================
# 13. SUICIDE CANCELLATION (Techno never dies)
# ==========================================
gamerule keepInventory true