package net.tazgirl.magicjson.registration.registers.objectification;

import net.tazgirl.magicjson.magicjson_events.registers.bases.ListRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.ListRegister;

public class CloseTokensRegister extends ListRegister<String>
{
    @Override
    protected String getIdentifier()
    {
        return "CloseTokensRegister";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return new FetchEvent();
    }

    public static class FetchEvent extends ListRegisterFetchEvent<String>
    {
        @Override
        public String getIdentifier()
        {
            return "CloseTokensRegisterEvent";
        }
    }
}
