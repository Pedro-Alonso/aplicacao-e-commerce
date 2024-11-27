package ECommerceApp.src.Classes;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger logger = null;
    private static final String LOG_FILE = "log.txt";

    /**
     * The message template to use when logging messages.
     * The first %s is the timestamp and the second %s is the message.
     */
    private String messageTemplate = "%s %s: %s";
    
    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    private void logStandard(String logLevel, String messageContent) {
        var dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var messageText = String.format(messageTemplate, dateTimeString, logLevel, messageContent);

        this.commitLog(messageText + "\n");
    }

    public void logInfo(String messageContent) {
        this.logStandard("INFO", messageContent);
    }

    public void logError(String messageContent) {
        this.logStandard("ERROR", messageContent);
    }

    public void logWarning(String messageContent) {
        this.logStandard("WARN", messageContent);
    }

    private void commitLog(String messageText) {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE, true);
            fileWriter.write(messageText);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while writing to the log file.");
        }
    }
}
