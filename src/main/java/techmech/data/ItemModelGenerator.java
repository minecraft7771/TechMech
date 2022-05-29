package techmech.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import techmech.TechMech;
import techmech.entity.EntityRegistry;

/** Generator for the item models
 * @author minecraft7771
 */
public class ItemModelGenerator extends ItemModelProvider
{
    /** Create new item model generator
     *
     * @param generator Data generator that will be used
     * @param helper Helper that will be used to generate the files
     */
    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper helper)
    {
        super(generator, TechMech.MOD_ID, helper);
    }

    /** Register models for all mod items
     */
    @Override
    protected void registerModels()
    {
        withExistingParent(EntityRegistry.MECH.get().getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
