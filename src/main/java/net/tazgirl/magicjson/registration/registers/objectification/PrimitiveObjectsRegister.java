package net.tazgirl.magicjson.registration.registers.objectification;

import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.PrimitiveInitRecord;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;

public class PrimitiveObjectsRegister extends MapRegister<String, PrimitiveInitRecord>
{

    @Override
    public String getIdentifier()
    {
        return "PrimitivesRegister";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return new FetchEvent();
    }

    public static class FetchEvent extends MapRegisterFetchEvent<String, PrimitiveInitRecord>
    {
        @Override
        public String getIdentifier()
        {
            return "PrimitivesRegisterEvent";
        }

        @Override
        public PrimitiveInitRecord put(String address, PrimitiveInitRecord value)
        {
            if(!address.contains(":") || address.contains("/")){
                MJLogging.Warn("Attempted to register a PrimitiveObject without using the correct form of \"namespace:token\", the attempted token registration for \"" + address + "\" has been skipped"); return null;}

            String synonym = RegistersForProcessing.tokenSynonyms.get(address);

            if(synonym != null){address = synonym.trim().toLowerCase();}
            if(address.contains(":")){address = address.substring(address.lastIndexOf(':') + 1).trim().toLowerCase();}

            return super.put(address, value);
        }
    }
}
