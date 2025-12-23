package net.tazgirl.magicjson.registration.registers.objectification;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class StatementObjectTokensRegister extends MapRegister<String, Class<? extends Base>>
{
    public Base getAsObject(String token, StatementHolder holder)
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

        @Override
        public Class<? extends Base> put(String address, Class<? extends Base> value)
        {
            if(!address.contains(":") || address.contains("/")){Logging.Warn("Attempted to register a StatementObject without using the correct form of \"namespace:token\", subfolders such as \"namespace:mutators/token\" are not allowed for token addresses. The attempted token registration for \"" + address + "\" has been skipped"); return null;}

            String synonym = RegistersForProcessing.tokenSynonyms.get(address);

            if(synonym != null){address = synonym.trim().toLowerCase();}
            else{address = address.substring(address.lastIndexOf(':') + 1).trim().toLowerCase();}

            return super.put(address, value);
        }

        public Class<? extends Base> put(String keyword, String namespace, Class<? extends Base> value)
        {
            return put(namespace + ":" + keyword, value);
        }

        public List<Class<? extends Base>> put(List<String> addresses, Class<? extends Base> value)
        {
            List<Class<? extends Base>> classes = new ArrayList<>();
            for(String string: addresses)
            {
                classes.add(put(string, value));
            }

            return classes;
        }

        public List<Class<? extends Base>> put(List<String> keywords, String namespace, Class<? extends Base> value)
        {
            // Ensuring mutability just in case, cba to make sure
            keywords = new ArrayList<>(keywords);

            keywords.replaceAll(string -> namespace + ":" + string);

            return put(keywords, value);
        }


    }
}
