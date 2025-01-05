package com.themysterys.fishymap.modules;

import com.noxcrew.noxesium.network.NoxesiumPackets;
import com.noxcrew.noxesium.network.clientbound.ClientboundMccServerPacket;
import com.themysterys.fishymap.FishymapClient;
import com.themysterys.fishymap.utils.Utils;

public class NoxesiumIntegration {
    public void init() {
        Utils.log("Initializing NoxesiumIntegration");

        NoxesiumPackets.CLIENT_MCC_SERVER.addListener(this, (any, packet, ctx) -> handlePacket(packet));

        Utils.log("NoxesiumIntegration has been initialized");
    }

    public void handlePacket(ClientboundMccServerPacket packet) {
        String subType = packet.subType();
        Utils.log("Received MCC_SERVER packet with subtype: " + subType);

        if (Utils.isOnFishingIsland(subType)) {
            Utils.log("On Fishing Island");
            FishymapClient.getInstance().setIsland(subType);
        } else {
            FishymapClient.getInstance().setIsland(null);
            Utils.log("Not on Fishing Island");
        }
    }
}
