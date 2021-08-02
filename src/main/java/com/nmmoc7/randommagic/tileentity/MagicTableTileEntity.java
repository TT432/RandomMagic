package com.nmmoc7.randommagic.tileentity;

import com.nmmoc7.randommagic.RandomMagic;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

/**
 * @author DustW
 */
public class MagicTableTileEntity extends LockableLootTileEntity {
    private final NonNullList<ItemStack> content = NonNullList.withSize(1, ItemStack.EMPTY);

    public MagicTableTileEntity() {
        super(ModTileEntityTypes.MAGIC_TABLE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        content.set(0, ItemStack.read(nbt.getCompound("Item")));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT result = super.write(compound);
        result.put("Item", this.content.get(0).write(new CompoundNBT()));
        return result;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return content;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        super.setInventorySlotContents(index, stack);
        update();
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack itemStack = super.removeStackFromSlot(index);
        update();
        return itemStack;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemStack = super.decrStackSize(index, count);
        update();
        return itemStack;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), 0, this.content.get(0).write(new CompoundNBT()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.content.set(0, ItemStack.read(pkt.getNbtCompound()));
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT result = super.getUpdateTag();
        result.put("Item", this.content.get(0).write(new CompoundNBT()));
        return result;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
        content.set(0, ItemStack.read(tag.getCompound("Item")));
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + RandomMagic.MOD_ID + ".item_pedestal");
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return null;
    }

    private void update() {
        BlockPos pos = this.getPos();
        BlockState state = this.world.getBlockState(pos);
        this.world.notifyBlockUpdate(this.getPos(), state, state, 0);
    }
}
