package techmech.setup;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import techmech.TechMech;
import techmech.entity.EntityRegistry;
import techmech.entity.mech.Mech;

/** Setup the The TechMech mod
 * @author minecraft7771
 */
@Mod.EventBusSubscriber(modid = TechMech.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Setup
{
    /** Attribute creation event listener
     *
     * @param event Attribute creation event details
     */
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event)
    {
        event.put(EntityRegistry.MECH.get(), Mech.prepareAttributes().build());
    }
}
