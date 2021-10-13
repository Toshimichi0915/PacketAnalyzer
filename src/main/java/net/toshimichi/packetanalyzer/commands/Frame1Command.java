package net.toshimichi.packetanalyzer.commands;

import net.toshimichi.packetanalyzer.Main;
import net.toshimichi.packetanalyzer.events.PacketReceiveEvent;
import net.toshimichi.packetanalyzer.events.PacketSendEvent;
import net.toshimichi.packetanalyzer.gui.MonitorFrame1;
import net.toshimichi.packetanalyzer.gui.PacketListener;
import net.toshimichi.packetanalyzer.packet.PacketBound;
import net.toshimichi.packetanalyzer.packet.PacketDetail;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

import javax.swing.SwingUtilities;
import java.awt.Frame;
import java.awt.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Frame1Command implements CommandExecutor, Listener {

    private final Set<PacketListener> listeners = Collections.synchronizedSet(new HashSet<>());

    public Frame1Command() {
        Bukkit.getPluginManager().registerEvents(new Listener() {

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
                for (PacketListener listener : listeners) {
                    SwingUtilities.invokeLater(listener.getFrame()::dispose);
                }
            }

        }, Main.getPlugin());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        SwingUtilities.invokeLater(() -> {
            PacketListener cl = newClientPacketListener();
            PacketListener sl = newServerPacketListener();
            Frame client = cl.getFrame();
            Frame server = sl.getFrame();
            client.setLocationRelativeTo(null);
            Point point = client.getLocation();
            server.setLocation((int) point.getX() + 30, (int) point.getY() + 30);
            client.setVisible(true);
            server.setVisible(true);
            listeners.add(cl);
            listeners.add(sl);
        });
        return true;
    }

    protected PacketListener newClientPacketListener() {
        return new MonitorFrame1(PacketBound.CLIENT);
    }

    protected PacketListener newServerPacketListener() {
        return new MonitorFrame1(PacketBound.SERVER);
    }

    protected void addPacket(PacketDetail p) {
        listeners.removeIf(listener -> !listener.getFrame().isDisplayable());

        for (PacketListener listener : listeners) {
            SwingUtilities.invokeLater(() -> listener.addPacket(p));
        }
    }
}
