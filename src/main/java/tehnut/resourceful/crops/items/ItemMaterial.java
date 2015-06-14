package tehnut.resourceful.crops.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tehnut.resourceful.crops.ModInformation;
import tehnut.resourceful.crops.ResourcefulCrops;

import java.util.List;

public class ItemMaterial extends Item {

    String[] materials = { "", ".mundane", ".magical", ".infused", ".arcane" };

    public ItemMaterial() {
        super();

        setUnlocalizedName(ModInformation.ID + ".dust");
        setTextureName(ModInformation.ID + ":dust_base");
        setCreativeTab(ResourcefulCrops.tabResourcefulCrops);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName() + materials[stack.getItemDamage() % materials.length];
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < materials.length; i++)
            list.add(new ItemStack(this, 1, i));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        return stack.getItemDamage() == 4;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 0:
                return EnumRarity.common;
            case 1:
                return EnumRarity.common;
            case 2:
                return EnumRarity.uncommon;
            case 3:
                return EnumRarity.rare;
            case 4:
                return EnumRarity.epic;
            default:
                return EnumRarity.common;
        }
    }
}