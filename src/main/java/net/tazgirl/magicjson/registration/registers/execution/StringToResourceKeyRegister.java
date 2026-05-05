package net.tazgirl.magicjson.registration.registers.execution;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;

public class StringToResourceKeyRegister extends MapRegister<String, ResourceKey<? extends Registry<?>>>
{
    @Override
    protected String getIdentifier()
    {
        return "StringToResourceKeyRegister";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return new FetchEvent();
    }

    public static class FetchEvent extends MapRegisterFetchEvent<String, ResourceKey<? extends Registry<?>>>
    {

        @Override
        public String getIdentifier()
        {
            return "StringToResourceKeyRegisterEvent";
        }
    }
}
