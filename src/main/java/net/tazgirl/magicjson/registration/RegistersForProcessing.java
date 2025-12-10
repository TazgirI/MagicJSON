package net.tazgirl.magicjson.registration;

import net.neoforged.fml.common.EventBusSubscriber;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.registration.registers.base.Register;
import net.tazgirl.magicjson.registration.registers.objectification.*;
import net.tazgirl.magicjson.registration.registers.tokenisation.EndCharsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.ExcludeCharsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.SoloCharsRegister;

import java.util.List;

@EventBusSubscriber(modid = MagicJson.MODID)
public class RegistersForProcessing
{
    // Tokenisation
    public static EndCharsRegister endChars = new EndCharsRegister();
    public static ExcludeCharsRegister excludeChars = new ExcludeCharsRegister();
    public static SoloCharsRegister soloChars = new SoloCharsRegister();


    // Objectification
    public static CloseTokensRegister closeTokens = new CloseTokensRegister();
    public static PrimitiveObjectsRegister primitiveObjects = new PrimitiveObjectsRegister();
    public static StatementObjectTokensRegister statementObjects = new StatementObjectTokensRegister();
//    public static UniqueArgumentsRegister uniqueArguments = new UniqueArgumentsRegister();

    // Alternate processing
    public static TokenSynonymsRegister tokenSynonyms = new TokenSynonymsRegister();

    // WARN: tokenSynonyms MUST come before primitiveObjects and statementObjects
    public static List<Register<?>> allRegisters = List.of(endChars, excludeChars, soloChars, closeTokens, tokenSynonyms, primitiveObjects, statementObjects);

    static boolean hasFired = false;


    public static void FireAllRegisterFetches()
    {
        if(!hasFired)
        {
            hasFired = true;

            for(Register<?> register: allRegisters)
            {
                register.fireAndStoreStaticEvent();
            }

        }
    }
}
