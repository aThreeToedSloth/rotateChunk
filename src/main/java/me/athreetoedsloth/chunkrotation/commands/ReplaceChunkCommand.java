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
        if(sender instanceof Player && strings.length == 1){
            try {
                int degrees = Integer.parseInt(strings[0]);
                if(degrees == 90 || degrees ==180 || degrees ==270){
                    Player p = (Player) sender;
                    plugin.chunkManager.rotate(p, Integer.parseInt(strings[0]));
                }
                else {
                    sender.sendMessage("You can only rotate the chunk by 90, 180 or 270 degrees.");
                }
            } catch (NumberFormatException n){
                sender.sendMessage("Please provide an integer to determine how many degrees the chunk should be rotated by.");
            }
            return true;
        }
    return false;
    }
}
