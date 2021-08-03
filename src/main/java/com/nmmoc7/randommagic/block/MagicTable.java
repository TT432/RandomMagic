package com.nmmoc7.randommagic.block;

import com.nmmoc7.randommagic.block.base.ModBlockBase;
import com.nmmoc7.randommagic.block.base.ModObjBlock;
import com.nmmoc7.randommagic.itemgroup.MainItemGroup;
import com.nmmoc7.randommagic.tileentity.MagicTableTileEntity;
import com.nmmoc7.randommagic.util.MathUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * @author DustW
 */
public class MagicTable extends ModObjBlock {
    private static final VoxelShape SHAPE_UP = Block.makeCuboidShape(3, 13, 3, 13, 15, 13);
    private static final VoxelShape SHAPE_MID = Block.makeCuboidShape(4, 3, 4, 12, 13, 12);
    private static final VoxelShape SHAPE_BELOW = Block.makeCuboidShape(2, 0, 2, 14, 3, 14);
    private static final VoxelShape SHAPE = VoxelShapes.or(SHAPE_UP, SHAPE_MID, SHAPE_BELOW);

    public MagicTable() {
        super("magic_table",
                AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5),
                MainItemGroup.GROUP);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MagicTableTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        // must click on the upper surface
        if (hit.getFace() != Direction.UP || !MathUtils.containsInclusive(
                SHAPE_UP.getBoundingBox(),
                hit.getHitVec().subtract(pos.getX(), pos.getY(), pos.getZ())
        )) {
            return ActionResultType.PASS;
        }

        if (!worldIn.isRemote) {
            ItemStack heldItem = player.getHeldItem(handIn);
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof MagicTableTileEntity) {
                MagicTableTileEntity tileEntity = (MagicTableTileEntity) te;
                ItemStack pedestalStack = tileEntity.getStackInSlot(0);
                if (pedestalStack.isEmpty() && !heldItem.isEmpty()) {
                    tileEntity.setInventorySlotContents(0, heldItem.copy());
                    player.setHeldItem(handIn, ItemStack.EMPTY);
                } else if (!pedestalStack.isEmpty() && heldItem.isEmpty()) {
                    player.setHeldItem(handIn, pedestalStack.copy());
                    tileEntity.setInventorySlotContents(0, ItemStack.EMPTY);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstoneFromInventory((IInventory) worldIn.getTileEntity(pos));
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof IInventory) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
        }

        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
