package net.tazgirl.magicjson;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.HashMap;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MagicJson.MODID)
public class MagicJson
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "magicjson";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();





    public MagicJson(IEventBus modEventBus, ModContainer modContainer)
    {

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us

        // TODO: Uncomment after tests
        // modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event)
    {

    }
}
