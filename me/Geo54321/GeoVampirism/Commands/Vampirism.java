package me.Geo54321.GeoVampirism.Commands;

import me.Geo54321.GeoVampirism.Utility.FileWorker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vampirism implements CommandExecutor {
    private FileWorker namesList;

    public Vampirism(FileWorker VampireList) {
        namesList = VampireList;
        namesList.loadFile();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if(player.hasPermission("GeoVampirism.core.toggleVampirism")) {
                    toggleVampirism(player);
                }
                else {
                    player.sendMessage("§cYou do not have permission to toggle vampirism.");
                }
            }
            else {
                player.sendMessage("§cThe subcommand you entered does not exist.");
            }
        }
        else {
            listVampires();
        }
        return true;
    }

    private String listVampires() {
        String vampires = "§4Vampires: §c";
        for(String name : namesList.getData()) {
            vampires += name;
            vampires += ", ";
        }
        vampires = vampires.substring(0,vampires.length()-2);
        return vampires;
    }

    private void toggleVampirism(Player player) {
        String name = player.getUniqueId().toString();
        if(listVampires().contains(name)) {
            namesList.removeLine(name);
            namesList.saveFile();
            player.sendMessage("§6Vampirism has been disabled.");
        }
        else {
            namesList.createLine(name);
            namesList.saveFile();
            player.sendMessage("§3Vampirism has been enabled.");
        }
    }
}
