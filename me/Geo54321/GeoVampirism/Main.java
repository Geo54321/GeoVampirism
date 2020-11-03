package me.Geo54321.GeoVampirism;

import me.Geo54321.GeoVampirism.Commands.Vampirism;
import me.Geo54321.GeoVampirism.Utility.FileWorker;
import me.Geo54321.GeoVampirism.Listeners.DaybedListener;
import me.Geo54321.GeoVampirism.Listeners.SunListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private FileConfiguration config = getConfig();
    private FileWorker VampireList = new FileWorker(this, "VampireList");

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.loadDefaultConfigFile();
        VampireList.loadFile();

        if(getConfig().getBoolean("modules.vampirism")) {
            getServer().getPluginManager().registerEvents(new SunListener(VampireList, getConfig().getInt("options.burn-seconds"), getConfig().getInt("options.debuff-seconds"), getConfig().getInt("options.start-burn-time"), getConfig().getInt("options.stop-burn-time"), getConfig().getBoolean("options.vampire-buffs")), this);
        }
        if(getConfig().getBoolean("modules.antibed")) {
            getServer().getPluginManager().registerEvents(new DaybedListener(VampireList, getConfig().getInt("options.start-burn-time"), getConfig().getInt("options.stop-burn-time")), this);
        }
        this.getCommand("vampirism").setExecutor(new Vampirism(VampireList));
    }

    @Override
    public void onDisable() {

    }

    private void loadDefaultConfigFile() {
        config.options().header("Geo's Vampirism Plugin");
        config.addDefault("options.burn-seconds",10);
        config.addDefault("options.debuff-seconds",30);
        config.addDefault("options.vampire-buffs",true);
        config.addDefault("options.start-burn-time",23500);
        config.addDefault("options.stop-burn-time",12500);

        config.addDefault("modules.vampirism", true);
        config.addDefault("modules.antibed", true);
        config.options().copyDefaults(true);
        saveConfig();
        reloadConfig();
    }
}
