package validation;

public interface IValidationCheck {
    void setSuivant(IValidationCheck gestionneur);

    void gestionPrioriteCommande(CommandeService commande);
}
