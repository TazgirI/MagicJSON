import net.tazgirl.magicjson.Registration;
import net.tazgirl.magicjson.main.DefaultValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationResourceLocationToAddressTests extends TestRoot
{

    @Test
    void mostSimpleUsecase()
    {
        assertEqualsSuper("magicjson:test", Registration.ResourceLocationToAddress("magicjson:magicjson/statement/test.json"),"mostSimpleUsecase");
    }

    @Test
    void subfolderConservation()
    {
        assertEqualsSuper("magicjson:testfolder/test", Registration.ResourceLocationToAddress("magicjson:magicjson/statement/testfolder/test.json"),"subfolderConservation");
    }

}
