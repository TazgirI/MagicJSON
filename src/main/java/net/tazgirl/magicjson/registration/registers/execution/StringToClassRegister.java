package net.tazgirl.magicjson.registration.registers.execution;

import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;

public class StringToClassRegister extends MapRegister<String, Class<?>>
{
    @Override
    protected String getIdentifier()
    {
        return "StringToClassRegister";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return new FetchEvent();
    }

    public static class FetchEvent extends MapRegisterFetchEvent<String, Class<?>>
    {
        @Override
        public String getIdentifier()
        {
            return "StringToClassRegisterFetchEvent";
        }
    }
}
