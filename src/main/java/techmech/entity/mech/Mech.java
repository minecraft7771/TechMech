package techmech.entity.mech;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.UUID;

/** Class for the mech entity
 * @author minecraft7771
 */
public class Mech extends AbstractHorse implements ContainerListener, Saddleable, PlayerRideable
{
    /** Name of the mech for location purposes */
    public static String name = "mech";
    /** UUID modifier for armor */
    private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");

    /** Create new mech entity
     *
     * @param type Type of the entity
     * @param level Level of the entity
     */
    public Mech(EntityType<? extends AbstractHorse> type, Level level)
    {
        super(type, level);
    }

    // region Overriden getters and setters

    /** Register the goals of the mech
     *
     */
    @Override
    protected void registerGoals()
    {

    }

    /** Get if the mech is a baby
     *
     * @return False
     */
    @Override
    public boolean isBaby()
    {
        return false;
    }

    /** Get if the mech is saddles (ready to be ridden)
     *
     * @return True
     */
    @Override
    public boolean isSaddled() {
        return true;
    }

    /** Get if the mech is tamed
     *
     * @return True
     */
    @Override
    public boolean isTamed()
    {
        return true;
    }

    /** Get if the mech is standing
     *
     * @return False
     */
    @Override
    public boolean isStanding()
    {
        return false;
    }

    /** Get if the mech can mate
     *
     * @param otherAnimal Breed partner
     * @return False
     */
    public boolean canMate(@NotNull Animal otherAnimal)
    {
        return false;
    }

    /** Get if the mech can parent
     *
     * @return False
     */
    @Override
    protected boolean canParent()
    {
        return false;
    }

    /** Get if the mech can wear armor
     *
     * @return True
     */
    @Override
    public boolean canWearArmor() {
        return true;
    }

    /** Get if the item is armor that the mech can wear
     *
     * @param pStack Item to be used as armor
     * @return True if the item can be used as armor, else false
     */
    @Override
    public boolean isArmor(ItemStack pStack) {
        return pStack.getItem() instanceof HorseArmorItem;
    }

    /** Get passengers riding offset for the mech
     *
     * @return Riding offset of the passengers
     */
    @Override
    public double getPassengersRidingOffset()
    {
        return (double)this.dimensions.height * 0.53D;
    }

    /** Get the breed offspring of the mech
     *
     * @param serverLevel Level of the mob
     * @param otherMob Breed partner
     * @return Null
     */
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob otherMob)
    {
        return null;
    }

    /** Get the ambient sound of the mech
     *
     * @return Ambient sound of the mech
     */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.HORSE_AMBIENT;
    }

    /** Get the death sound of the mech
     *
     * @return Death sound of the mech
     */
    @Nullable
    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.HORSE_DEATH;
    }

    /** Get the eating sound of the mech
     *
     * @return Null
     */
    @Nullable
    @Override
    protected SoundEvent getEatingSound()
    {
        return null;
    }

    /** Get the hurt sound of the mech
     *
     * @return Hurt sound of the mech
     */
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource)
    {
        return SoundEvents.HORSE_HURT;
    }

    /** Get the angry sound of the mech
     *
     * @return Null
     */
    @Nullable
    @Override
    protected SoundEvent getAngrySound()
    {
        return null;
    }

    /** Add additional data of the mech to be saved
     *
     * @param pCompound Tags to be added
     */
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound)
    {
        super.addAdditionalSaveData(pCompound);
        if (!this.inventory.getItem(1).isEmpty())
        {
            pCompound.put("ArmorItem", this.inventory.getItem(1).save(new CompoundTag()));
        }
    }

    /** Protected helper method to read subclass entity data from NBT.
     *
     * @param pCompound Tag to be read
     */
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound)
    {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("ArmorItem", 10))
        {
            ItemStack itemstack = ItemStack.of(pCompound.getCompound("ArmorItem"));
            if (!itemstack.isEmpty() && this.isArmor(itemstack))
            {
                this.inventory.setItem(1, itemstack);
            }
        }
        this.updateContainerEquipment();
    }

    // endregion

    // region Functions borrowed from vanilla rideable

    /** Get the Armor that the mech wears
     *
     * @return Worn armor of the mech
     */
    public ItemStack getArmor()
    {
        return this.getItemBySlot(EquipmentSlot.CHEST);
    }

    /** Apply armor to the mech
     *
     * @param armor Armor item
     */
    private void setArmor(ItemStack armor)
    {
        this.setItemSlot(EquipmentSlot.CHEST, armor);
        this.setDropChance(EquipmentSlot.CHEST, 0.0F);
    }

    /** Set the armor equipment of the mech
     *
     * @param armor Armor equipment to be set
     */
    private void setArmorEquipment(ItemStack armor)
    {
        this.setArmor(armor);
        if (!this.level.isClientSide)
        {
            this.getAttribute(Attributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
            if (this.isArmor(armor))
            {
                int i = ((HorseArmorItem)armor.getItem()).getProtection();
                if (i != 0)
                {
                    this.getAttribute(Attributes.ARMOR).addTransientModifier(new AttributeModifier(ARMOR_MODIFIER_UUID, "Horse armor bonus", (double)i, AttributeModifier.Operation.ADDITION));
                }
            }
        }
    }

    /** Function that will be invoked when the player has interacted with the mech
     *
     * @param pPlayer Player that interacted with the mech
     * @param pHand Hand details of the player
     * @return Interaction result
     */
    @Override
    public @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand)
    {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        // If the player is riding the entity
        if (pPlayer.isPassenger())
        {
            // If they hold no item in hand, shoot
            if (itemstack.isEmpty())
            {
                shoot(pPlayer);
            }
            // If they hold a block in hand, place it
            else
            {
                Block block = Block.byItem(itemstack.getItem());

                if (block != Blocks.AIR)
                {
                    BlockHitResult hitResult = (BlockHitResult)pPlayer.pick(20.0D, 0.0F, false);

                    if (pPlayer.level.getBlockState(hitResult.getBlockPos()).getBlock() != Blocks.AIR)
                    {
                        BlockPos blockpos = hitResult.getBlockPos().relative(hitResult.getDirection());
                        pPlayer.level.setBlock(blockpos, block.getStateForPlacement(new BlockPlaceContext(pPlayer, pHand, itemstack, hitResult)), 0);
                        itemstack.shrink(1);
                    }
                }
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }

        // Open the inventory of the mech if player sneak right clicks
        if (pPlayer.isSecondaryUseActive())
        {
            this.openInventory(pPlayer);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }

        // If the mech is currently ridden call super
        if (this.isVehicle())
        {
            return super.mobInteract(pPlayer, pHand);
        }

        // If the player right clicks with an item in hand
        if (!itemstack.isEmpty())
        {
            // Equip armor if the player uses armor on it
            if (this.isArmor(itemstack))
            {
                this.openInventory(pPlayer);
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        }

        // If none of the above applied set the player as rider
        this.doPlayerRide(pPlayer);
        return InteractionResult.sidedSuccess(this.level.isClientSide);
    }

    /** Play the gallop sound of the mech
     */
    @Override
    protected void playGallopSound(@NotNull SoundType p_30709_)
    {
        playSound(SoundEvents.IRON_GOLEM_STEP, p_30709_.getVolume() * 0.15f, p_30709_.getPitch() * 0.5f);
        if (this.random.nextInt(10) == 0)
        {
            this.playSound(SoundEvents.HORSE_BREATHE, p_30709_.getVolume() * 0.6f, p_30709_.getPitch());
        }

        ItemStack stack = this.inventory.getItem(1);
        if (isArmor(stack)) stack.onHorseArmorTick(level, this);
    }

    /** Play the step sound of the mech
     *
     * @param pPos Position of the mech
     * @param pBlock Block that the mech is on
     */
    @Override
    protected void playStepSound(@NotNull BlockPos pPos, @NotNull BlockState pBlock)
    {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    /** Update the equipment of the mech
     */
    @Override
    protected void updateContainerEquipment()
    {
        if (!this.level.isClientSide)
        {
            super.updateContainerEquipment();
            this.setArmorEquipment(this.inventory.getItem(1));
            this.setDropChance(EquipmentSlot.CHEST, 0.0F);
        }
    }

    /** Called by InventoryBasic.onInventoryChanged() on a array that is never filled.
     */
    @Override
    public void containerChanged(@NotNull Container pInvBasic)
    {
        ItemStack itemstack = this.getArmor();
        super.containerChanged(pInvBasic);
        ItemStack itemstack1 = this.getArmor();
        if (this.tickCount > 20 && this.isArmor(itemstack1) && itemstack != itemstack1)
        {
            this.playSound(SoundEvents.HORSE_ARMOR, 0.5F, 1.0F);
        }
    }

    // endregion

    // region mech specific functions

    /** Shoot a projects from the mech
     *
     * @param player Mech driver
     */
    private void shoot(Player player)
    {
        ArrowItem arrowitem = (ArrowItem)(Items.ARROW);
        AbstractArrow abstractarrow = arrowitem.createArrow(player.level, new ItemStack(Items.ARROW), player);
        abstractarrow.pickup = AbstractArrow.Pickup.DISALLOWED;
        abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
        player.level.addFreshEntity(abstractarrow);
    }

    /** Prepare the attributes of the mech
     *
     * @return Attribute supplier builder that contains the attributes of a mech
     */
    public static AttributeSupplier.Builder prepareAttributes()
    {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20f)
                .add(Attributes.FOLLOW_RANGE, 40f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f)
                .add(Attributes.JUMP_STRENGTH, 1.0f);
    }

    /** Function that will be invoked when the mech has taken damage
     *
     * @param source Source of the damage
     * @param damage Amount of dealt damage
     * @return True if damage was dealt, else false
     */
    @Override
    public boolean hurt(DamageSource source, float damage)
    {
        Entity attacker = source.getEntity();

        // If the attacking entity is a passenger of the mech, ignore the attack
        if (attacker != null && hasPassenger(attacker))
        {
            BlockPos blockpos = ((BlockHitResult)attacker.pick(20.0D, 0.0F, false)).getBlockPos();
            attacker.level.destroyBlock(blockpos, true);

            return true;
        }

        return super.hurt(source, damage);
    }

    // endregion
}
