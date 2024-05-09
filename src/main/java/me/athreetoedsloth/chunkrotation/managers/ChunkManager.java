package me.athreetoedsloth.chunkrotation.managers;

import me.athreetoedsloth.chunkrotation.ChunkRotation;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class ChunkManager {

    ChunkRotation plugin;
    public ChunkManager(ChunkRotation plugin){
        this.plugin = plugin;
    }
    private Material[][][] blocks = new Material[16][384][16];
    int x, z, _x, _z;

    public void rotate(Player p, int degrees){
        //Define chunks.
        Chunk chunk = p.getChunk();
        int chunkX = chunk.getX() * 16 + 8;
        int chunkZ = chunk.getZ() * 16 + 8;

        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 384; j++){
                for(int k = 0; k < 16; k++){
                    blocks[i][j][k] = chunk.getBlock(i,j-64,k).getType();
                }
            }
        }

        for(int i = 0; i < 16 ; i++){
            for(int j = 0; j < 384; j++){
                for(int k = 0; k < 16; k++){
                    x = chunk.getBlock(i,j-64,k).getLocation().getBlockX() - chunkX;
                    z = chunk.getBlock(i,j-64,k).getLocation().getBlockZ() - chunkZ;

                    switch (degrees){
                        case 90:
                            _x = -z;
                            _z = x;
                            new Location(p.getWorld(), _x + chunkX-1, j-64, _z + chunkZ).getBlock().setType(blocks[i][j][k]);
                            break;
                        case 180:
                            _x = -x;
                            _z = -z;
                            new Location(p.getWorld(), _x + chunkX-1, j-64, _z + chunkZ-1).getBlock().setType(blocks[i][j][k]);
                            break;
                        default:
                            _x = z;
                            _z = -x;
                            new Location(p.getWorld(), _x + chunkX, j-64, _z + chunkZ-1).getBlock().setType(blocks[i][j][k]);
                            break;
                    }
                }
            }
        }
    }
}
