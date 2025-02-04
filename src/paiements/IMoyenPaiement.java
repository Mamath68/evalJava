package paiements;

public interface IMoyenPaiement {
    void payer(double montant);

    void display();
}
