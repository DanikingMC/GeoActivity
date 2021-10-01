package geoactivity.client.integration.rei;


import geoactivity.client.gui.screen.AutoStoneBuilderScreen;
import geoactivity.client.gui.screen.CoalRefinerScreen;
import geoactivity.client.integration.rei.category.ConversionCategory;
import geoactivity.client.integration.rei.category.RefinementCategory;
import geoactivity.client.integration.rei.display.ConversionDisplay;
import geoactivity.client.integration.rei.display.RefinementDisplay;
import geoactivity.common.GeoActivity;
import geoactivity.common.recipe.ConversionRecipe;
import geoactivity.common.recipe.RefinementRecipe;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GAREIPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<RefinementDisplay> REFINEMENT = CategoryIdentifier.of(new Identifier(GeoActivity.MODID, "refinement"));
    public static final CategoryIdentifier<ConversionDisplay> CONVERSION = CategoryIdentifier.of(new Identifier(GeoActivity.MODID, "conversion"));


    @Override
    public void registerCategories(CategoryRegistry registry) {
        RefinementCategory.init(registry);
        ConversionCategory.init(registry);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(RefinementRecipe.class, RefinementDisplay::new);
        registry.registerFiller(ConversionRecipe.class, ConversionDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerContainerClickArea(new Rectangle(93, 36, 22, 16), CoalRefinerScreen.class, REFINEMENT);
        registry.registerContainerClickArea(new Rectangle(108, 64, 14, 14), AutoStoneBuilderScreen.class, CONVERSION);
    }


}
