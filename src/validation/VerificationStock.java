package validation;

import produits.Produits;

import java.util.Map;

public class VerificationStock implements IValidationCheck {
    public IValidationCheck suivant = null;

    @Override
    public void setSuivant(IValidationCheck gestionneur) {
        this.suivant = gestionneur;
    }

    @Override
    public void gestionPrioriteCommande(CommandeService order) {
        boolean canContinue = true;
        if (order.getType() == EValidationChain.STOCK) {
            for (Map.Entry<Produits, Integer> cmd : order.getProducts().entrySet()) {
                Produits produit = cmd.getKey();
                int orderQuantity = cmd.getValue();
                if (produit.getQuantite() < orderQuantity) {
                    System.out.println("La commande n'est pas valide ! Le produit " + produit + " est en rupture de stock !");
                    canContinue = false;
                }
            }
        }
        if (canContinue && this.suivant != null)  {
            order.setType(EValidationChain.PAIEMENT);
            this.suivant.gestionPrioriteCommande(order);
        }
    }
}
