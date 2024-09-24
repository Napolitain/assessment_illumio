package boucher.maxime;

import java.util.ArrayList;
import java.util.List;

public class FlowLogParser implements LogParser {
    private final List<FlowLog> flowLogs;

    public FlowLogParser() {
        flowLogs = new ArrayList<>();
    }

    @Override
    public void parse(String log) {
        flowLogs.clear();
        String[] lines = log.split(System.lineSeparator());
        for (String line : lines) {
            String[] fields = line.split(" ");
            if (fields.length == 14) {
                flowLogs.add(new FlowLog(
                        Integer.parseInt(fields[0]),
                        Long.parseLong(fields[1]),
                        fields[2],
                        fields[3],
                        fields[4],
                        Integer.parseInt(fields[5]),
                        Integer.parseInt(fields[6]),
                        ProtocolNumber.protocolFromValue(fields[7]),
                        Integer.parseInt(fields[8]),
                        Integer.parseInt(fields[9]),
                        Long.parseLong(fields[10]),
                        Long.parseLong(fields[11]),
                        fields[12],
                        fields[13]
                ));
            }
        }
    }

    public List<FlowLog> getFlowLogs() {
        return flowLogs;
    }

}
