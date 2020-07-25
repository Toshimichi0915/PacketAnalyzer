package net.toshimichi.packetanalyzer.events;

import net.toshimichi.packetanalyzer.packet.PacketBound;
import net.toshimichi.packetanalyzer.packet.PacketDetail;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.Date;

public class PacketReceiveEvent extends PacketEvent {

    private static final HandlerList handlerList = new HandlerList();

    public PacketReceiveEvent(Player player, Object packet) {
        super(player, new PacketDetail(packet, PacketBound.SERVER, new Date()));
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
