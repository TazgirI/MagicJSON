package net.tazgirl.magicjson.registration.registers.objectification;

import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;

import java.util.List;

public class StatementKeywordsRegister extends MapRegister<String, String>
{
    public StatementKeywordsRegister()
    {
        registersToDuplicateCheck = List.of(RegistersForProcessing.statementObjects, RegistersForProcessing.primitiveObjects);
    }

    @Override
    protected String getIdentifier()
    {
        return "StatementKeywordsRegister";
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
            return "StatementKeywordsRegisterEvent";
        }

        @Override
        public String put(String address, String value)
        {
            return super.put(address, value);
        }
    }
}
