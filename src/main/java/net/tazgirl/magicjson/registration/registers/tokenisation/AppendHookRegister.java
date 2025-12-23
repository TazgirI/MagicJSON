package net.tazgirl.magicjson.registration.registers.tokenisation;

import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;
import net.tazgirl.magicjson.statements.hooks.base.AppendHook;

public class AppendHookRegister extends MapRegister<String, Class<? extends AppendHook>>
{

    @Override
    protected String getIdentifier()
    {
        return "AppendHookRegister";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return new FetchEvent();
    }

    public static class FetchEvent extends MapRegisterFetchEvent<String, Class<? extends AppendHook>>
    {
        @Override
        public String getIdentifier()
        {
            return "AppendHookRegisterEvent";
        }
    }
}
