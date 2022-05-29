package techmech.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import techmech.TechMech;
import techmech.entity.EntityRegistry;
import techmech.item.ItemRegistry;
import techmech.setup.ModConfiguration;

/** Generator for the mod language file
 * @author minecraft7771
 */
public class LanguageGenerator extends LanguageProvider
{
    /** Create new language generator
     *
     * @param generator Data generator that will be used
     * @param locale Used localization
     */
    public LanguageGenerator(DataGenerator generator, String locale)
    {
        super(generator, TechMech.MOD_ID, locale);
    }

    /** Add translations here
     */
    @Override
    protected void addTranslations()
    {
        add("itemGroup." + ModConfiguration.CREATIVE_TAB_NAME, "TechMech");
        add(ItemRegistry.MECH_EGG.get(), "Mech Spawn Egg");
        add(EntityRegistry.MECH.get(), "Mech");
    }
}
