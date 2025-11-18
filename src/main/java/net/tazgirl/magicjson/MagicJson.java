package net.tazgirl.magicjson;

import net.tazgirl.magicjson.main.addresses.StatementAddress;
import net.tazgirl.magicjson.main.function_object.objects.BaseFunctionObject;
import net.tazgirl.magicjson.main.function_object.FunctionManager;
import net.tazgirl.magicjson.main.function_object.SourceFunctionHolder;
import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.StatementManager;
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
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event)
    {

    }


    // Function that allows you to process a function without declaring the base manager, that way paths can run themselves without divulging or overwriting the address



    // Function manager is a data handler only, actual running/execution is run in this function
    public static Object RunFunction(FunctionManager functionManager)
    {
        SourceFunctionHolder functionHolder = PrivateCore.getFunctionCopy().get(functionManager.getFunctionAddress().getLocalAddress());



        if(functionHolder != null)
        {
            return RunFunction(functionHolder, functionManager);
        }


        Logging.Debug("Attempted to run a function that does not match any known addresses   " +
                "Failed address: " + functionManager.getFunctionAddress(), false);

        return null;
    }

    public static Object RunFunction(String address, FunctionManager functionManager)
    {
        if(PrivateCore.hasFunction(address))
        {
            return RunFunction(PrivateCore.getFunction(address), functionManager);
        }

        Logging.Debug("Attempted to run a function that does not match any known addresses   " +
                "Failed address: " + address, false);

        return null;
    }


    public static Object RunFunction(SourceFunctionHolder functionHolder, FunctionManager functionManager)
    {
        functionHolder.SpreadManager(functionManager);

        for(BaseFunctionObject functionObject: functionHolder.heldObjects())
        {
            if(functionManager.getProcessFlag() == FunctionManager.ProcessFlag.STOP)
            {
                break;
            }
            functionObject.Run();
        }

        return functionManager.getReturnValue();
    }

    public static Object RunStatement(StatementManager statementManager)
    {
        BaseStatementObject statementObject = PrivateCore.getStatement(statementManager.getStatementAddress());

        if(statementObject != null)
        {
            statementObject.SpreadManager(statementManager);
            return statementObject.Resolve();
        }

        return null;
    }

    public static Object RunStatement(StatementAddress address, StatementManager manager)
    {
        BaseStatementObject statementObject = PrivateCore.getStatement(address);

        if(statementObject != null)
        {
            statementObject.SpreadManager(manager);
            return statementObject.Resolve();
        }

        return null;
    }

    // ONly to be used for statements without args
    public static Object RunStatement(StatementAddress statementAddress)
    {
        return RunStatement(new StatementManager(statementAddress, new HashMap<>()));
    }

}
