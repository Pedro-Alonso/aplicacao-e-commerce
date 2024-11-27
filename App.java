import ECommerceApp.src.Classes.Logger;

public class App {
    public static void main(String[] args) throws Exception {
        Logger logger = Logger.getInstance();

        logger.logInfo("This is an info message.");
        logger.logError("This is an error message.");
        logger.logWarning("This is a warning message.");
    }
}
