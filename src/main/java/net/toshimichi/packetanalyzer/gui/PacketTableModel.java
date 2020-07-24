package net.toshimichi.packetanalyzer.gui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PacketTableModel extends AbstractTableModel {

    private final ArrayList<ArrayList<PacketDetail>> packets = new ArrayList<>();

    private String getClassName(Object packet) {
        return packet.getClass().getSimpleName();
    }

    public void addPacket(PacketDetail packet) {
        for (ArrayList<PacketDetail> list : packets) {
            if (!getClassName(list.get(0).getPacket()).equals(getClassName(packet.getPacket()))) continue;
            list.add(packet);
            fireTableCellUpdated(packets.indexOf(list), 1);
            return;
        }
        ArrayList<PacketDetail> list = new ArrayList<>();
        list.add(packet);
        packets.add(list);
        int index = packets.indexOf(list);
        fireTableRowsInserted(index, index);
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
                return "パケット名";
            case 1:
                return "転送回数";
            case 2:
                return "受信先";
            default:
                return null;
        }
    }

    private ArrayList<PacketDetail> getValueAt(int rowIndex) {
        if (packets.size() <= rowIndex) return new ArrayList<>();
        else return packets.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ArrayList<PacketDetail> list = getValueAt(rowIndex);
        PacketDetail example = list.get(0);
        switch (columnIndex) {
            case 0:
                return getClassName(example.getPacket());
            case 1:
                return list.size();
            case 2:
                return example.getBound();
            default:
                return null;
        }
    }
}
