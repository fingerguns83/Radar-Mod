package com.themysterys.radar.modules;

import com.themysterys.radar.utils.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class AutoRod {
    private static int index = 0;

    private static final MutableComponent[] components = new MutableComponent[] {
            Component.literal("Oops did I leave this command in the mod ").append(Component.literal("\uE068").withStyle(Utils.mccFont())),
            Component.literal("Oh well it doesn't do anything I promise ").append(Component.literal("\uE06B").withStyle(Utils.mccFont())),
            Component.literal("No really, you have to believe me ").append(Component.literal("\uE08B").withStyle(Utils.mccFont())),
            Component.literal("Trust me, this command is useless ").append(Component.literal("\uE080").withStyle(Utils.mccFont())),
            Component.literal("No one ever trusts me anymore ").append(Component.literal("\uE08C").withStyle(Utils.mccFont())),
            Component.literal("Look this is a mapping mod. Not an auto fishing mod"),
            Component.literal("Why would I even put auto fishing in here. I'm a ").append(Component.literal("Moderator").withColor(11613948)),
            Component.literal("Look just stop trying, I'm warning you."),
            Component.literal("Do you ").append(Component.literal("really").withStyle(ChatFormatting.ITALIC)).append(" want to break the server rules ").append(Component.literal("\uE065").withStyle(Utils.mccFont())),
            Component.literal("..."),
            Component.literal("Look if you keep running this, I'll have to report you to the ").append(Component.literal("\uE081").withStyle(Utils.mccFont())).append(Component.literal("Noxcrew").withStyle(ChatFormatting.RED, ChatFormatting.BOLD)),
            Component.literal("This is your last chance..."),
            Component.literal("You have now been reported to the ").append(Component.literal("\uE081").withStyle(Utils.mccFont())).append(Component.literal("Noxcrew ").withStyle(ChatFormatting.RED, ChatFormatting.BOLD)).append(Component.literal("\uE08A").withStyle(Utils.mccFont())),
    };

    public static void sendMessage() {
        if (index < components.length) {
            Component message = components[index];
            index++;
            Utils.sendMessage(message, true);
        }
    }
}
