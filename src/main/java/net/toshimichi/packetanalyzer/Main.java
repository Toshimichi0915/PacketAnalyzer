package net.toshimichi.packetanalyzer;

import net.toshimichi.packetanalyzer.commands.FrameShowCommand;
import net.toshimichi.packetanalyzer.commands.MonitorToggleCommand;
import net.toshimichi.packetanalyzer.utils.NativeNettyUtils;
import net.toshimichi.packetanalyzer.utils.NettyUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public class Main extends JavaPlugin {

    private static final NativeNettyUtils nettyUtils = new NativeNettyUtils();
    private static Main plugin;
    private static final String name = "packet_monitor";

    public static Plugin getPlugin() {
        return plugin;
    }

    public static NettyUtils getNettyUtils() {
        return nettyUtils;
    }

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("frame").setExecutor(new FrameShowCommand());
        getCommand("monitor").setExecutor(new MonitorToggleCommand());
    }

    @Override
    public void onDisable() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            nettyUtils.extract(p, "monitor");
        }
    }
}
