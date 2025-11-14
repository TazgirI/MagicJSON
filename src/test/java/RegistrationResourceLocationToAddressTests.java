import net.tazgirl.magicjson.Registration;
import net.tazgirl.magicjson.main.DefaultValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationResourceLocationToAddressTests
{

    @Test
    void mostSimpleUsecase()
    {
        assertEquals("magicjson:test", Registration.ResourceLocationToAddress("magicjson:magicjson/statement/test.json"));
    }

    @Test
    void subfolderConservation()
    {
        assertEquals("magicjson:testfolder/test", Registration.ResourceLocationToAddress("magicjson:magicjson/statement/testfolder/test.json"));
    }

}
