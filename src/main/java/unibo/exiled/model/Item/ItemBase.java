package unibo.exiled.model.item;

import java.util.Optional;
import java.util.OptionalDouble;

/**
 * Questa classe astratta implementa l'interfaccia {@code Item} fornendo
 * un'implementazione di base comune per gli oggetti nel gioco.
 * Gli oggetti possono estendere questa classe per ereditare questa implementazione di base e aggiungere
 * funzionalità specifiche.
 * La classe ha un costruttore che richiede il nome e la descrizione dell'oggetto il tipo che può
 * essere Healing(curativo) PowerUp(potenziamento) o Standard(oggetto non utilizzabile es. cristalli), e nel caso in cui sia 
 * un oggetto utilizzabile il valore di quanto potenzia o cura
 * Esempi di oggetti che possono estendere questa classe includono oggetti curativi,
 * oggetti di potenziamento ecc.
 */
public abstract class ItemBase implements Item{
    private final String name;
    private final String description;
    private final Optional<Double> value;
    private final ItemType itemType;

    public ItemBase(String name,String description,Optional<Double> value,ItemType itemType){
        this.name = name;
        this.description = description;
        this.value=value;
        this.itemType=itemType;
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
