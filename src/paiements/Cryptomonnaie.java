package paiements;

import static functions.Arrondir.arrondir;

public class Cryptomonnaie implements IMoyenPaiement {
    @Override
    public void payer(double montant) {
        System.out.println("Paiement de " + arrondir(montant) + " € effectué(e) par Cryptomonnaie.");
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "--> Méthode de payement : Cryptomonnaie";
    }
}
