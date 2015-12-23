package tehnut.resourceful.crops.util.handler;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.BonemealEvent;
import tehnut.resourceful.crops.ConfigHandler;
import tehnut.resourceful.crops.api.ModInformation;
import tehnut.resourceful.crops.block.BlockRCrop;
import tehnut.resourceful.crops.util.helper.LogHelper;

public class EventHandler {

    @SubscribeEvent
    public void onBonemeal(BonemealEvent event) {
        if (event.block.getBlock() instanceof BlockRCrop)
            event.setCanceled(true);
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equals(ModInformation.ID)) {
            ConfigHandler.syncConfig();
            LogHelper.info(StatCollector.translateToLocal("config.ResourcefulCrops.console.refresh"));
        }
    }
}
