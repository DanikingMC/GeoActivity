package geoactivity.common.registry;

import geoactivity.common.event.handler.AttackBlockHandler;
import geoactivity.common.event.handler.ItemTooltipHandler;
import geoactivity.common.event.handler.UseItemHandler;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public class GAEvents {

    public static void init() {
        ItemTooltipCallback.EVENT.register(new ItemTooltipHandler());
        AttackBlockHandler.EVENT.register(new AttackBlockHandler());
        UseItemHandler.EVENT.register(new UseItemHandler());
    }
}
