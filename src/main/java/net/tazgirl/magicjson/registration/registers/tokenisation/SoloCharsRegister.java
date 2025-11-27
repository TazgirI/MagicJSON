package net.tazgirl.magicjson.registration.registers.tokenisation;

import net.tazgirl.magicjson.magicjson_events.registers.bases.ListRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.ListRegister;

public class SoloCharsRegister extends ListRegister<Character>
{

    @Override
    public String getIdentifier()
    {
        return "SoloCharsRegister";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return new FetchEvent();
    }

    public static class FetchEvent extends ListRegisterFetchEvent<Character>
    {

        @Override
        public String getIdentifier()
        {
            return "SoloCharsRegisterEvent";
        }
    }
}
