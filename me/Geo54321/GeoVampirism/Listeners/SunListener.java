package me.Geo54321.GeoVampirism.Listeners;

import me.Geo54321.GeoVampirism.Utility.FileWorker;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SunListener implements Listener {
    private FileWorker allNames;
    private int burnTicks, debuffTicks, startBurn, stopBurn;
    private boolean doBuff;
    public SunListener(FileWorker VampireList, int burnSeconds, int debuffSeconds, int startBurn, int stopBurn, boolean doBuff) {
        this.allNames = VampireList;
        this.burnTicks = burnSeconds * 20;
        this.debuffTicks = debuffSeconds * 20;
        this.startBurn = startBurn;
        this.stopBurn = stopBurn;
        this.doBuff = doBuff;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (isVampire(player)) {
            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                if (isWorld(player)) {
                    if (isTime(player)) {
                        if(isClear(player)) {
                            if (isInSunlight(player)) {
                                weaken(player);
                                if (isInDirectLight(player)) {
                                    ignite(player);
                                }
                            }
                        }
                    }
                    else {
                        if(isInSunlight(player)) {
                            buff(player);
                        }
                    }
                }
                else {
                    buff(player);
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

    private boolean isWorld(Player player) {
        if(player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
            return true;
        }
        return false;
    }

    private boolean isTime(Player player) {
        if(player.getWorld().getTime() > startBurn || player.getWorld().getTime() < stopBurn) {
            return true;
        }
        return false;
    }

    private boolean isClear(Player player) {
        if(!player.getWorld().hasStorm() && !player.getWorld().isThundering()) {
            return true;
        }
        return false;
    }

    private boolean isInSunlight(Player player) {
        if(player.getLocation().getBlock().getLightFromSky() > 9) {
            return true;
        }
        return false;
    }

    private boolean isInDirectLight(Player player) {
        if(player.getLocation().getBlock().getLightFromSky() > 14) {
            return true;
        }
        return false;
    }

    private void weaken(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, debuffTicks, 0, true, true ,true), true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, debuffTicks, 0, true, true ,true), true);
    }

    private void ignite(Player player) {
        if(player.getFireTicks() <= 1) {
            player.setFireTicks(burnTicks);
        }
    }

    private void buff(Player player) {
        if(doBuff) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, false, false, false));
        }
    }
}
