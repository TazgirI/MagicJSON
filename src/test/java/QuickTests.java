import net.tazgirl.magicjson.old.main.TokenToStatement;
import net.tazgirl.magicjson.old.main.Tokenise;
import org.junit.jupiter.api.Test;

public class QuickTests
{

    @Test
    void quickTest()
    {
        Print(TokenToStatement.objectFromTokens(Tokenise.TokeniseStatement("\"test\""), "test").toString());
    }

    static void Print(String string)
    {
        System.out.println(string);
    }
}
