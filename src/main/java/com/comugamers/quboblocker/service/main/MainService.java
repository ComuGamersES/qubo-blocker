package com.comugamers.quboblocker.service.main;

import com.comugamers.quboblocker.QuboBlocker;
import com.comugamers.quboblocker.service.Service;
import com.comugamers.quboblocker.service.file.FileService;
import com.comugamers.quboblocker.service.listener.PacketListenerService;
import com.comugamers.quboblocker.service.packetevents.PacketEventsService;

public class MainService implements Service {

    private final Service packetEventsService;
    private final Service packetListenerService;
    private final Service fileService;

    public MainService(QuboBlocker plugin) {
        this.packetEventsService = new PacketEventsService(plugin);
        this.packetListenerService = new PacketListenerService(plugin);
        this.fileService = new FileService(plugin);
    }

    @Override
    public void start() {
        startServices(packetEventsService, fileService, packetListenerService);
    }

    @Override
    public void stop() {
        stopServices(packetEventsService);
    }

    private void startServices(Service... services) {
        for (Service service : services) {
            service.start();
        }
    }

    private void stopServices(Service... services) {
        for (Service service : services) {
            service.stop();
        }
    }
}
