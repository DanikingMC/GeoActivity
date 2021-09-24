package geoactivity.client;

import geoactivity.client.gui.screen.CoalRefinerScreen;
import geoactivity.common.registry.GAScreenHandlerTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class GeoActivityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(GAScreenHandlerTypes.COAL_REFINER, CoalRefinerScreen::new);

    }
}
