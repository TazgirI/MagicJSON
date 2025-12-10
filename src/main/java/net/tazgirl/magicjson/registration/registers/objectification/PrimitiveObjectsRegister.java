package net.tazgirl.magicjson.registration.registers.objectification;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.InitRecord;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;

public class PrimitiveObjectsRegister extends MapRegister<String, InitRecord>
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

    public static class FetchEvent extends MapRegisterFetchEvent<String, InitRecord>
    {
        @Override
        public String getIdentifier()
        {
            return "PrimitivesRegisterEvent";
        }

        @Override
        public InitRecord put(String address, InitRecord value)
        {
            if(!address.contains(":") || address.contains("/")){
                Logging.Warn("Attempted to register a PrimitiveObject without using the correct form of \"namespace:token\", the attempted token registration for \"" + address + "\" has been skipped"); return null;}

            String synonym = RegistersForProcessing.tokenSynonyms.get(address);

            if(synonym != null){address = synonym.trim().toLowerCase();}
            if(address.contains(":")){address = address.substring(address.lastIndexOf(':') + 1).trim().toLowerCase();}

            return super.put(address, value);
        }
    }
}
