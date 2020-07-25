package net.toshimichi.packetanalyzer.gui;

import javax.swing.*;
import java.awt.*;

public class TableFrame extends JFrame{

    private final JTable table;

    public TableFrame(String title, JTable table) {
        super(title);
        this.table = table;
        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(table);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
    }

    public JTable getTable() {
        return table;
    }
}
