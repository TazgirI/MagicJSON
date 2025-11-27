package net.tazgirl.magicjson.event_handlers;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.magicjson_events.GetHooksEvent;

@EventBusSubscriber(modid = MagicJson.MODID)
public class RegisterHooks
{
    @SubscribeEvent
    public static void registerHooks(GetHooksEvent event)
    {
        // TODO: Remember to actually register your hooks

        // event.register(...)
    }
}
