package com.themysterys.fishymap.utils;

import com.themysterys.fishymap.Fishymap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.decoration.DisplayEntity;

import java.util.List;
import java.util.UUID;

public class FishingSpot {
    private final String cords;
    private final List<String> perks;
    private final String island;
    private final DisplayEntity.TextDisplayEntity entity;

    public FishingSpot(String cords, List<String> perks, String island, DisplayEntity.TextDisplayEntity entity) {
        this.cords = cords;
        this.perks = perks;
        this.island = island;
        this.entity = entity;
    }

    public String getCords() {
        return cords;
    }

    public List<String> getPerks() {
        return perks;
    }

    public String getIsland() {
        return island;
    }

    public DisplayEntity.TextDisplayEntity getEntity() {
        return entity;
    }

    public String format() {
        UUID uuid = MinecraftClient.getInstance().player.getUuid();
        Boolean shareUser = Fishymap.getInstance().getConfig().shareUser;

        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("\"cords\": \"").append(cords).append("\",\n");
        json.append("\"perks\": [");

        for (int i = 0; i < perks.size(); i++) {
            json.append("\"").append(perks.get(i)).append("\"");
            if (i < perks.size() - 1) {
                json.append(", ");
            }
        }

        json.append("],\n");
        json.append("\"island\": \"").append(island).append("\",\n");

        json.append("\"uuid\": \"").append(uuid).append("\",\n");
        json.append("\"shareUser\": ").append(shareUser).append("\n");

        json.append("}");

        return json.toString();
    }

    public String deleteFormat() {
        UUID uuid = MinecraftClient.getInstance().player.getUuid();

        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("\"cords\": \"").append(cords).append("\",\n");
        json.append("\"island\": \"").append(island).append("\",\n");
        json.append("\"uuid\": \"").append(uuid).append("\"\n");
        json.append("}");

        return json.toString();
    }

}