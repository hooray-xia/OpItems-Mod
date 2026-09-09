package name.modid;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;

public class EnderSpear extends Item {
    
    private static final int COOLDOWN_TICKS = 100; // 12 seconds
    private static final double TELEPORT_RANGE = 22.0; // Max teleport distance

    public EnderSpear(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        // Play teleport sound
        level.playSound(
            null,
            player.getX(),
            player.getY(),
            player.getZ(),
            SoundEvents.CHORUS_FRUIT_TELEPORT,
            SoundSource.PLAYERS,
            1.0F,
            1.0F
        );

        if (level instanceof ServerLevel serverLevel) {
            // Get where the player is looking
            Vec3 look = player.getLookAngle();
            Vec3 startPos = player.position();
            
            // Calculate teleport destination (up to 32 blocks in the direction the player is looking)
            Vec3 destination = findSafeDestination(serverLevel, player, startPos, look);
            
            // If no safe destination found, find the furthest safe point along the path
            if (destination == null) {
                destination = findFurthestSafePoint(serverLevel, player, startPos, look);
            }
            
            // Only teleport if we found a safe destination
            if (destination != null) {
                // Teleport the player
                player.teleportTo(
                    destination.x,
                    destination.y,
                    destination.z
                );
                
                // Play teleport particles
                serverLevel.sendParticles(
                    net.minecraft.core.particles.ParticleTypes.PORTAL,
                    destination.x,
                    destination.y + 1,
                    destination.z,
                    150,
                    0.5,
                    0.5,
                    0.5,
                    0.1
                );
            } else {
                // If no safe spot found, don't teleport and play a fail sound
                level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.ZOMBIE_ATTACK_IRON_DOOR,
                    SoundSource.PLAYERS,
                    0.5F,
                    0.5F
                );
                return InteractionResult.FAIL;
            }
        }
        
        // Add cooldown
        player.getCooldowns().addCooldown(itemStack, COOLDOWN_TICKS);
        player.awardStat(Stats.ITEM_USED.get(this));
        
        return InteractionResult.SUCCESS;
    }
    
    private Vec3 findSafeDestination(ServerLevel level, Player player, Vec3 startPos, Vec3 look) {
        // Check destination at full range first
        Vec3 fullDestination = startPos.add(look.x * TELEPORT_RANGE, look.y * TELEPORT_RANGE, look.z * TELEPORT_RANGE);
        if (isSafeTeleportLocation(level, player, fullDestination)) {
            return fullDestination;
        }
        
        // If full range isn't safe, check closer positions
        for (double distance = TELEPORT_RANGE - 1; distance >= 1; distance -= 0.5) {
            Vec3 testDest = startPos.add(look.x * distance, look.y * distance, look.z * distance);
            if (isSafeTeleportLocation(level, player, testDest)) {
                return testDest;
            }
        }
        
        return null; // No safe destination found
    }
    
    private Vec3 findFurthestSafePoint(ServerLevel level, Player player, Vec3 startPos, Vec3 look) {
        // Check each block along the path to find the furthest point that's safe
        for (double distance = TELEPORT_RANGE; distance >= 0; distance -= 0.5) {
            Vec3 testPos = startPos.add(look.x * distance, look.y * distance, look.z * distance);
            if (isSafeTeleportLocation(level, player, testPos)) {
                return testPos;
            }
        }
        return null;
    }
    
    private boolean isSafeTeleportLocation(ServerLevel level, Player player, Vec3 position) {
        // Check if the position is within world bounds
        if (position.x < -30000000 || position.x > 30000000 || 
            position.y < -64 || position.y > 320 || 
            position.z < -30000000 || position.z > 30000000) {
            return false;
        }
        
        // Get the block positions for the player's bounding box
        double width = player.getBbWidth() / 2;
        double height = player.getBbHeight();
        
        // Check if the player's bounding box would be inside any block
        AABB boundingBox = new AABB(
            position.x - width, position.y, position.z - width,
            position.x + width, position.y + height, position.z + width
        );
        
        // Check all blocks in the bounding box
        int minX = (int) Math.floor(boundingBox.minX);
        int maxX = (int) Math.floor(boundingBox.maxX);
        int minY = (int) Math.floor(boundingBox.minY);
        int maxY = (int) Math.floor(boundingBox.maxY);
        int minZ = (int) Math.floor(boundingBox.minZ);
        int maxZ = (int) Math.floor(boundingBox.maxZ);
        
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    BlockState blockState = level.getBlockState(blockPos);
                    
                    // Check if the block is solid (not air, not water, etc.)
                    if (!blockState.isAir() && !blockState.is(Blocks.WATER) && !blockState.is(Blocks.LAVA)) {
                        // Check if the bounding box intersects with the block
                        AABB blockAABB = new AABB(
                            x, y, z,
                            x + 1, y + 1, z + 1
                        );
                        if (boundingBox.intersects(blockAABB)) {
                            return false; // Would be inside a solid block
                        }
                    }
                }
            }
        }
        
        // Also check if the player would fall into the void (if they're below y=0 and there's no block below)
        if (position.y < -60) {
            return false;
        }
        
        return true;
    }
}