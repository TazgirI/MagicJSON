package net.tazgirl.magicjson.helpers;

import java.util.List;
import java.util.Map;

public class ResourceLocationToStatementAddress
{
    // Make sure to exit after first hit to prevent too much removal
    public static List<String> resourceLocationReplacements = List.of(
            ":magicjson/statement/",
            ":magicjson/function/",
            // The entrys below this line are only backups to catch shorthand
            ":statement/",
            ":function/"
    );

    public static String handle(String location)
    {
        if(!location.contains(":")){return null;}

        for(String stringToReplace : resourceLocationReplacements)
        {
            if(location.contains(stringToReplace))
            {
                location = location.replace(stringToReplace,":");
                break;
            }
        }

        int indexSubtraction = location.contains(".") ? location.substring(location.lastIndexOf('.')).length() : 0;

        location = location.substring(0, location.length() - indexSubtraction);


        return location;
    }
}
