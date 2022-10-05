package com.comugamers.quboblocker.service.listener;

import com.comugamers.quboblocker.QuboBlocker;
import com.comugamers.quboblocker.listener.ServerListPacketListener;
import com.comugamers.quboblocker.service.Service;
import com.github.retrooper.packetevents.PacketEvents;

public class PacketListenerService implements Service {

    private final QuboBlocker plugin;

    public PacketListenerService(QuboBlocker plugin) {
        this.plugin = plugin;
    }

    @Override
    public void start() {
        PacketEvents.getAPI().getEventManager().registerListener(new ServerListPacketListener(plugin));
    }
}
