package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.Main;
import net.toshimichi.packetanalyzer.lang.Language;
import net.toshimichi.packetanalyzer.packet.PacketDetail;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MonitorTableModel extends AbstractTableModel {

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
        Language lang = Main.getLanguage();
        switch (column) {
            case 0:
                return lang.get("packet");
            case 1:
                return lang.get("times");
            case 2:
                return lang.get("bound");
            default:
                return null;
        }
    }

    public List<PacketDetail> getValueAt(int rowIndex) {
        if (packets.size() <= rowIndex) return new ArrayList<>();
        else return packets.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<PacketDetail> list = getValueAt(rowIndex);
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
