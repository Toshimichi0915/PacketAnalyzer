package net.toshimichi.packetanalyzer.packet;

import java.util.Date;
import java.util.UUID;

public class PacketDetail {
    private final Object packet;
    private final PacketBound bound;
    private final Date date;

    public PacketDetail(Object packet, PacketBound bound, Date date) {
        this.packet = packet;
        this.bound = bound;
        this.date = date;
    }

    public Object getPacket() {
        return packet;
    }

    public PacketBound getBound() {
        return bound;
    }

    public Date getDate() {
        return date;
    }
}
