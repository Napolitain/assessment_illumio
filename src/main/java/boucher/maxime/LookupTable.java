package boucher.maxime;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LookupTable {
    private final Map<String, String> lookupMap = new HashMap<>();

    /**
     * Add an entry to the lookup table using the destination port and protocols as keys and the tag as the value.
     *
     * @param dstPort  destination port
     * @param protocol protocol
     * @param tag      tag
     */
    public void addEntry(int dstPort, int protocol, String tag) {
        String key = generateKey(dstPort, protocol);
        lookupMap.put(key, tag);
    }

    /**
     * Add entries to the lookup table from a file. The file can contain or not its header.
     *
     * @param lut the lookup table file containing the entries
     * @throws IOException if the file cannot be read
     */
    public void addEntries(File lut) throws IOException {
        addEntries(new FileReader(lut));
    }

    public void addEntries(Reader lut) throws IOException {
        BufferedReader reader = new BufferedReader(lut);
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                String[] fields = line.split(",");
                if (fields.length == 3) {
                    addEntry(Integer.parseInt(fields[0]), ProtocolNumber.protocolFromName(fields[1]).getValue(), fields[2]);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid entry: " + line);
            }
        }
        reader.close();
    }

    /**
     * Return the tag for the given destination port and protocol.
     * If no tag is found, return "Untagged".
     *
     * @param dstPort  destination port
     * @param protocol protocol (6 for TCP, 17 for UDP)
     * @return the tag or "Untagged"
     */
    public String getTag(int dstPort, int protocol) {
        String key = generateKey(dstPort, protocol);
        return lookupMap.getOrDefault(key, "Untagged");
    }

    /**
     * Generate a key for the lookup map from the destination port and protocol.
     *
     * @param dstPort  the destination port
     * @param protocol the protocol
     * @return the generated key
     */
    private String generateKey(int dstPort, int protocol) {
        return dstPort + "," + protocol;
    }
}
