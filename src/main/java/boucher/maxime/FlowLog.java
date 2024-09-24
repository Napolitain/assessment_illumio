package boucher.maxime;

public record FlowLog(
        int version,
        long accountId,
        String eni,
        String srcAddr,
        String dstAddr,
        int srcPort,
        int dstPort,
        ProtocolNumber protocol,
        int packets,
        int bytes,
        long startTime,
        long endTime,
        String action,
        String logStatus
) {
}