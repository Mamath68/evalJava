package validation;

public class VerificationCommande implements IValidationCheck {
    public IValidationCheck suivant = null;

    @Override
    public void setSuivant(IValidationCheck gestionneur) {
        this.suivant = gestionneur;
    }

    @Override
    public void gestionPrioriteCommande(CommandeService order) {
        if (order.getType() == EValidationChain.COMMANDE) {
            System.out.println("La commande n°" + order.getOrderId() + " a était validé ! Tout est bon ! Préparation de la commande");
        }
        if (this.suivant != null) this.suivant.gestionPrioriteCommande(order);
    }
}
