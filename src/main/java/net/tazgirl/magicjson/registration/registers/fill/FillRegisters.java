package net.tazgirl.magicjson.registration.registers.fill;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.registration.InitRecord;
import net.tazgirl.magicjson.registration.registers.objectification.CloseTokensRegister;
import net.tazgirl.magicjson.registration.registers.objectification.PrimitiveObjectsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.EndCharsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.ExcludeCharsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.SoloCharsRegister;
import net.tazgirl.magicjson.registration.registers.objectification.StatementObjectTokensRegister;
import net.tazgirl.magicjson.statements.objects.Arg;
import net.tazgirl.magicjson.statements.objects.numerical_mutators.Add;
import net.tazgirl.magicjson.statements.objects.numerical_mutators.Divide;
import net.tazgirl.magicjson.statements.objects.numerical_mutators.Subtract;
import net.tazgirl.magicjson.statements.objects.primitives.BooleanObject;
import net.tazgirl.magicjson.statements.objects.primitives.NullObject;

import java.util.List;

@EventBusSubscriber(modid = MagicJson.MODID)
public class FillRegisters
{
    @SubscribeEvent
    public static void fillEndChars(EndCharsRegister.FetchEvent event)
    {
        event.put('(');
        event.put(')');
        event.put(' ');
        event.put('.');
    }

    @SubscribeEvent
    public static void fillSoloChars(SoloCharsRegister.FetchEvent event)
    {
        event.put('(');
        event.put(')');
        event.put(' ');
    }

    @SubscribeEvent
    public static void fillExcludeChars(ExcludeCharsRegister.FetchEvent event)
    {
        event.put(' ');
    }

    @SubscribeEvent
    public static void fillStatementObjectTokens(StatementObjectTokensRegister.FetchEvent event)
    {
        event.put("magicjson:arg", Arg.class);

        event.put(List.of("div","/"), "magicjson",Divide.Full.class);
        event.put(List.of("mod","%"), "magicjson",Divide.Full.class);
        event.put(List.of("add","+","plus"), "magicjson", Add.class);
        event.put(List.of("sub","-","minus"), "magicjson", Subtract.class);
    }

    @SubscribeEvent
    public static void fillCloseChars(CloseTokensRegister.FetchEvent event)
    {
        event.put(")");
    }

    @SubscribeEvent
    public static void fillPrimitives(PrimitiveObjectsRegister.FetchEvent event)
    {
        event.put("magicjson:true",new InitRecord(BooleanObject.class, true));
        event.put("magicjson:false",new InitRecord(BooleanObject.class, false));
        event.put("magicjson:null",new InitRecord(NullObject.class, null));
    }

//    @SubscribeEvent
//    public static void fillUniqueArguments(UniqueArgumentsRegister.FetchEvent event)
//    {
//        event.put(".integer",Integer.class);
//        event.put(".int", Integer.class);
//        event.put(".float", Float.class);
//        event.put(".long", Long.class);
//        event.put(".double", Double.class);
//    }
}
