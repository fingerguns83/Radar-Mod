package com.themysterys.radar.modules;

import com.themysterys.radar.utils.Utils;

public class AutoRod {
    private static int index = 0;

    private static final String[] messages = new String[] {
            "Oops did I leave this command in the mod <font:mcc:icon>\uE068",
            "Oh well it doesn't do anything I promise <font:mcc:icon>\uE06B",
            "No really, you have to believe me <font:mcc:icon>\uE08B",
            "Trust me, this command is useless <font:mcc:icon>\uE080",
            "No one ever trusts me anymore <font:mcc:icon>\uE08C",
            "Look this is a mapping mod. Not an auto fishing mod.",
            "Why would I even put auto fishing in here. I'm a <#b136fc>Moderator</#b136fc>.",
            "Look just stop trying, I'm warning you.",
            "Do you <i>really</i> want to break the server rules <font:mcc:icon>\uE065",
            "...",
            "Look if you keep running this, I'll have to report you to the <font:mcc:icon>\uE081</font> <red><bold>Noxcrew</bold></red>",
            "This is your last chance...",
            "You have now been reported to the <font:mcc:icon>\uE081</font> <red><bold>Noxcrew</bold></red> <font:mcc:icon>\uE08A"
    };

    public static void sendMessage() {
        if (index < messages.length) {
            String message = messages[index];
            index++;
            Utils.sendMiniMessage(message, true, null);
        }
    }
}
