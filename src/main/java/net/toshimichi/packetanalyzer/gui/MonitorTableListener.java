package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.packet.PacketDetail;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MonitorTableListener extends MouseAdapter {

    private final MonitorTable table;

    public MonitorTableListener(MonitorTable table) {
        this.table = table;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        MonitorTableModel model = table.getModel();
        List<PacketDetail> list = model.getValueAt(row);
        PacketTimeFrame frame = new PacketTimeFrame(list);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
