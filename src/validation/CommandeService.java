package validation;

import commande.Commande;
import notification.Clients;
import produits.Produits;

import java.util.Map;

public class CommandeService {
    private EValidationChain type;
    private final Clients clients;
    private final Commande commande;
    private final String conclusion = "";

    public CommandeService(EValidationChain type, Clients clients, Commande commande) {
        this.type = type;
        this.clients = clients;
        this.commande = commande;
    }

    public EValidationChain getType() {
        return this.type;
    }

    public void setType(EValidationChain type) {
        this.type = type;
    }

    public double getFundOfCustomer() {
        return this.clients.getFondBancaire();
    }

    public Map<Produits, Integer> getProducts() {
        return this.commande.getProduits();
    }

    public double getTotalPrice() {
        return this.commande.getPrixTotal();
    }

    public int getOrderId() {
        return this.commande.getId();
    }

    public String getConclusion() {
        return this.conclusion;
    }

    public void display() {
        System.out.println(this.getConclusion());
    }
}
