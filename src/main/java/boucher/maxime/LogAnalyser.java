package boucher.maxime;

import java.util.HashMap;

public interface LogAnalyser {
    /**
     * Count of matches for each tag, sample o/p shown below
     * <p>
     * Tag Counts:
     * Tag,Count
     * sv_P2,1
     * sv_P1,2
     * sv_P4,1
     * email,3
     * Untagged,9
     *
     * @return the count of matches for each tag
     */
    HashMap<String, Integer> countMatches();

    /**
     * Count of matches for each port/protocol combination
     * <p>
     * Port/Protocol Combination Counts:
     * Port,Protocol,Count
     * <p>
     * 22,tcp,1
     * 23,tcp,1
     * 25,tcp,1
     * 110,tcp,1
     * 143,tcp,1
     * 443,tcp,1
     * 993,tcp,1
     * 1024,tcp,1
     * 49158,tcp,1
     * 80,tcp,1
     *
     * @return the count of matches for each port/protocol combination
     */
    HashMap<String, Integer> countPortProtocolCombinations();
}
