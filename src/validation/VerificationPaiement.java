package validation;

public class VerificationPaiement implements IValidationCheck {
    public IValidationCheck suivant = null;

    @Override
    public void setSuivant(IValidationCheck gestionneur) {
        this.suivant = gestionneur;
    }

    @Override
    public void gestionPrioriteCommande(CommandeService order) {
        boolean canContinue = true;
        if (order.getType() == EValidationChain.PAIEMENT) {
            if (order.getFundOfCustomer() < order.getTotalPrice()) {
                System.out.println("La commande n°" + order.getOrderId() + " n'est pas valide. Motif : Manque de fonds !");
                canContinue = false;
            }
        }
        if (canContinue && this.suivant != null)  {
            order.setType(EValidationChain.COMMANDE);
            this.suivant.gestionPrioriteCommande(order);
        }
    }
}
