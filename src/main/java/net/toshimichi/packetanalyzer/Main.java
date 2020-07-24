package net.toshimichi.packetanalyzer;

import net.toshimichi.packetanalyzer.commands.FrameShowCommand;
import net.toshimichi.packetanalyzer.commands.MonitorToggleCommand;
import net.toshimichi.packetanalyzer.utils.NativeNettyUtils;
import net.toshimichi.packetanalyzer.utils.NettyUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;

public class Main extends JavaPlugin {

    private static final NativeNettyUtils nettyUtils = new NativeNettyUtils();
    private static Main plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    public static NettyUtils getNettyUtils() {
        return nettyUtils;
    }

    @Override
    public void onEnable() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        plugin = this;
        getCommand("frame").setExecutor(new FrameShowCommand());
        getCommand("monitor").setExecutor(new MonitorToggleCommand());
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            nettyUtils.extract(p, "packet_monitor");
        }
    }
}
