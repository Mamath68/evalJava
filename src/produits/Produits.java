package produits;

import static functions.Arrondir.arrondir;

public class Produits {

    private final int id;
    private final String nom;
    private final double prix;
    private final String category;
    private final int quantite;

    public Produits(ProductBuilder builder) {
        this.id = builder.id;
        this.nom = builder.nom;
        this.prix = builder.prix;
        this.category = builder.category;
        this.quantite = builder.quantite;
    }

    public String getNom() {
        return nom;
    }

    public String getCategory() {
        return category;
    }

    public double getPrix() {
        return this.prix;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "\n --> Produit : " +
                "n°" + id +
                ", nom = '" + this.getNom() + '\'' +
                ", prix = " + arrondir(this.getPrix()) + " € " +
                ", catégorie = '" + this.getCategory() + '\'' +
                ", quantité = " + this.getQuantite() +
                ".";
    }

    public static class ProductBuilder implements IProduits {
        private static int idAutoIncrement = 1;
        private final int id;
        private String nom;
        private double prix;
        private String category;
        private int quantite;

        public ProductBuilder(){
            this.id = idAutoIncrement++;
        }

        public ProductBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public ProductBuilder setPrix(double prix) {
            this.prix = prix;
            return this;
        }

        public ProductBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public ProductBuilder setQuantite(int quantite) {
            this.quantite = quantite;
            return this;
        }

        public Produits build() {
            return new Produits(this);
        }
    }
}
