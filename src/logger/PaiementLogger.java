package logger;

public class PaiementLogger {
    private String message;

    public void log(String message) {
        System.out.println("Transaction : " + message);
    }
}
