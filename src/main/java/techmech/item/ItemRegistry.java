package techmech.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import techmech.TechMech;
import techmech.entity.EntityRegistry;
import techmech.entity.mech.Mech;
import techmech.setup.ModConfiguration;

/** Registry for mod items
 * @author minecraft7771
 */
public class ItemRegistry
{
    /** Register of all mod items */
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TechMech.MOD_ID);

    /** Register all items
     */
    public static void init()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static RegistryObject<Item> MECH_EGG = ITEMS.register(Mech.name, () -> new ForgeSpawnEggItem(EntityRegistry.MECH, 0x5d5e5c, 0x940c0c, new Item.Properties().tab(ModConfiguration.CREATIVE_MODE_TAB)));
}
