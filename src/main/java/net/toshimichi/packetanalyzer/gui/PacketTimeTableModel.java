package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.packet.PacketComparator;
import net.toshimichi.packetanalyzer.packet.PacketDetail;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PacketTimeTableModel extends AbstractTableModel {

    private final ArrayList<PacketDetail> packets;
    private final SimpleDateFormat formatter;

    public PacketTimeTableModel(Collection<PacketDetail> collection) {
        packets = new ArrayList<>(collection);
        packets.sort(new PacketComparator());
        Collections.reverse(packets);
        formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    }

    @Override
    public int getRowCount() {
        return packets.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "パケット";
            case 1:
                return "送信先";
            case 2:
                return "日時";
            default:
                return null;
        }
    }

    public PacketDetail getValueAt(int rowIndex) {
        if(packets.size() <= rowIndex) return null;
        return packets.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PacketDetail packet = getValueAt(rowIndex);
        if(packet == null) return null;
        switch (columnIndex) {
            case 0:
                return packet.getPacket().getClass().getSimpleName();
            case 1:
                return packet.getBound();
            case 2:
                return formatter.format(packet.getDate());
            default:
                return null;
        }
    }
}
