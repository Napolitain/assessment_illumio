package boucher.maxime;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlowLogAnalyserTest extends FlowLogAnalyser {

    private FlowLogAnalyser flowLogAnalyser;

    @BeforeMethod
    public void setUp() throws IOException {
        // TODO: this part should eventually be removed and replaced with more proper test setup
        flowLogAnalyser = new FlowLogAnalyser();
        var lookupStream = Main.class.getResourceAsStream("/lookup.csv");
        var flowLogsStream = Main.class.getResourceAsStream("/flow_logs.csv");
        var lookupReader = new java.io.InputStreamReader(lookupStream);
        var flowLogsReader = new java.io.InputStreamReader(flowLogsStream);
        flowLogAnalyser.addLookupTableEntries(lookupReader);
        flowLogAnalyser.parseFlowLogs(flowLogsReader);
    }

    @AfterMethod
    public void tearDown() {
        flowLogAnalyser = null;
    }

    @Test
    public void testTestCountMatches() {
        var tagCounts = flowLogAnalyser.countMatches();
        var total = 0;
        for (var count : tagCounts.values()) {
            total += count;
        }
        Assert.assertEquals(total, 14);
        Assert.assertEquals(tagCounts.get("sv_P2").intValue(), 1);
        Assert.assertEquals(tagCounts.get("sv_P1").intValue(), 2);
        Assert.assertEquals(tagCounts.get("email").intValue(), 3);
        Assert.assertEquals(tagCounts.get("Untagged").intValue(), 8);
    }

    @Test
    public void testTestCountPortProtocolCombinations() {
        var total = 0;
        for (var count : flowLogAnalyser.countPortProtocolCombinations().values()) {
            total += count;
        }
        Assert.assertEquals(total, 14);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("23,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("993,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("80,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("49155,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("25,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("49157,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("443,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("110,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("49153,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("49158,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("143,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("49156,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("49154,TCP").intValue(), 1);
        Assert.assertEquals(flowLogAnalyser.countPortProtocolCombinations().get("1024,TCP").intValue(), 1);
    }
}