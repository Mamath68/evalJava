package paiements;

import static functions.Arrondir.arrondir;

public class PayPal implements IMoyenPaiement {
    @Override
    public void payer(double montant) {
        System.out.println("Paiement de " + arrondir(montant) + " € effectué(e) par PayPal.");
    }
}
