package techmech.data;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import techmech.TechMech;

/** Data generator of the mod
 * @author minecraft7771
 */
@Mod.EventBusSubscriber(modid = TechMech.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerator
{
    /** Calls all data generators of the mod
     *
     * @param event Run Data event parameters
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        net.minecraft.data.DataGenerator generator = event.getGenerator();

        if (event.includeServer())
        {

        }
        if (event.includeClient())
        {
            generator.addProvider(new ItemModelGenerator(generator, event.getExistingFileHelper()));
            generator.addProvider(new LanguageGenerator(generator, "en_us"));
        }
    }
}
