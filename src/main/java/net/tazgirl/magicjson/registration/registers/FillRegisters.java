package net.tazgirl.magicjson.registration.registers;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.registration.PrimitiveInitRecord;
import net.tazgirl.magicjson.registration.registers.objectification.CloseTokensRegister;
import net.tazgirl.magicjson.registration.registers.objectification.LeadCharTokensRegister;
import net.tazgirl.magicjson.registration.registers.objectification.PrimitiveObjectsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.AppendHookRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.EndCharsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.ExcludeCharsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.SoloCharsRegister;
import net.tazgirl.magicjson.registration.registers.objectification.StatementObjectTokensRegister;
import net.tazgirl.magicjson.statements.hooks.LivingDamageEventStatementObject;
import net.tazgirl.magicjson.statements.objects.StringMutators.Concat;
import net.tazgirl.magicjson.statements.objects.evaluation.Equals;
import net.tazgirl.magicjson.statements.objects.flow.If;
import net.tazgirl.magicjson.statements.objects.memory.args.ArgGet;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.complex_numeric.Gaussian;
import net.tazgirl.magicjson.statements.objects.complex_numeric.Random;
import net.tazgirl.magicjson.statements.objects.compounds.And;
import net.tazgirl.magicjson.statements.objects.compounds.Or;
import net.tazgirl.magicjson.statements.objects.minecraft.Teleport;
import net.tazgirl.magicjson.statements.objects.numeric_evaluators.GreaterThan;
import net.tazgirl.magicjson.statements.objects.numeric_evaluators.LessThan;
import net.tazgirl.magicjson.statements.objects.numeric_evaluators.NumericEquals;
import net.tazgirl.magicjson.statements.objects.numerical_mutators.Add;
import net.tazgirl.magicjson.statements.objects.numerical_mutators.Divide;
import net.tazgirl.magicjson.statements.objects.numerical_mutators.Subtract;
import net.tazgirl.magicjson.statements.objects.primitives.BooleanObject;
import net.tazgirl.magicjson.statements.objects.primitives.NullObject;
import net.tazgirl.magicjson.statements.objects.primitives.StringObject;

import java.util.List;
import java.util.Map;

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
        event.put('{');
        event.put('}');
    }

    @SubscribeEvent
    public static void fillSoloChars(SoloCharsRegister.FetchEvent event)
    {
        event.put('(');
        event.put(')');
        event.put(' ');
        event.put('{');
        event.put('}');
    }

    @SubscribeEvent
    public static void fillExcludeChars(ExcludeCharsRegister.FetchEvent event)
    {

    }

    // REMEMBER TAZ, LEAD CHAR IS PRE-REMOVED
    @SubscribeEvent
    public static void fillLeadCharTokens(LeadCharTokensRegister.FetchEvent event)
    {
        // Full stop entry is only here to make sure no-one tries to register behaviour that can't be hit as it's
        event.put('.', null);

        // Any token beginning with '_' is treated as shorthand for an arg fetch
        // Arg("waves") and _waves will both create the same object
        event.put('_', (string, holder) ->
        {
            ArgGet argGet = new ArgGet(holder);
            argGet.handleBase(new StringObject(holder, string));
            return argGet;
        });

        // Any token beginning with '"' has the first and last character stripped and is then passed as a StringObjectpo
        event.put('"', (string, holder) ->
        {
            // If there is another character plus the ending speech mark
            if(!(string.length() >= 2))
            {
                return new StringObject(holder, string.substring(0, string.length() - 1));
            }
            else
            {
                MJLogging.debug("Detected string '" + "\"" + string + "'  but is too short to process");
                return null;
            }
        });


    }

    private static final Map<List<String>, Class<? extends Base>> multiples =
            Map.ofEntries(
                    Map.entry(List.of("and", "&"), And.class),
                    Map.entry(List.of("or", "||"), Or.class),

                    // FEAT: Allow <= and >= to init with unique argument of .equals
                    Map.entry(List.of("lessthan", "<"), LessThan.class),
                    Map.entry(List.of("greaterthan", ">"), GreaterThan.class),
                    Map.entry(List.of("equals","=", "=="), Equals.class),
                    Map.entry(List.of("numequals","num=", "num=="), NumericEquals.class),



                    Map.entry(List.of("div", "/"), Divide.Full.class),
                    Map.entry(List.of("mod", "%"), Divide.Modulus.class),
                    Map.entry(List.of("add", "+", "plus"), Add.class),
                    Map.entry(List.of("sub", "-", "minus"), Subtract.class),

                    Map.entry(List.of("gaussian", "gauss"), Gaussian.class),


                    Map.entry(List.of("livingdamage", "living_damage"), LivingDamageEventStatementObject.class)
            );

    final static Map<String, Class<? extends Base>> singles = Map.of(
            "arg", ArgGet.class,

            "concat", Concat.class,

            "random", Random.class,

            "if", If.class,

            "teleport", Teleport.class,

            "livingdamage", LivingDamageEventStatementObject.class

    );

    @SubscribeEvent
    public static void fillStatementObjectTokens(StatementObjectTokensRegister.FetchEvent event)
    {
        for(Map.Entry<List<String>, Class<? extends Base>> entry : multiples.entrySet())
        {
            event.put(entry.getKey(), "magicjson", entry.getValue());
        }

        for(Map.Entry<String, Class<? extends Base>> entry : singles.entrySet())
        {
            event.put("magicjson:" + entry.getKey(), entry.getValue());
        }
    }

    @SubscribeEvent
    public static void fillCloseTokens(CloseTokensRegister.FetchEvent event)
    {
        event.put(")");
        event.put(" ");
        event.put("}");
    }

    @SubscribeEvent
    public static void fillPrimitives(PrimitiveObjectsRegister.FetchEvent event)
    {
        event.put("magicjson:true",new PrimitiveInitRecord(BooleanObject.class, true));
        event.put("magicjson:false",new PrimitiveInitRecord(BooleanObject.class, false));
        event.put("magicjson:null",new PrimitiveInitRecord(NullObject.class, null));
    }

    @SubscribeEvent
    public static void fillAppendHooks(AppendHookRegister.FetchEvent event)
    {

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
