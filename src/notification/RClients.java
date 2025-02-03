package notification;

public record RClients(String nom) implements INotification {

    @Override
    public void update(String message) {
        System.out.println(this.nom() +
                " a reçu une nouvelle notification :\n" +
                ">>> " + message);
    }
}
