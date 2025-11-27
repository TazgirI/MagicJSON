package net.tazgirl.magicjson.registration.registers.tokenisation;

import net.neoforged.neoforge.common.NeoForge;
import net.tazgirl.magicjson.magicjson_events.registers.bases.ListRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.ListRegister;

import java.util.Arrays;
import java.util.List;

public class EndCharsRegister extends ListRegister<Character>
{
    // WARN: Register and RegisterFetchEvent parents must have exact same type param


    @Override
    public String getIdentifier()
    {
        return "EndCharsRegister";
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
            return "EndCharsRegisterEvent";
        }
    }
}
