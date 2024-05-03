package me.athreetoedsloth.chunkrotation;

import me.athreetoedsloth.chunkrotation.commands.ReplaceChunkCommand;
import me.athreetoedsloth.chunkrotation.managers.ChunkManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChunkRotation extends JavaPlugin {

    public ChunkManager chunkManager = new ChunkManager(this);

    @Override
    public void onEnable() {
        this.getCommand("rotate").setExecutor(new ReplaceChunkCommand(this));
    }

    @Override
    public void onDisable() {
    }
}
