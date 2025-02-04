package validation;

public class VerificationPaiement implements IValidationCheck {
    public IValidationCheck suivant = null;

    @Override
    public void setSuivant(IValidationCheck gestionneur) {
        this.suivant = gestionneur;
    }

    @Override
    public void gestionPrioriteCommande(CommandeService commande) {
        boolean canContinue = true;
        if (commande.getType() == EValidationChain.PAIEMENT) {
            if (commande.getFundOfCustomer() < commande.getTotalPrice()) {
                System.out.println("La commande n°" + commande.getOrderId() + " n'est pas valide. Motif : Manque de fonds !");
                canContinue = false;
            }
        }
        if (canContinue && this.suivant != null)  {
            commande.setType(EValidationChain.COMMANDE);
            this.suivant.gestionPrioriteCommande(commande);
        }
    }
}
