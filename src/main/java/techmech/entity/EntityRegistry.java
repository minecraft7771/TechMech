package techmech.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import techmech.TechMech;
import techmech.entity.mech.Mech;

/** Registry for mod entities
 * @author minecraft7771
 */
public class EntityRegistry
{
    /** Register of all mod entities */
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TechMech.MOD_ID);

    /** Register all entities
     */
    public static void init()
    {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<EntityType<Mech>> MECH = ENTITIES.register(Mech.name, () -> EntityType.Builder.of(Mech::new, MobCategory.CREATURE)
            .sized(0.7f, 4.5f)
            .clientTrackingRange(8)
            .setShouldReceiveVelocityUpdates(false)
            .build(Mech.name));
}
