import commande.Commande;
import commande.EStatut;
import notification.RClients;
import notification.CommandeObserver;
import paiements.EMoyenPaiement;
import paiements.FMoyenPaiement;
import paiements.IMoyenPaiement;

public class Main {
    public static void main(String[] args) {

        /* **** La Commande **** */
        new Commande.CommandeBuilder()
                .addProduct("un site internet", 20000)
                .setStatus(EStatut.EN_PROGRESSION)
                .build();

        new Commande.CommandeBuilder()
                .addProduct("un serveur", 10000)
                .addProduct("un ordinateur", 2500)
                .addProduct("une télé", 1200)
                .setStatus(EStatut.ARRIVER)
                .build();

        new Commande.CommandeBuilder()
                .addProduct("McBook Air Pro", 200.50)
                .setStatus(EStatut.EN_PROGRESSION)
                .build();

        IMoyenPaiement payment_1 = FMoyenPaiement.creerMoyenDePaiement(EMoyenPaiement.PAYPAL);
        IMoyenPaiement payment_2 = FMoyenPaiement.creerMoyenDePaiement(EMoyenPaiement.CARTE_BANCAIRE);

        payment_1.payer(200);
        payment_2.payer(200);

        CommandeObserver observateur_des_commandes = new CommandeObserver();
        RClients george = new RClients("George");
        RClients martin = new RClients("Martin");

        observateur_des_commandes.addObserver(george);
        observateur_des_commandes.addObserver(martin);

        observateur_des_commandes.notifier("Votre commande a bien été reçu !");
        observateur_des_commandes.notifier("Votre commande est en cours de préparation !");
    }
}
