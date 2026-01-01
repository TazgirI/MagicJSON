package net.tazgirl.magicjson;

import net.tazgirl.magicjson.data.Constants;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class MJLogging
{
    static Logger localLogger = MagicJson.LOGGER;


    //BEFOREBUILD: REVIEW
    static String currentBuildInformation = "\nThe current build is Alpha 0.1.0, no other loggable build information is present";



    public static void Warn(@NotNull String warnMessage)
    {
        if(Constants.chatDebugFlags)
        {
            SendMessage.All(warnMessage);
        }

        localLogger.warn(warnMessage.concat(currentBuildInformation));
    }

    public static void Error(@NotNull String errorMessage)
    {
        if(Constants.chatDebugFlags)
        {
            SendMessage.All(errorMessage);
        }

        localLogger.error(errorMessage.concat(currentBuildInformation));
    }

    public static void Debug(@NotNull String debugMessage)
    {
        if(Constants.chatDebugFlags)
        {
            SendMessage.All(debugMessage);
        }

        localLogger.debug(debugMessage);
    }

    public static void Info(@NotNull String infoMessage)
    {
        if(Constants.chatDebugFlags)
        {
            SendMessage.All(infoMessage);
        }

        localLogger.info(infoMessage);
    }
}
