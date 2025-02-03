public class TransactionLogger {
    private static TransactionLogger instance;

    private TransactionLogger() {}

    public static TransactionLogger getInstance() {
        if (instance == null) {
            instance = new TransactionLogger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
