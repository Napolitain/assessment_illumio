package boucher.maxime;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;

public class FlowLogAnalyserTest extends FlowLogAnalyser {

    private FlowLogAnalyser flowLogAnalyser;

    @BeforeMethod
    public void setUp() throws IOException {
        // TODO: this part should eventually be removed and replaced with more proper test setup
        var rootPath = Path.of(System.getProperty("user.dir"));
        var lookupPath = Path.of(rootPath.toString(), "src/test/resources/lookup.csv");
        var flowLogsPath = Path.of(rootPath.toString(), "src/test/resources/flow_logs.csv");
        // Join 2 path
        flowLogAnalyser = new FlowLogAnalyser();
        flowLogAnalyser.addLookupTableEntries(lookupPath.toFile());
        flowLogAnalyser.parseFlowLogs(flowLogsPath.toFile());
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