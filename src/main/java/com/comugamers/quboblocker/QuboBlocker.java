package com.comugamers.quboblocker;

import com.comugamers.quboblocker.file.YamlFile;
import com.comugamers.quboblocker.service.Service;
import com.comugamers.quboblocker.service.main.MainService;
import org.bukkit.plugin.java.JavaPlugin;

public class QuboBlocker extends JavaPlugin {

    private Service service;
    private YamlFile config;

    @Override
    public void onEnable() {
        service = new MainService(this);
        service.start();

        // Plugin is now enabled, send a message about it
        getLogger().info("QuboBlocker is now enabled and looking for connections!");
    }

    @Override
    public void onDisable() {
        service.stop();

        // Plugin is now disabled, send a message about it
        getLogger().info("QuboBlocker is now disabled!");
    }

    @Override
    public YamlFile getConfig() {
        return config;
    }

    public void setConfig(YamlFile config) {
        this.config = config;
    }
}
