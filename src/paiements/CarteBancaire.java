package paiements;

import static maths.Arrondir.arrondir;

public class CarteBancaire implements IMoyenPaiement {
    @Override
    public void payer(double montant) {
        System.out.println("Paiement de " + arrondir(montant) + " € effectué(e) par Carte Bancaire.");
    }
}
