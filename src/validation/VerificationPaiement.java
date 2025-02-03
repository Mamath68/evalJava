package validation;

import commande.Commande;

public class VerificationPaiement implements IHandler {
    private IHandler next;

    @Override
    public void setNext(IHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(Commande commande) {
        System.out.println("Vérification du paiement pour la commande " + commande);
        if (next != null) {
            next.handle(commande);
        }
    }
}
