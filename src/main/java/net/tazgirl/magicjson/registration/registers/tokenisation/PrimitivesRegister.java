package net.tazgirl.magicjson.registration.registers.tokenisation;

import net.tazgirl.magicjson.magicjson_events.registers.bases.ListRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.ListRegister;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;
import net.tazgirl.magicjson.statements.objects.Base;

public class PrimitivesRegister extends MapRegister<String, Class<? extends Base>>
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

    public static class FetchEvent extends MapRegisterFetchEvent<String, Class<? extends Base>>
    {
        @Override
        public String getIdentifier()
        {
            return "PrimitivesRegisterEvent";
        }
    }
}
