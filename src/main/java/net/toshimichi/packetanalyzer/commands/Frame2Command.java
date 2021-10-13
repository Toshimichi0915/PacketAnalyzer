package net.toshimichi.packetanalyzer.commands;

import net.toshimichi.packetanalyzer.gui.MonitorFrame2;
import net.toshimichi.packetanalyzer.gui.PacketListener;
import net.toshimichi.packetanalyzer.packet.PacketBound;

public class Frame2Command extends Frame1Command {

    @Override
    protected PacketListener newClientPacketListener() {
        return new MonitorFrame2(PacketBound.CLIENT);
    }

    @Override
    protected PacketListener newServerPacketListener() {
        return new MonitorFrame2(PacketBound.SERVER);
    }
}
