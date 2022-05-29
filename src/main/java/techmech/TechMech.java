package techmech;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import techmech.entity.EntityRegistry;
import techmech.item.ItemRegistry;

/** Load TechMech mod
 * @author minecraft7771
 */
@Mod(TechMech.MOD_ID)
public class TechMech
{
    /** Global ID of the mod */
    public static final String MOD_ID = "techmech";

    /** Global name of the mod */
    public static final String MOD_NAME = "TechMech";

    /** Load mod
     */
    public TechMech()
    {
        ItemRegistry.init();
        EntityRegistry.init();
    }
}
