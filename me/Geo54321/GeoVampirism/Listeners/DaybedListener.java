package me.Geo54321.GeoVampirism.Listeners;

import me.Geo54321.GeoVampirism.Utility.FileWorker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class DaybedListener implements Listener {
    private FileWorker allNames;
    private int startBurn, stopBurn;

    public DaybedListener(FileWorker VampireList, int startBurn, int stopBurn) {
        allNames = VampireList;
        this.startBurn = startBurn;
        this.stopBurn = stopBurn;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (isVampire(player)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getClickedBlock().getType().equals(Material.BLACK_BED)) {
                    if (isDaytime(player)) {
                        setNight(player);
                    }
                }
            }
        }
    }

    private boolean isVampire(Player player) {
        for(String name : allNames.getData()) {
            if(player.getDisplayName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDaytime(Player player) {
        if(player.getWorld().getTime() > startBurn || player.getWorld().getTime() < stopBurn) {
            return true;
        }
        return false;
    }

    public void setNight(Player player) {
        player.getWorld().setTime(stopBurn+1);
        Bukkit.broadcastMessage("§4" + player.getDisplayName() + "§6 has skipped the day.");
    }
}
