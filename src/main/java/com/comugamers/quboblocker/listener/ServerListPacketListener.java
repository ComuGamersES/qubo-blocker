package com.comugamers.quboblocker.listener;

import com.comugamers.quboblocker.QuboBlocker;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;

public class ServerListPacketListener extends PacketListenerAbstract {

    private final QuboBlocker plugin;

    public ServerListPacketListener(QuboBlocker plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (isServerListPacket(event.getPacketType())) {
            // Get the source address
            String source = event.getSocketAddress().getAddress().getHostAddress();

            // Check if it is trusted before doing anything
            if (plugin.getConfig().getStringList("trusted-addresses").contains(source)) {
                // If so, return
                return;
            }

            // Cancel the event and close the connection
            event.setCancelled(true);
            event.getUser().closeConnection();

            // Log a warning if the admin wants to
            if (plugin.getConfig().getBoolean("log-warning")) {
                plugin.getLogger().warning(
                        "Connection closed during server list ping from " + event.getUser().getAddress().toString()
                                + " - are backend servers on this node exposed?"
                );
            }
        }
    }

    private boolean isServerListPacket(PacketTypeCommon type) {
        return type == PacketType.Handshaking.Client.LEGACY_SERVER_LIST_PING
                || type == PacketType.Handshaking.Server.LEGACY_SERVER_LIST_RESPONSE
                || type == PacketType.Status.Client.REQUEST
                || type == PacketType.Status.Server.RESPONSE
                || type == PacketType.Status.Client.PING
                || type == PacketType.Status.Server.PONG;
    }
}
