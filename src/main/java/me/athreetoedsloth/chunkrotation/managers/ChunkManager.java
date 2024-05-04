package me.athreetoedsloth.chunkrotation.managers;

import me.athreetoedsloth.chunkrotation.ChunkRotation;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class ChunkManager {

    ChunkRotation plugin;
    public ChunkManager(ChunkRotation plugin){
        this.plugin = plugin;
    }
    private Material[][][] blocksCentre = new Material[16][320][16];
    private Material[][][] blocksCentre2 = new Material[16][320][16];
    int x, z, _x, _z;

    public void rotate(Player p, int degrees){
        //Define chunks.
        Chunk chunk1 = p.getChunk();
        Chunk chunkCentre = p.getWorld().getChunkAt(new Location(p.getWorld(), 1, 0, 0));
        Chunk chunkCentre2;
        switch (degrees){
            case 90:
                chunkCentre2 = p.getWorld().getChunkAt(new Location(p.getWorld(), -1, 0, 0));
                break;
            case 180:
                chunkCentre2 = p.getWorld().getChunkAt(new Location(p.getWorld(), -1, 0, -1));
                break;
            default:
                chunkCentre2 = p.getWorld().getChunkAt(new Location(p.getWorld(), 0, 0, -1));
                break;
        }

        //Create backup of centre blocks and replace centre with chunk1 blocks.
        for(int i = 0; i < 16 ; i++){
            for(int j = 0; j < 320; j++){
                for(int k = 0; k < 16; k++){
                    blocksCentre[i][j][k] = chunkCentre.getBlock(i,j,k).getType();
                    blocksCentre2[i][j][k] = chunkCentre2.getBlock(i,j,k).getType();
                    chunkCentre.getBlock(i,j,k).setType(chunk1.getBlock(i,j,k).getType());
                }
            }
        }

        rotate(chunkCentre, p, degrees);

        //Move chunk1 back to original position and replace centre with original.
        for(int i = 0; i < 16 ; i++){
            for(int j = 0; j < 320; j++){
                for(int k = 0; k < 16; k++){
                    chunk1.getBlock(i,j,k).setType(chunkCentre2.getBlock(i,j,k).getType());
                    chunkCentre.getBlock(i,j,k).setType(blocksCentre[i][j][k]);
                    chunkCentre2.getBlock(i,j,k).setType(blocksCentre2[i][j][k]);
                }
            }
        }
    }

    private void rotate(Chunk chunkCentre, Player p, int degrees){
        for(int i = 0; i < 16 ; i++){
            for(int j = 0; j < 320; j++){
                for(int k = 0; k < 16; k++){
                    x = chunkCentre.getBlock(i,j,k).getLocation().getBlockX();
                    z = chunkCentre.getBlock(i,j,k).getLocation().getBlockZ();
                    switch (degrees){
                        case 90:
                            _x = -z;
                            _z = x;
                            new Location(p.getWorld(), _x-1, j, _z).getBlock().setType(chunkCentre.getBlock(i,j,k).getType());
                            break;
                        case 180:
                            _x = -x;
                            _z = -z;
                            new Location(p.getWorld(), _x-1, j, _z-1).getBlock().setType(chunkCentre.getBlock(i,j,k).getType());
                            break;
                        case 270:
                            _x = z;
                            _z = -x;
                            new Location(p.getWorld(), _x, j, _z-1).getBlock().setType(chunkCentre.getBlock(i,j,k).getType());
                            break;
                    }
                }
            }
        }
    }
}
