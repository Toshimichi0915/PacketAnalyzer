package net.toshimichi.packetanalyzer.events;

import net.toshimichi.packetanalyzer.gui.PacketDetail;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PacketEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();
    private final Player player;
    private final PacketDetail packet;

    public PacketEvent(Player player, PacketDetail packet) {
        super(true);
        this.player = player;
        this.packet = packet;
    }

    public Player getPlayer() {
        return player;
    }

    public PacketDetail getPacket() {
        return packet;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
