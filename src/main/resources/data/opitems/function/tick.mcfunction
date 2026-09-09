execute as @a store result score @s temp_health run data get entity @s Health

execute as @a at @s if score @s temp_health matches ..6 if block ~ ~ ~ minecraft:water if entity @e[type=minecraft:warden,distance=..10] if entity @e[type=minecraft:axolotl,distance=..10] run advancement grant @s only opitems:abyssal_guardian