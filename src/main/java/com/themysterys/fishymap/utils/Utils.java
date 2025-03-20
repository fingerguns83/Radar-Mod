package com.themysterys.fishymap.utils;

import com.themysterys.fishymap.Fishymap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class Utils {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private static final Logger logger = LoggerFactory.getLogger("FishyMap");

    public static void log(String message) {
        logger.info("[FishyMap] {}", message);
    }

    public static void error(String message) {
        logger.error("[FishyMap] {}", message);
    }

    public static void warn(String message) {
        logger.warn("[FishyMap] {}", message);
    }

    public static void sendMiniMessage(String message, boolean prefix, TagResolver replacements) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player == null) {
            return;
        }

        if (prefix) {
            message = "[<gradient:#00c8ff:#006eff>FishyMap</gradient>]: " + message;
        }
        Component parsed;
        if (replacements != null) {
            parsed = miniMessage.deserialize(message, replacements);
        } else {
            parsed = miniMessage.deserialize(message);
        }
        player.sendMessage(parsed);

    }

    public static Boolean isOnIsland() {
        ServerInfo serverInfo = MinecraftClient.getInstance().getCurrentServerEntry();
        if (serverInfo == null) {
            return false;
        }

        return serverInfo.address.endsWith("mccisland.net") || serverInfo.address.endsWith("mccisland.com");
    }

    public static Boolean isOnFishingIsland(String islandName) {
        List<String> islandList = List.of("temperate_1", "temperate_2", "temperate_3", "tropical_1", "tropical_2", "tropical_3", "barren_1", "barren_2", "barren_3");

        return islandList.contains(islandName);
    }

    public static void sendRequest(String path, String data) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Fishymap.getURL()+ "/" + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data, StandardCharsets.UTF_8))
                .build();

        // Using sendAsync to avoid blocking the main thread
        CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // You can optionally handle the response here
        futureResponse.thenAccept(response -> {
            // Handle the response (for example, logging or processing the body)
            if (response.statusCode() != 200 || response.statusCode() != 201) {
                System.out.println("Received status code: " + response.statusCode());
                System.out.println("Response received:" + response.body());
            }

            if (Objects.equals(path, "spots")) {
                if (response.statusCode() == 201) {
                    Utils.sendMiniMessage("<green>Spot successfully added to map", true, null);
                } else {
                    Utils.sendMiniMessage("<yellow>Spot already on the map", true, null);
                }
            }
        }).exceptionally(ex -> {
            // Handle any exceptions (for example, logging)
            ex.printStackTrace();
            return null;
        });
    }
}
