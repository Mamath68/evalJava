package validation;

import commande.Commande;

public interface IHandler {
    void setNext(IHandler handler);

    void handle(Commande commande);
}
