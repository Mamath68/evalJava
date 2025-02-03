package validation;

import commande.Commande;

public class VerificationStock implements IHandler {
    private IHandler next;

    @Override
    public void setNext(IHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(Commande commande) {
        System.out.println("Vérification du stock pour la commande " + commande);
        if (next != null) {
            next.handle(commande);
        }
    }
}
