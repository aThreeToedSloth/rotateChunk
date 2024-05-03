package me.athreetoedsloth.chunkrotation.commands;

import me.athreetoedsloth.chunkrotation.ChunkRotation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReplaceChunkCommand implements CommandExecutor {

    ChunkRotation plugin;

    public ReplaceChunkCommand(ChunkRotation plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            plugin.chunkManager.rotate(p);
            return true;
        }
    return false;
    }
}
