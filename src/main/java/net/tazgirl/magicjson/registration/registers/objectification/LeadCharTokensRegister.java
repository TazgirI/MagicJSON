package net.tazgirl.magicjson.registration.registers.objectification;

import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;
import net.tazgirl.magicjson.statements.objects.Base;

import java.util.function.Function;

public class LeadTokenCharsRegister extends MapRegister<String, Function<String, Base>>
{
    @Override
    protected String getIdentifier()
    {
        return "LeadTokenChars";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return null;
    }

    public static class FetchEvent extends MapRegisterFetchEvent<String, Function<String, Base>>
    {

        @Override
        public String getIdentifier()
        {
            return "LeadTokenCharsEvent";
        }
    }
}
