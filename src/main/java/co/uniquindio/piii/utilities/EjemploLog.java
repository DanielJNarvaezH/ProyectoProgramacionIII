package co.uniquindio.piii.utilities;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;

public class EjemploLog {

    private static final Logger logger =Logger.getLogger(EjemploLog.class.getName());
    private static FileHandler fileHandler;

        static {
            try {
                fileHandler = new FileHandler("C:\\Users\\Dell\\Documents\\.vscode\\ProyectoProgramacionIII\\proyectoProgramacionIII\\src\\main\\java\\co\\uniquindio\\piii\\persistencia\\log\\proyectoProgramacionIII_Log.txt", true); // Appends to the log file
                fileHandler.setFormatter(new SimpleFormatter());
    
                logger.addHandler(fileHandler);
                logger.setLevel(Level.ALL);  // Logs all levels
    
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error al configurar el logger", e);
            }
        }


    public static void logInfo(String mensaje) {
        logger.log(Level.INFO, mensaje);
    }

    public static void logWarning(String mensaje) {
        logger.log(Level.WARNING, mensaje);
    }

    public static void logError(String mensaje) {
        logger.log(Level.SEVERE, mensaje);
    }

    public static void logError(String mensaje, Exception ex) {
        logger.log(Level.SEVERE, mensaje, ex);
    }
}

