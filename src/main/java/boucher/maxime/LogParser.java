package boucher.maxime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public interface LogParser {
    /**
     * Parse the log string.
     *
     * @param log the log string to parse
     */
    void parse(String log);

    /**
     * Parse the log file.
     *
     * @param logFile the log file to parse
     * @throws IOException if the log file cannot be read
     */
    default void parse(File logFile) throws IOException {
        parse(new FileReader(logFile));
    }

    /**
     * Parse the log reader.
     *
     * @param logReader the log reader to parse
     * @throws IOException if the log reader cannot be read
     */
    default void parse(java.io.Reader logReader) throws IOException {
        BufferedReader reader = new BufferedReader(logReader);
        StringBuilder logBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            logBuilder.append(line).append(System.lineSeparator());
        }
        var log = logBuilder.toString();
        parse(log);
    }
}
