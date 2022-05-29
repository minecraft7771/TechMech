package techmech.entity.mech;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import techmech.TechMech;

/** Renderer of the mech
 * @author minecraft7771
 */
public class MechRenderer extends MobRenderer<Mech, MechModel<Mech>>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TechMech.MOD_ID, "textures/entity/" + Mech.name + ".png");

    /** Create new mech renderer
     *
     * @param context Renderer context
     */
    public MechRenderer(EntityRendererProvider.Context context)
    {
        super(context, new MechModel<>(context.bakeLayer(MechModel.LAYER_LOCATION)), 1f);
    }

    /** Get the location of the texture of the mech
     *
     * @param entity Entity of which the texture will be gotten
     * @return Location of the texture
     */
    @NotNull
    @Override
    public ResourceLocation getTextureLocation(Mech entity)
    {
        return TEXTURE;
    }
}
