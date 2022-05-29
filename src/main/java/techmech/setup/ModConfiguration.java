package techmech.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;
import techmech.TechMech;

/** Configuration of the mod
 * @author minecraft7771
 */
@Mod.EventBusSubscriber(modid = TechMech.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfiguration
{
    /** Name of the creative mode tab of the mod */
    public static final String CREATIVE_TAB_NAME = TechMech.MOD_ID;

    /** Creative mode tab of the mod */
    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab(CREATIVE_TAB_NAME)
    {
        @Override
        public ItemStack makeIcon()
        {
            //return new ItemStack(ItemRegistry.HOLLOW_PORTAL_ITEM.get());
            return new ItemStack(Items.NETHER_STAR);
        }
    };
}
