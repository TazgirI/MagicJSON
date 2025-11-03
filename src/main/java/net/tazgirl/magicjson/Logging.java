package net.tazgirl.magicjson;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.annotation.Nullable;

public class Logging
{
    static Logger localLogger = MagicJson.LOGGER;

    public static void Log(@NotNull String message, @Nullable Logger callerLogger)
    {
        logCheck(callerLogger).info(message);
    }

    public static void Warn(@NotNull String message, @Nullable Logger callerLogger)
    {
        logCheck(callerLogger).warn(message);
    }

    public static void Error(@NotNull String message, @Nullable Logger callerLogger)
    {
        logCheck(callerLogger).error(message);
    }

    public static void Debug(@NotNull String message, @Nullable Logger callerLogger)
    {
        logCheck(callerLogger).debug(message);
    }

    public static void LogAndTell(@NotNull String message, @Nullable Logger callerLogger)
    {
        logCheck(callerLogger).info(message);
        SendMessage.All(message);
    }



    static Logger logCheck(@Nullable Logger logger)
    {
        return logger != null ? logger : localLogger;
    }
}
