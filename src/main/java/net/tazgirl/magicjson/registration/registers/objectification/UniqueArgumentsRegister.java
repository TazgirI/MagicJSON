//package net.tazgirl.magicjson.registration.registers.objectification;
//
//import net.tazgirl.magicjson.magicjson_events.registers.bases.MapRegisterFetchEvent;
//import net.tazgirl.magicjson.magicjson_events.registers.bases.RegisterFetchEventRoot;
//import net.tazgirl.magicjson.registration.registers.base.MapRegister;
//import net.tazgirl.magicjson.statements.hooks.base.Hook;
//import org.checkerframework.checker.units.qual.K;
//
//public class UniqueArgumentsRegister extends MapRegister<String, Object>
//{
//    @Override
//    public String getIdentifier()
//    {
//        return "UniqueArgumentsRegister";
//    }
//
//    @Override
//    public RegisterFetchEventRoot<?> getFreshStaticEvent()
//    {
//        return new FetchEvent();
//    }
//
//    @Override
//    public Object put(String key, Object value)
//    {
//        if(key.charAt(0) != '.'){key = "." + key;}
//
//        return super.put(key, value);
//    }
//
//    @Override
//    public Boolean containsKey(String key)
//    {
//        if(key.charAt(0) != '.'){key = "." + key;}
//
//        return super.containsKey(key);
//    }
//
//    @Override
//    public Object get(String key)
//    {
//        if(key.charAt(0) != '.'){key = "." + key;}
//
//        if(super.get(key) instanceof Object object)
//        {
//            return object;
//        }
//        else
//        {
//            return key;
//        }
//    }
//
//    public static class FetchEvent extends MapRegisterFetchEvent<String, Object>
//    {
//        @Override
//        public String getIdentifier()
//        {
//            return "UniqueArgumentsRegisterEvent";
//        }
//    }
//}
