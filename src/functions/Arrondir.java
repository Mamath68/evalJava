package functions;

public class Arrondir {
    public static String arrondir(double nombre) {
        if (nombre == (long) nombre) {
            return String.format("%d", (long) nombre);
        } else {
            return String.format("%s", nombre);
        }
    }
}
