package Lincey.NeverMind.console;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
    public static FileConfiguration config;
    public static File log;
    public static Plugin plugin;

    public static String getPrefix() {
        return config.getString("Messages.Prefix").replace("&", "§");
    }

    public void onEnable() {
        long startTime = System.currentTimeMillis();
        File configs = new File(this.getDataFolder() + File.separator + "config.yml");
        if (!configs.exists()) {
            this.getConfig().options().copyDefaults(true);
            this.saveDefaultConfig();
        }

        (new File(this.getDataFolder().getAbsolutePath())).mkdirs();
        log = new File(this.getDataFolder().getAbsolutePath() + File.separator + "log.txt");
        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException var5) {
                Bukkit.getLogger().info("§c|ERROR|§f - " + var5.toString());
            }
        }

        config = this.getConfig();
        plugin = this;
        this.getCommand("console").setExecutor(new Main_CMD());
        this.getLogger().info(" ");
        this.getLogger().info("   NCC Loaded");
        this.getLogger().info("   Dev" + ":" + " Lincey");
        this.getLogger().info(" ");
    }
}
