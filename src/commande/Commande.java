package commande;

import notification.Clients;
import produits.Produits;

import java.util.HashMap;
import java.util.Map;

import static functions.Arrondir.arrondir;

public class Commande {
    private final int id;
    private final Map<Produits, Integer> produits;
    private final double prixTotal;
    private final String status;
    private final Clients clients;

    public Commande(CommandeBuilder builder) {
        this.id = builder.id;
        this.produits = builder.produits;
        this.prixTotal = builder.prixTotal;
        this.status = builder.status;
        this.clients = builder.clients;
    }

    public Map<Produits, Integer> getProduits() {
        return this.produits;
    }

    public double getPrixTotal() {
        return this.prixTotal;
    }

    public int getId() {
        return this.id;
    }

    public void display() {
        System.out.println(this);
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        String numberProducts = (this.getProduits().size() > 1) ? "produits" : "produit";

        return "Commande : " +
                "n°" + this.getId() +
                ", par " + this.clients.getNom() +
                ", " + numberProducts + ": " + this.getProduits() +
                ", prix total: " + arrondir(this.getPrixTotal()) + " €" +
                ", statut: '" + this.getStatus() + "'.";
    }

    public static class CommandeBuilder implements ICommandBuilder {
        private static int idAutoIncrement = 1;
        private final Map<Produits, Integer> produits = new HashMap<>();
        private final int id;
        private double prixTotal;
        private String status = EStatut.EN_ATTENTE.toString();
        private Clients clients;

        public CommandeBuilder() {
            this.id = idAutoIncrement++;
        }

        public Map<Produits, Integer> getProduits() {
            return produits;
        }

        public CommandeBuilder addProduct(Produits produits, int quantite) {
            if (quantite > 0 && quantite <= produits.getQuantite()) {
                this.getProduits().put(produits, quantite);
                this.prixTotal += produits.getPrix() * quantite;
            }
            return this;
        }

        public String getStatus(EStatut status) {
            return switch (status) {
                case EStatut.EN_ATTENTE -> "En attente";
                case EStatut.EN_PROGRESSION -> "En cours";
                case EStatut.ARRIVER -> "Livrée";
                case EStatut.ANNULER -> "Annulée";
            };
        }

        public CommandeBuilder setStatus(EStatut status) {
            this.status = this.getStatus(status);
            return this;
        }

        public CommandeBuilder setClients(Clients clients) {
            this.clients = clients;
            return this;
        }

        public Commande build() {
            return new Commande(this);
        }
    }
}
