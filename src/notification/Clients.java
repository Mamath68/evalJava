package notification;

public class Clients implements INotification{

    private String nom;

    public Clients(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void update(String message) {
        System.out.println(this.getNom() +
                " a reçu une nouvelle notification :\n" +
                ">>> " + message);
    }
}
