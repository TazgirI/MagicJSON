package net.tazgirl.magicjson.registration.registers.tokenisation;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.magicjson_events.registers.bases.ListRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.lang.reflect.InvocationTargetException;

public class StatementObjectTokensRegister extends MapRegister<String, Class<? extends Base>>
{
    public Base getObjectFromToken(String token, StatementHolder holder)
    {
        Class<? extends Base> tokenObjectClass = register.get(token.toLowerCase());

        if(tokenObjectClass != null)
        {
            try
            {
                return tokenObjectClass.getDeclaredConstructor(StatementHolder.class).newInstance(holder);
            }
            catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }


        Logging.Debug("Attempted to get an object from unrecognised token: " + token);
        return null;
    }

    @Override
    public String getIdentifier()
    {
        return "StatementObjectTokensRegister";
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
            return "StatementObjectTokensRegisterEvent";
        }
    }
}
