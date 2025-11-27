package net.tazgirl.magicjson.registration.registers.tokenisation;

import net.tazgirl.magicjson.magicjson_events.registers.bases.ListRegisterFetchEvent;
import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
import net.tazgirl.magicjson.registration.registers.base.ListRegister;

public class ExcludeCharsRegister extends ListRegister<Character>
{
    public boolean isStringExcludable(String string)
    {
        return (string.length() == 1 && register.contains(string.charAt(0)) || string.isEmpty());
    }

    @Override
    public String getIdentifier()
    {
        return "ExcludeCharsRegister";
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
            return "ExcludeTokensRegisterEvent";
        }
    }
}
