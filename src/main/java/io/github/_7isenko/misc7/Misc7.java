package io.github._7isenko.misc7;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Misc7 extends JavaPlugin {
    // How to build: Maven/Misc7/Lifecycle/package
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // this.getCommand("template_command").setExecutor(new SpigotCommandExecutor());
        getServer().getPluginManager().registerEvents(new DoorClickListener(), plugin);
        getServer().getPluginManager().registerEvents(new LilyFallListener(), plugin);
        getServer().getPluginManager().registerEvents(new SignChestOpenListener(), plugin);
    }

    @Override
    public void onDisable() {
    }
}