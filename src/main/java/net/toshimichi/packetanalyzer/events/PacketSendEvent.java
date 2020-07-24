package net.toshimichi.packetanalyzer.events;

import net.toshimichi.packetanalyzer.gui.PacketBound;
import net.toshimichi.packetanalyzer.gui.PacketDetail;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.Date;

public class PacketSendEvent extends PacketEvent {

    private static final HandlerList handlerList = new HandlerList();

    public PacketSendEvent(Player player, Object packet) {
        super(player, new PacketDetail(packet, PacketBound.CLIENT, new Date()));
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
