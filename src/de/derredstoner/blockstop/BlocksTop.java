package de.derredstoner.blockstop;

import de.derredstoner.blockstop.commands.BlocksTopCommand;
import de.derredstoner.blockstop.handler.UpdateHandler;
import de.derredstoner.blockstop.listeners.InventoryListener;
import de.derredstoner.blockstop.mysql.MySQL;
import jdk.nashorn.internal.ir.Block;
import me.clip.ezblocks.EZBlocks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BlocksTop extends JavaPlugin {

    static BlocksTop instance;

    @Override
    public void onEnable() {
        instance = this;

        createConfig();

        MySQL.connect();

        UpdateHandler.startUpdating();

        getCommand("blockstop").setExecutor(new BlocksTopCommand());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
        MySQL.closeConnection();
    }

    public static BlocksTop getInstance() {
        return instance;
    }

    private FileConfiguration config;
    private static File configf;

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void createConfig() {

        configf = new File(this.getDataFolder(), "config.yml" );

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            this.saveResource( "config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(configf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configf);
        try {
            config.load(configf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
