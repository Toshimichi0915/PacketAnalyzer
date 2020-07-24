package net.toshimichi.packetanalyzer.gui;

import java.util.Date;

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
