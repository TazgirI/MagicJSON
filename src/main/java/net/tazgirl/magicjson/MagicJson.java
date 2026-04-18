package net.tazgirl.magicjson;

import net.tazgirl.magicjson.processing.Tokenisation;
import net.tazgirl.magicjson.processing.TokensToHolder;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Map;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MagicJson.MODID)
public class  MagicJson
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "magicjson";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    static IEventBus MOD_EVENT_BUS;


    public static Object runStatement(String address)
    {
        return PrivateCore.runStatement(address);
    }

    public static Object runStatement(String address, Map<String, Object> args)
    {
        return PrivateCore.runStatement(address, args);
    }

    public static boolean statementExists(String address)
    {
        return PrivateCore.hasStatement(address);
    }

    public static boolean isAddress(Object object)
    {
        return object instanceof String string && PrivateCore.hasStatement(string);
    }

    public static Object attemptRunObject(Object object)
    {
        if(isAddress(object))
        {
            return runStatement((String) object);
        }

        return object;
    }

    public static Object attemptRunObject(Object object, Map<String, Object> args)
    {
        if(isAddress(object))
        {
            return runStatement((String) object, args);
        }

        return object;
    }
    public static Object tokeniseAndRun(String string)
    {
        return tokeniseAndRun(string, "N:A");
    }

    public static Object tokeniseAndRun(String string, String debugAddress)
    {
        return new TokensToHolder(new Tokenisation(string), debugAddress);
    }

    public MagicJson(IEventBus modEventBus, ModContainer modContainer)
    {
        MOD_EVENT_BUS = modEventBus;
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event)
    {

    }
}
