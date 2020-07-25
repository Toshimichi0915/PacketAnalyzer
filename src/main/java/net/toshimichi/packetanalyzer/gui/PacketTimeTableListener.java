package net.toshimichi.packetanalyzer.gui;

import net.toshimichi.packetanalyzer.packet.PacketDetail;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PacketTimeTableListener extends MouseAdapter {
    private final PacketTimeTable table;

    public PacketTimeTableListener(PacketTimeTable table) {
        this.table = table;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        PacketTimeTableModel model = table.getModel();
        PacketDetail packet = model.getValueAt(row);
        PacketFrame frame = new PacketFrame(packet.getPacket());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
