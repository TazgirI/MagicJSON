package net.tazgirl.magicjson.registration.registers.objectification;

import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.PrimitiveInitRecord;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;

public class StatementKeywordsRegister extends MapRegister<String, String>
{
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
