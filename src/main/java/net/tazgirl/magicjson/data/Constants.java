package net.tazgirl.magicjson.data;

import net.minecraft.server.MinecraftServer;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.registration.registers.base.Register;

import java.util.List;

public class Constants
{
    public static boolean loadingComplete = false;

    public static final String hookSynonymAddressElement = "address";
    public static final String hookSynonymSynonymElement = "synonym";

    public static final List<Register<?>> allRegisters = RegistersForProcessing.allRegisters;

    public static MinecraftServer server;

    public static boolean isTesting = false;

    public static enum trueEmpty
    {
        TRUE_EMPTY
    }
}
