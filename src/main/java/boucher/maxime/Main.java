package boucher.maxime;

import java.io.IOException;
import java.nio.file.Path;

public final class Main {

    public static void main(String[] args) throws IOException {
        // Create a new FlowLogAnalyser instance
        FlowLogAnalyser flowLogAnalyser = new FlowLogAnalyser();

        // Arguments example : /path/to/lookup.csv /path/to/flow_logs.csv
        try {
            var lookupPath = Path.of(args[0]);
            var flowLogsPath = Path.of(args[1]);
            flowLogAnalyser.addLookupTableEntries(lookupPath.toFile());
            flowLogAnalyser.parseFlowLogs(flowLogsPath.toFile());
        } catch (ArrayIndexOutOfBoundsException e) {
            var lookupStream = Main.class.getResourceAsStream("/lookup.csv");
            var flowLogsStream = Main.class.getResourceAsStream("/flow_logs.csv");
            var lookupReader = new java.io.InputStreamReader(lookupStream);
            var flowLogsReader = new java.io.InputStreamReader(flowLogsStream);
            flowLogAnalyser.addLookupTableEntries(lookupReader);
            flowLogAnalyser.parseFlowLogs(flowLogsReader);
        }

        // Print the tag counts and port/protocol combinations
        flowLogAnalyser.printTagCounts();
        flowLogAnalyser.printPortProtocolCombinations();
    }

}
