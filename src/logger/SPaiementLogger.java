package logger;

public class SPaiementLogger {
    private static PaiementLogger instance;
    private SPaiementLogger() {}

    public static PaiementLogger getInstance() {
        if (instance == null) {
            instance = new PaiementLogger();
        }
        return instance;
    }
}
