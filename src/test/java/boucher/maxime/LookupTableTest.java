package boucher.maxime;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LookupTableTest {

    @Test
    public void testAddEntries() throws IOException {
        var lookupStream = Main.class.getResourceAsStream("/lookup.csv");
        LookupTable lookupTable = new LookupTable();
        var lookupReader = new java.io.InputStreamReader(lookupStream);
        lookupTable.addEntries(lookupReader);

        var total = 0;
        for (var tag : lookupTable.getTags()) {
            total++;
        }
        Assert.assertEquals(total, 11);
    }

}