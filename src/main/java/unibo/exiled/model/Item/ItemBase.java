package unibo.exiled.model.Item;

/**
 * Questa classe astratta implementa l'interfaccia {@code Item} fornendo
 * un'implementazione di base comune per gli oggetti nel gioco.
 * Gli oggetti possono estendere questa classe per ereditare questa implementazione di base e aggiungere
 * funzionalità specifiche.
 * La classe ha un costruttore che richiede il nome e la descrizione dell'oggetto,
 * che vengono assegnati alle variabili di istanza protette {@code name} e {@code description}.
 * Esempi di oggetti che possono estendere questa classe includono oggetti curativi,
 * oggetti di potenziamento ecc.
 */
public abstract class ItemBase implements Item{
    private final String name;
    private final String description;

    public ItemBase(String name,String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
    
}
