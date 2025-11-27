package net.tazgirl.magicjson.registration;

import net.tazgirl.magicjson.registration.registers.HookTokenSynonymsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.EndCharsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.ExcludeCharsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.SoloCharsRegister;
import net.tazgirl.magicjson.registration.registers.tokenisation.StatementObjectTokensRegister;

public class Registers
{
    // Tokens

    public static EndCharsRegister endChars = new EndCharsRegister();
    public static SoloCharsRegister soloChars = new SoloCharsRegister();
    public static ExcludeCharsRegister excludeChars = new ExcludeCharsRegister();
    public static StatementObjectTokensRegister statementObjects = new StatementObjectTokensRegister();


    public static HookTokenSynonymsRegister hookTokenSynonyms = new HookTokenSynonymsRegister();



}
