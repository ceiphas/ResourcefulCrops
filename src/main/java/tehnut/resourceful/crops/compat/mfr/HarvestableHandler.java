package tehnut.resourceful.crops.compat.mfr;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import tehnut.resourceful.crops.ConfigHandler;
import tehnut.resourceful.crops.block.BlockRCrop;
import tehnut.resourceful.crops.registry.BlockRegistry;
import tehnut.resourceful.crops.registry.ItemRegistry;
import tehnut.resourceful.crops.api.registry.SeedRegistry;
import tehnut.resourceful.crops.tile.TileRCrop;
import tehnut.resourceful.crops.api.util.BlockStack;
import tehnut.resourceful.crops.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HarvestableHandler implements IFactoryHarvestable {

    // IFactoryHarvestable

    @Override
    public Block getPlant() {
        return BlockRegistry.crop;
    }

    @Override
    public HarvestType getHarvestType() {
        return HarvestType.Normal;
    }

    @Override
    public boolean breakBlock() {
        return false;
    }

    @Override
    public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {

        if (world.getBlock(x, y, z) instanceof BlockRCrop && ConfigHandler.enableMFRAutomation) {
            TileEntity cropTile = world.getTileEntity(x, y, z);

            if (cropTile != null && cropTile instanceof TileRCrop)
                if (world.getBlockMetadata(x, y, z) == 7)
                    return true;
        }

        return false;
    }

    @Override
    public List<ItemStack> getDrops(World world, Random random, Map<String, Boolean> harvesterSettings, int x, int y, int z) {

        TileEntity cropTile = world.getTileEntity(x, y, z);
        List<ItemStack> drops = new ArrayList<ItemStack>();

        if (cropTile != null && cropTile instanceof TileRCrop) {
            drops.add(new ItemStack(ItemRegistry.seed, 1, SeedRegistry.getIndexOf(((TileRCrop) cropTile).getSeedName())));
            drops.add(new ItemStack(ItemRegistry.shard, 1, SeedRegistry.getIndexOf(((TileRCrop) cropTile).getSeedName())));
            return drops;
        }

        return null;
    }

    @Override
    public void preHarvest(World world, int x, int y, int z) {
        Utils.playBlockBreakAnim(world, x, y, z, new BlockStack(getPlant(), world.getBlockMetadata(x, y, z)));
        TileEntity cropTile = world.getTileEntity(x, y, z);
        world.setBlockMetadataWithNotify(x, y, z, 0, 3);
        ((TileRCrop)cropTile).setShouldDrop(false);
        world.setBlockToAir(x, y, z);
        ((TileRCrop)cropTile).setShouldDrop(true);
    }

    @Override
    public void postHarvest(World world, int x, int y, int z) {

    }
}
