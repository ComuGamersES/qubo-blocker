package com.comugamers.quboblocker.service.packetevents;

import com.comugamers.quboblocker.QuboBlocker;
import com.comugamers.quboblocker.service.Service;
import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;

public class PacketEventsService implements Service {

    private final QuboBlocker plugin;

    public PacketEventsService(QuboBlocker plugin) {
        this.plugin = plugin;
    }

    @Override
    public void start() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(plugin));
        PacketEvents.getAPI()
                .getSettings()
                .bStats(true)
                .checkForUpdates(false)
                .debug(Boolean.getBoolean("comugamers.qubo-blocker.debug"));

        // Load and initialize API
        PacketEvents.getAPI().load();
        PacketEvents.getAPI().init();
    }

    @Override
    public void stop() {
        // Terminate API
        PacketEvents.getAPI().terminate();
    }
}
