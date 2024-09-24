package boucher.maxime;

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
    public void testTestAddLookupTableEntries() {
    }

    @Test
    public void testTestParseFlowLogs() {
    }

    @Test
    public void testTestCountMatches() {
        var tagCounts = flowLogAnalyser.countMatches();
        System.out.println("Tag Counts:");
        System.out.println("Tag,Count");
        tagCounts.forEach((tag, count) -> System.out.println(tag + "," + count));
    }

    @Test
    public void testTestCountPortProtocolCombinations() {
        var portProtocolCounts = flowLogAnalyser.countPortProtocolCombinations();
        System.out.println("Port/Protocol Combination Counts:");
        System.out.println("Port,Protocol,Count");
        portProtocolCounts.forEach((key, count) -> System.out.println(key + "," + count));
    }
}