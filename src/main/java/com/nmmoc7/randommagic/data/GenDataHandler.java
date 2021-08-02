package com.nmmoc7.randommagic.data;

import com.nmmoc7.randommagic.data.block.GenBlockModel;
import com.nmmoc7.randommagic.data.block.GenBlockState;
import com.nmmoc7.randommagic.data.item.GenItemModel;
import com.nmmoc7.randommagic.data.lang.LangEnUs;
import com.nmmoc7.randommagic.data.lang.LangZhCn;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenDataHandler {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new GenItemModel(generator, helper));
            generator.addProvider(new GenBlockModel(generator, helper));
            generator.addProvider(new GenBlockState(generator, helper));
        }

        if (event.includeServer()) {
            generator.addProvider(new LangZhCn(generator));
            generator.addProvider(new LangEnUs(generator));
        }
    }
}
