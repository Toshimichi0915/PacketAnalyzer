package net.toshimichi.packetanalyzer.commands;

import net.toshimichi.packetanalyzer.Main;
import net.toshimichi.packetanalyzer.events.PacketReceiveEvent;
import net.toshimichi.packetanalyzer.events.PacketSendEvent;
import net.toshimichi.packetanalyzer.gui.PacketBound;
import net.toshimichi.packetanalyzer.gui.PacketDetail;
import net.toshimichi.packetanalyzer.gui.PacketFrame;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FrameShowCommand implements CommandExecutor, Listener {

    private final Set<PacketFrame> frames = Collections.synchronizedSet(new HashSet<>());

    public FrameShowCommand() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        SwingUtilities.invokeLater(() -> {
            PacketFrame client = new PacketFrame(PacketBound.CLIENT);
            PacketFrame server = new PacketFrame(PacketBound.SERVER);
            client.setLocationRelativeTo(null);
            Point point = client.getLocation();
            server.setLocation((int) point.getX() + 30, (int) point.getY() + 30);
            client.setVisible(true);
            server.setVisible(true);
            frames.add(client);
            frames.add(server);
        });
        return true;
    }

    private void addPacket(PacketDetail p) {
        frames.removeIf(frame -> !frame.isDisplayable());

        for (PacketFrame frame : frames) {
            SwingUtilities.invokeLater(() -> frame.addPacket(p));
        }
    }

    @EventHandler
    public void onPacketReceived(PacketReceiveEvent e) {
        addPacket(e.getPacket());
    }

    @EventHandler
    public void onPacketSent(PacketSendEvent e) {
        addPacket(e.getPacket());
    }

    @EventHandler
    public void onDisable(PluginDisableEvent e) {
        if (!e.getPlugin().equals(Main.getPlugin())) return;
        for (PacketFrame frame : frames) {
            SwingUtilities.invokeLater(frame::dispose);
        }
    }
}
