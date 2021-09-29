package geoactivity.common.registry;

import geoactivity.client.gui.screen.handler.CoalRefinerScreenHandler;
import geoactivity.client.gui.screen.handler.ReinforcedMinerScreenHandler;
import geoactivity.common.GeoActivity;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public final class GAScreenHandlerTypes {

    public static final ScreenHandlerType<CoalRefinerScreenHandler> COAL_REFINER = simple("coal_refiner", CoalRefinerScreenHandler::new);
    public static final ScreenHandlerType<ReinforcedMinerScreenHandler> REINFORCED_MINER = simple("reinforced_miner", ReinforcedMinerScreenHandler::new);

        public static <T extends ScreenHandler> ScreenHandlerType<T> simple(final String name, final ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory) {
        return ScreenHandlerRegistry.registerSimple(new Identifier(GeoActivity.MODID, name), factory);
    }

    public static <T extends ScreenHandler> ScreenHandlerType<T> extended(final String name, final ScreenHandlerRegistry.ExtendedClientHandlerFactory<T> factory) {
        return ScreenHandlerRegistry.registerExtended(new Identifier(GeoActivity.MODID, name), factory);
    }
}
