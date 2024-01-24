package unibo.exiled.model.move;

import java.util.List;
import java.util.Optional;

import unibo.exiled.model.utilities.ElementalType;

/**
 * Interface for managing magical moves.
 */
public interface MagicMoveContainer {

    /**
     * Adds a new magical move to the container.
     *
     * @param magicMove The magical move to add.
     */
    void addMagicMove(MagicMove magicMove);

    /**
     * Retrieves the magical move with the specified name, if present in the container.
     *
     * @param name The name of the sought magical move.
     * @return The magical move with the specified name, or Optional.empty() if not present in the container.
     */
    Optional<MagicMove> getMagicMoveByName(String name);

    /**
     * Retrieves a list containing all magical moves present in the container.
     *
     * @return A list of all magical moves in the container.
     */
    List<MagicMove> getAllMagicMoves();

    /**
     * Retrieves a random magical move of the specified type.
     *
     * @param type The desired type of magical move.
     * @return A random magical move of the specified type, or Optional.empty() if none exist in the contanier.
     */
    Optional<MagicMove> getRandomMagicMoveByType(ElementalType type);
}

