package net.tazgirl.magicjson;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.tazgirl.magicjson.helpers.ResourceLocationToStatementAddress;
import net.tazgirl.magicjson.processing.Tokenisation;
import net.tazgirl.magicjson.processing.TokensToHolder;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RunStatementProcessing
{
    public static void ProcessStatements(ServerStartingEvent event)
    {
        for(Map.Entry<ResourceLocation, Resource> entry : GetAllStatements(event).entrySet())
        {
            try(InputStream inputStream = entry.getValue().open())
            {
                String resourceAddress = entry.getKey().toString();
                String address = ResourceLocationToStatementAddress.handle(resourceAddress);

                Tokenisation tokeniser = new Tokenisation(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
                TokensToHolder objectiser = new TokensToHolder(tokeniser, entry.getKey().toString());

                StatementHolder constructedStatement = objectiser.process();

                PrivateCore.putStatement(address, constructedStatement);
            }
            catch (IOException e)
            {
                MJLogging.Warn(entry.getKey().toString() + " could not be opened, skipping file");
                continue;
            }
        }
    }

    public static Map<ResourceLocation, Resource> GetAllStatements(ServerStartingEvent event)
    {
        ResourceManager resourceManager = event.getServer().getResourceManager();

        return resourceManager.listResources("magicjson/statement", path -> path.getPath().endsWith(".txt"));
    }

}
