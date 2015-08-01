package tehnut.resourceful.crops.registry;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import tehnut.resourceful.crops.ConfigHandler;
import tehnut.resourceful.crops.api.ResourcefulAPI;
import tehnut.resourceful.crops.item.*;
import tehnut.resourceful.crops.util.helper.RenderHelper;

public class ItemRegistry {

    public static ItemSeed seed;
    public static Item shard;
    public static Item pouch;
    public static Item stone;
    public static Item material;

    public static void registerItems() {
        stone = new ItemStone();
        GameRegistry.registerItem(stone, "ItemStone");
        ResourcefulAPI.stone = stone;

        material = new ItemMaterial();
        GameRegistry.registerItem(material, "ItemMaterial");
        ResourcefulAPI.material = material;

        seed = new ItemSeed();
        GameRegistry.registerItem(seed, "ItemSeed");
        ResourcefulAPI.seed = seed;

        shard = new ItemShard();
        GameRegistry.registerItem(shard, "ItemShard");
        ResourcefulAPI.shard = shard;

        if (ConfigHandler.enableSeedPouches) {
            pouch = new ItemPouch();
            GameRegistry.registerItem(pouch, "ItemPouch");
            ResourcefulAPI.pouch = pouch;
        }
    }

    public static void registerRenders() {
        RenderHelper.inventoryItemRenderAll(stone);
        RenderHelper.inventoryItemRenderAll(shard);
        RenderHelper.inventoryItemRenderAll(seed);
        RenderHelper.inventoryItemRenderAll(pouch);
        RenderHelper.inventoryItemRender(material);
        RenderHelper.inventoryItemRender(material, 1);
        RenderHelper.inventoryItemRender(material, 2);
        RenderHelper.inventoryItemRender(material, 3);
        RenderHelper.inventoryItemRender(material, 4);
    }
}
