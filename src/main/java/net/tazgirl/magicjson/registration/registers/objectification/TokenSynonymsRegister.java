package net.tazgirl.magicjson.registration.registers.objectification;

import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;

public class TokenSynonymsRegister extends MapRegister<String, String>
{
    @Override
    public String getIdentifier()
    {
        return "HookTokenSynonymsRegister";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return new FetchEvent();
    }

    public static class FetchEvent extends MapRegisterFetchEvent<String, String>
    {
        @Override
        public String getIdentifier()
        {
            return "HookTokenSynonymsRegisterEvent";
        }
    }
}
