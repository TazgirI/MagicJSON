package net.tazgirl.magicjson.registration.registers.objectification;

import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.MapRegister;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.checkerframework.checker.units.qual.C;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LeadCharTokensRegister extends MapRegister<Character, BiFunction<String, StatementHolder, Base>>
{
    // TODO: This works however currently only the final object is properly added to the stack in a way that stores children relations. i.e if you have ArgGet(StringObject()) then the String object thats created within the BifFunction cannot be found by any "meta" objects or code that alter the structure of constructed statements

    @Override
    protected String getIdentifier()
    {
        return "LeadCharTokens";
    }

    @Override
    public RegisterFetchEventRoot<?> getFreshStaticEvent()
    {
        return null;
    }

    public static class FetchEvent extends MapRegisterFetchEvent<Character, BiFunction<String, StatementHolder, Base>>
    {

        @Override
        public String getIdentifier()
        {
            return "LeadCharTokensEvent";
        }
    }
}
