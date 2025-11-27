package net.tazgirl.magicjson.helpers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamToJson
{

    public static JsonObject getJson(InputStream inputStream)
    {
        if(JsonParser.parseReader(new InputStreamReader(inputStream)) instanceof JsonObject jsonObject)
        {
            return jsonObject;
        }
        return null;
    }

}
