import commande.Commande;
import commande.Commande.CommandeBuilder;
import commande.EStatut;
import logger.PaiementLogger;
import logger.SPaiementLogger;
import notification.Clients;
import notification.CommandeObserver;
import paiements.EMoyenPaiement;
import paiements.FMoyenPaiement;
import paiements.IMoyenPaiement;
import produits.Produits;
import produits.Produits.ProductBuilder;
import validation.*;

public class Main {
    public static void main(String[] args) {

        // -- INIT -- \\

        // → Liste des produits
        Produits xiaomiRedmi13c = new ProductBuilder()
                .setNom("Xiaomi Redmi 13C")
                .setPrix(170)
                .setCategory("Smartphone")
                .setQuantite(400)
                .build();
        Produits ps4 = new ProductBuilder()
                .setNom("PlayStation 4")
                .setPrix(399.99)
                .setCategory("Console")
                .setQuantite(250)
                .build();

        Produits creerSiteWeb = new ProductBuilder()
                .setNom("Creation de site web")
                .setPrix(1500)
                .setCategory("Service")
                .setQuantite(100)
                .build();
        Produits miseEnLigne = new ProductBuilder()
                .setNom("Hébergement cloud")
                .setPrix(25)
                .setCategory("Service")
                .setQuantite(10)
                .build();
        Produits sav = new ProductBuilder()
                .setNom("Maintenance et mise à jour de logiciels")
                .setPrix(95)
                .setCategory("Service")
                .setQuantite(10)
                .build();
        Produits creerApplication = new ProductBuilder()
                .setNom("Creation d'application mobile")
                .setPrix(5000)
                .setCategory("Service")
                .setQuantite(10)
                .build();

        // → Liste des payements
        IMoyenPaiement paypal = FMoyenPaiement.creerMoyenDePaiement(EMoyenPaiement.PAYPAL);
        IMoyenPaiement creditCards = FMoyenPaiement.creerMoyenDePaiement(EMoyenPaiement.CARTE_BANCAIRE);
        IMoyenPaiement cryptocurrency = FMoyenPaiement.creerMoyenDePaiement(EMoyenPaiement.CRYPTOMONNAIE);

        // → Liste des clients
        Clients rico = new Clients("Mathieu", 4000);
        Clients lip = new Clients("George", 10);

        // → Liste des commandes
        Commande rico_order = new CommandeBuilder()
                .setClients(rico)
                .addProduct(ps4, 1)
                .addProduct(miseEnLigne, 1)
                .setStatus(EStatut.EN_ATTENTE)
                .build();
        Commande lip_order = new CommandeBuilder()
                .setClients(lip)
                .addProduct(xiaomiRedmi13c, 2)
                .setStatus(EStatut.EN_ATTENTE)
                .build();

        // → Pour la notification
        CommandeObserver general_observer = new CommandeObserver();
        CommandeObserver rico_observer = new CommandeObserver();
        CommandeObserver lip_observer = new CommandeObserver();

        general_observer.addObserver(rico);
        general_observer.addObserver(lip);

        rico_observer.addObserver(rico);

        lip_observer.addObserver(lip);

        // → Pour gérer la responsabilité
        IValidationCheck stockCheck = new VerificationStock();
        IValidationCheck paymentCheck = new VerificationPaiement();
        IValidationCheck orderDispatch = new VerificationCommande();

        stockCheck.setSuivant(paymentCheck);
        paymentCheck.setSuivant(orderDispatch);

        // --> Pour le logger
        PaiementLogger logger = SPaiementLogger.getInstance();
        PaiementLogger anotherLogger = SPaiementLogger.getInstance();

        // -- DISPLAY -- \\

        System.out.print("\n");
        System.out.println("#---------- PRODUITS ----------#");
        System.out.print("\n");

        xiaomiRedmi13c.display();
        ps4.display();

        System.out.print("\n");

        creerSiteWeb.display();
        miseEnLigne.display();
        sav.display();
        creerApplication.display();

        System.out.print("\n");
        System.out.println("#---------- METHODES DE PAYEMENTS ----------#");
        System.out.print("\n");

        creditCards.display();
        paypal.display();
        cryptocurrency.display();

        System.out.print("\n");

        creditCards.payer(200);

        System.out.print("\n");
        System.out.println("#---------- CLIENTS ----------#");
        System.out.print("\n");

        rico.display();
        lip.display();

        System.out.print("\n");
        System.out.println("#---------- COMMANDES ----------#");
        System.out.print("\n");

        rico_order.display();
        lip_order.display();

        //System.out.println(order_rico.getProduits());

        System.out.print("\n");
        System.out.println("#---------- NOTIFICATIONS ----------#");
        System.out.print("\n");

        general_observer.notify("Votre commande a bien été reçu !");
        rico_observer.notify("Votre commande est en cours de préparation !");
        lip_observer.notify("Votre commande est en attende de produits disponible !");

        System.out.print("\n");
        System.out.println("#---------- RESPONSABILITES ----------#");
        System.out.print("\n");

        CommandeService rico_requestStock = new CommandeService(EValidationChain.STOCK, rico, rico_order);

        stockCheck.gestionPrioriteCommande(rico_requestStock);

        rico_requestStock.display();

        CommandeService lip_requestStock = new CommandeService(EValidationChain.STOCK, lip, lip_order);

        stockCheck.gestionPrioriteCommande(lip_requestStock);

        lip_requestStock.display();

        System.out.print("\n");
        System.out.println("#---------- LOG ----------#");
        System.out.print("\n");

        logger.log("Paiement effectué.");
        anotherLogger.log("Annulation de la commande.");
    }
}
