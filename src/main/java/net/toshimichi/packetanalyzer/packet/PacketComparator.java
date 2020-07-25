package net.toshimichi.packetanalyzer.packet;

import java.util.Comparator;

public class PacketComparator implements Comparator<PacketDetail> {
    @Override
    public int compare(PacketDetail o1, PacketDetail o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
