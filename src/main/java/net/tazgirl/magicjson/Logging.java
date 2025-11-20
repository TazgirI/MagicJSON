package net.tazgirl.magicjson;

import net.tazgirl.magicjson.old.SendMessage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class Logging
{
    static Logger localLogger = MagicJson.LOGGER;


    //BEFOREBUILD: REVIEW
    static String currentBuildInformation = "\nThe current build is Alpha 0.1, no other loggable build information is present";



    public static void Warn(@NotNull String warnMessage, boolean chatFlag)
    {
        localLogger.warn(warnMessage.concat(currentBuildInformation));

        if(chatFlag)
        {
            SendMessage.All(warnMessage);
        }
    }

    public static void Error(@NotNull String errorMessage, boolean chatFlag)
    {
        localLogger.error(errorMessage.concat(currentBuildInformation));

        if(chatFlag)
        {
            SendMessage.All(errorMessage);
        }
    }

    public static void Debug(@NotNull String debugMessage, boolean chatFlag)
    {
        localLogger.debug(debugMessage);

        if(chatFlag)
        {
            SendMessage.All(debugMessage);
        }
    }

    public static void Info(@NotNull String infoMessage, boolean chatFlag)
    {
        localLogger.info(infoMessage);

        if(chatFlag)
        {
            SendMessage.All(infoMessage);
        }
    }

    public enum LogType
    {
        INFO,
        DEBUG,
        WARN,
        ERROR,

    }

}
