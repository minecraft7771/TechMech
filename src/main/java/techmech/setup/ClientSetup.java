package techmech.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import techmech.TechMech;
import techmech.entity.EntityRegistry;
import techmech.entity.mech.MechModel;
import techmech.entity.mech.MechRenderer;

/** Setup that will be invoked on the client side
 * @author minecraft7771
 */
@Mod.EventBusSubscriber(modid = TechMech.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup
{
    /** Event used to register render layers
     *
     * @param event Event details
     */
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(MechModel.LAYER_LOCATION, MechModel::createBodyLayer);
    }

    /** Event used to register renderers
     *
     * @param event Event details
     */
    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(EntityRegistry.MECH.get(), MechRenderer::new);
    }
}
