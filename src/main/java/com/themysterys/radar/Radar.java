package com.themysterys.radar;

import com.themysterys.radar.config.RadarConfig;
import com.themysterys.radar.utils.Utils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;

public class Radar implements ModInitializer {

    public boolean isNewInstallation = !Files.exists(FabricLoader.getInstance().getConfigDir().resolve("radar.json"));
    private final RadarConfig config = RadarConfig.load();

    private boolean devMode = false;

    private static Radar instance;

    @Override
    public void onInitialize() {
        Utils.log("Radar is initializing!");
        instance = this;
    }

    public RadarConfig getConfig() {
        return config;
    }

    public static Radar getInstance() {
        return instance;
    }

    public void toggleDevMode() {
        devMode = !devMode;
    }

    public boolean getDevMode() {
        return devMode;
    }

    public static String getURL() {
        if (getInstance().devMode) {
            return "http://localhost:8879";
        } else {
            return "https://fishyapi.themysterys.com";
        }
    }
}
