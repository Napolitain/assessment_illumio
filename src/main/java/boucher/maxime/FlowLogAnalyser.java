package boucher.maxime;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FlowLogAnalyser implements LogAnalyser {
    private final LookupTable lookupTable;
    private final FlowLogParser flowLogParser;

    public FlowLogAnalyser() {
        this.flowLogParser = new FlowLogParser();
        this.lookupTable = new LookupTable();
    }

    public void addLookupTableEntries(File lut) throws IOException {
        lookupTable.addEntries(lut);
    }

    public void addLookupTableEntries(java.io.Reader lut) throws IOException {
        lookupTable.addEntries(lut);
    }

    public void parseFlowLogs(File flowLogs) throws IOException {
        flowLogParser.parse(flowLogs);
    }

    public void parseFlowLogs(java.io.Reader flowLogs) throws IOException {
        flowLogParser.parse(flowLogs);
    }

    @Override
    public HashMap<String, Integer> countMatches() {
        var tagCounts = new HashMap<String, Integer>();
        for (FlowLog flowLog : flowLogParser.getFlowLogs()) {
            String tag = lookupTable.getTag(flowLog.dstPort(), flowLog.protocol().ordinal());
            tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
        }

        return tagCounts;
    }

    @Override
    public HashMap<String, Integer> countPortProtocolCombinations() {
        var portProtocolCounts = new HashMap<String, Integer>();
        for (FlowLog flowLog : flowLogParser.getFlowLogs()) {
            String key = flowLog.dstPort() + "," + flowLog.protocol();
            portProtocolCounts.put(key, portProtocolCounts.getOrDefault(key, 0) + 1);
        }

        return portProtocolCounts;
    }

    public void printTagCounts() {
        var tagCounts = countMatches();
        System.out.println("Tag Counts:");
        System.out.println("Tag,Count");
        tagCounts.forEach((tag, count) -> System.out.println(tag + "," + count));
    }

    public void printPortProtocolCombinations() {
        var portProtocolCounts = countPortProtocolCombinations();
        System.out.println("Port/Protocol Combination Counts:");
        System.out.println("Port,Protocol,Count");
        portProtocolCounts.forEach((key, count) -> System.out.println(key + "," + count));
    }
}
