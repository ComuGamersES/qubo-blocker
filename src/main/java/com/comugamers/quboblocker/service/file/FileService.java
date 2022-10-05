package com.comugamers.quboblocker.service.file;

import com.comugamers.quboblocker.QuboBlocker;
import com.comugamers.quboblocker.file.YamlFile;
import com.comugamers.quboblocker.service.Service;

public class FileService implements Service {

    private final QuboBlocker plugin;

    public FileService(QuboBlocker plugin) {
        this.plugin = plugin;
    }

    @Override
    public void start() {
        plugin.setConfig(new YamlFile(plugin, "config"));
    }
}
