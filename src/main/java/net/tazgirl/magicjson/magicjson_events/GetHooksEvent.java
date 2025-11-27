package net.tazgirl.magicjson.magicjson_events;

import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.common.NeoForge;
import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.registration.Registers;
import net.tazgirl.magicjson.registration.processes.HookTokenSynonyms;
import net.tazgirl.magicjson.statements.hooks.Hook;

import java.util.HashMap;
import java.util.Map;

public class GetHooksEvent extends Event
{
    Map<String, Class<? extends Hook>> returnHooks = new HashMap<>();


    public GetHooksEvent()
    {

    }

    public void register(String modid, String hookName, Class<? extends Hook> hookClass)
    {
        String hookAddress = modid + ":" + hookName;
        String synonym = Registers.hookTokenSynonyms.get(hookAddress);

        hookAddress = synonym != null ? synonym : hookName;


        Class<? extends Hook> existingValue = returnHooks.putIfAbsent(hookAddress, hookClass);

        if(existingValue != null)
        {
            Logging.Warn("HOOK ATTEMPTED TO REGISTER A TOKEN THAT IS ALREADY IN USE \n" +
                    "Token: " + hookAddress + "\n" +
                    "Attempted Hook: " + hookClass.toString());
        }
    }

    protected static Map<String, Class<? extends Hook>> GetHooksForRegistry()
    {
        return NeoForge.EVENT_BUS.post(new GetHooksEvent()).returnHooks;
    }

}
