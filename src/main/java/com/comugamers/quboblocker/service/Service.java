package com.comugamers.quboblocker.service;

public interface Service {

    void start();
    default void stop() {}
}
