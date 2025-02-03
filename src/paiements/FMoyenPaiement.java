package paiements;

public class FMoyenPaiement {

    private static IMoyenPaiement getMoyenPaiement(EMoyenPaiement type) {
        return switch (type) {
            case EMoyenPaiement.PAYPAL -> new PayPal();
            case EMoyenPaiement.CRYPTOMONNAIE -> new Cryptomonnaie();
            default -> throw new IllegalArgumentException("Type de paiement inconnu");
        };
    }

    public static IMoyenPaiement creerMoyenDePaiement(EMoyenPaiement type) {
        return getMoyenPaiement(type);
    }
}
