package Logic.Exceptions;


import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class LoggerConfig {
    
    public static void configurar() {
        try {
            Logger logger = Logger.getLogger("");

            Handler[] handlers = logger.getHandlers();
            for (Handler handler : handlers) {
                logger.removeHandler(handler);
            }

            FileHandler fileHandler = new FileHandler("binnacle.log", true);
            
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {

                    String fecha = dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(record.getMillis()), ZoneId.systemDefault()));

                    StringBuilder stringBuilder = new StringBuilder();

                    stringBuilder.append(fecha).append(" [").append(record.getLevel()).append("] ").append(record.getLoggerName()).append(" - ")
                    .append(record.getMessage()).append("\n");

                    if (record.getThrown() != null) {
                        Throwable throwable = record.getThrown();
                        stringBuilder.append(throwable.toString()).append("\n");
                        for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
                            stringBuilder.append("\tat ").append(stackTraceElement).append("\n");
                        }
                    }

                    return stringBuilder.toString();
                }
            });

            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            throw new RuntimeException("No se pudo configurar el logger", e);
        }
    }
    
}
