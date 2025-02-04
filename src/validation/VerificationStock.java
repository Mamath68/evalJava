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
    public void gestionPrioriteCommande(CommandeService commande) {
        boolean peuContinuer = true;
        if (commande.getType() == EValidationChain.STOCK) {
            for (Map.Entry<Produits, Integer> cmd : commande.getProducts().entrySet()) {
                Produits produit = cmd.getKey();
                int orderQuantity = cmd.getValue();
                if (produit.getQuantite() < orderQuantity) {
                    System.out.println("La commande n'est pas valide ! Le produit " + produit + " est en rupture de stock !");
                    peuContinuer = false;
                }
            }
        }
        if (peuContinuer && this.suivant != null)  {
            commande.setType(EValidationChain.PAIEMENT);
            this.suivant.gestionPrioriteCommande(commande);
        }
    }
}
