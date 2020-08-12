package io.github._7isenko.misc7;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Misc7 extends JavaPlugin {
    // How to build: Maven/Misc7/Lifecycle/package
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Lily pads don't break your legs
        getServer().getPluginManager().registerEvents(new LilyFallListener(), plugin);

        // You can easily open chests with sighs or frames on it
        getServer().getPluginManager().registerEvents(new ChestOpener(), plugin);

        // Right click on fully grown crop replants it
        getServer().getPluginManager().registerEvents(new CropsReplant(), plugin);
    }

    @Override
    public void onDisable() {
    }
}