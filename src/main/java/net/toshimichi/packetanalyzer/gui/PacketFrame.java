package net.toshimichi.packetanalyzer.gui;

import javax.swing.*;
import java.awt.*;

public class PacketFrame extends JFrame {

    private final JPanel panel = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane();
    private final PacketTable table;
    private final PacketBound bound;

    public PacketFrame(PacketBound bound) {
        super("Packet Monitor (" + bound + ")");
        this.bound = bound;
        table = new PacketTable();
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(table);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
    }

    public void addPacket(PacketDetail e) {
        if (e.getBound() == bound)
            table.addPacket(e);
    }
}
