package unibo.exiled.model.Item;
/**
 * Questa interfaccia modella un oggetto presente nel gioco.
 * Un oggetto rappresenta un elemento fondamentale del contesto di gioco.
 * Ogni oggetto ha un nome e una descrizione.
 * L'implementazione di questa interfaccia consente di definire comportamenti specifici
 * per ciascun tipo di oggetto nel gioco.
 */
public interface Item {
    /**
     * Restituisce il nome dell'oggetto.
     *
     * @return Il nome dell'oggetto.
     */
    public String getName();
    /**
     * Restituisce la descrizione dell'oggetto.
     *
     * @return La descrizione dell'oggetto.
     */
    public String getDescription();
}
