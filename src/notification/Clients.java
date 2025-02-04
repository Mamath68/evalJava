package notification;

import commande.Commande;
import paiements.IMoyenPaiement;

import java.util.List;

import static functions.Arrondir.arrondir;

public class Clients implements INotification {

    private static int idAutoIncrement = 1;
    private final int id;
    private final String nom;
    private final double fondBancaire;
    private List<IMoyenPaiement> moyenPaiements;
    private List<Commande> commandes;

    public Clients(String nom, double fondBancaire) {
        this.id = idAutoIncrement++;
        this.nom = nom;
        this.fondBancaire = fondBancaire;
    }

    public String getNom() {
        return this.nom;
    }

    public double getFondBancaire() {
        return this.fondBancaire;
    }

    public void display() {
        System.out.println(this);
    }

    @Override
    public void update(String message) {
        System.out.println("--> " + this.getNom() +
                " a reçu une nouvelle notification :\n" +
                ">>> " + message);
    }

    @Override
    public String toString() {
        return "Client: " +
                "n°" + this.id +
                ", nom: '" + this.getNom() + "'" +
                ", fond bancaire: " + arrondir(this.getFondBancaire()) + " €.";
    }
}
