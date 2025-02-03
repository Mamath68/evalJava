package commande;

import java.util.ArrayList;
import java.util.List;

import static functions.Arrondir.arrondir;

public class Commande {

    private final int id;
    private final List<String> produits;
    private final double prixTotal;
    private final String status;

    public Commande(CommandeBuilder builder) {
        this.id = builder.id;
        this.produits = builder.produits;
        this.prixTotal = builder.prix;
        this.status = builder.status;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public int getId() {
        return id;
    }

    public List<String> getProduits() {
        return produits;
    }

    @Override
    public String toString() {
        String numberProducts = (this.produits.size() == 1) ? "Produit" : "Produits";
        return "Commande : " +
                "n°" + this.getId() +
                ", " + numberProducts + ": " + this.getProduits() +
                ", Prix: " + arrondir(this.getPrixTotal()) +
                ", Statut: '" + this.status + '\'';
    }

    public void display() {
        System.out.println(this);
    }

    public static class CommandeBuilder implements ICommandBuilder {
        private static int idAutoIncrement = 1;
        private final List<String> produits = new ArrayList<>();
        private final int id;
        private double prix;
        private String status = EStatut.EN_ATTENTE.toString();

        public CommandeBuilder() {
            this.id = idAutoIncrement++;
        }

        public CommandeBuilder addProduct(String produit, double prix) {
            this.produits.add(produit);
            this.prix += prix;
            return this;
        }

        public String getStatus(EStatut status) {
            return switch (status) {
                case EN_ATTENTE -> "En attente";
                case EN_PROGRESSION -> "En cours";
                case ARRIVER -> "Livrée";
                case ANNULER -> "Annulée";
            };
        }

        public CommandeBuilder setStatus(EStatut status) {
            this.status = this.getStatus(status);
            return this;
        }

        @Override
        public void build() {
            Commande commande = new Commande(this);
            commande.display();
        }
    }
}
