package net.tazgirl.magicjson;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class Logging
{
    static Logger localLogger = MagicJson.LOGGER;


    //BEFOREBUILD: REVIEW
    static String currentBuildInformation = "\nThe current build is Alpha 0.1, no other loggable build information is present";



    public static void Warn(@NotNull String warnMessage)
    {
        localLogger.warn(warnMessage.concat(currentBuildInformation));
    }

    public static void Error(@NotNull String errorMessage)
    {
        localLogger.error(errorMessage.concat(currentBuildInformation));
    }

    public static void Debug(@NotNull String debugMessage)
    {
        localLogger.debug(debugMessage);

    }

    public static void Info(@NotNull String infoMessage)
    {
        localLogger.info(infoMessage);
    }
}
