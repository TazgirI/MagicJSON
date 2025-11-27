package net.tazgirl.magicjson.registration.registers.tokenisation;

import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;
import net.tazgirl.magicjson.statements.hooks.Hook;

import java.util.Map;

public class HookTokensRegister extends MapRegister<String, Class<? extends Hook>>
{
    @Override
    public String getIdentifier()
    {
        return "HookTokensRegister";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return new FetchEvent();
    }

    public static class FetchEvent extends MapRegisterFetchEvent<String, Class<? extends Hook>>
    {

        @Override
        public String getIdentifier()
        {
            return "HookTokensRegister";
        }
    }
}
