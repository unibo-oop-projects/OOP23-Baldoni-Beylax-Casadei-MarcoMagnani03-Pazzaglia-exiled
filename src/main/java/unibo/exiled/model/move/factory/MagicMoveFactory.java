package unibo.exiled.model.move.factory;

import unibo.exiled.model.move.MagicMove;

/**
 * The MagicMoveFactory interface defines methods to create different types
 * of MagicMoves, such as Normal, Fire, Water, Bolt, and Grass.
 */
public interface MagicMoveFactory {

    /**
     * Creates a Normal type MagicMove with the specified attributes.
     *
     * @param name        The name of the Normal MagicMove.
     * @param description The description of the Normal MagicMove.
     * @param power       The power of the Normal MagicMove.
     * @param levelToLearn the minimum level needed to learn the move.
     * @return A Normal type MagicMove.
     */
    MagicMove createNormalMagicMove(String name, String description, double power, int levelToLearn);

    /**
     * Creates a Fire type MagicMove with the specified attributes.
     *
     * @param name        The name of the Fire MagicMove.
     * @param description The description of the Fire MagicMove.
     * @param power       The power of the Fire MagicMove.
     * @param levelToLearn the minimum level needed to learn the move.
     * @return A Fire type MagicMove.
     */
    MagicMove createFireMagicMove(String name, String description, double power, int levelToLearn);

    /**
     * Creates a Water type MagicMove with the specified attributes.
     *
     * @param name        The name of the Water MagicMove.
     * @param description The description of the Water MagicMove.
     * @param power       The power of the Water MagicMove.
     * @param levelToLearn the minimum level needed to learn the move.
     * @return A Water type MagicMove.
     */
    MagicMove createWaterMagicMove(String name, String description, double power, int levelToLearn);

    /**
     * Creates a Bolt type MagicMove with the specified attributes.
     *
     * @param name        The name of the Bolt MagicMove.
     * @param description The description of the Bolt MagicMove.
     * @param power       The power of the Bolt MagicMove.
     * @param levelToLearn the minimum level needed to learn the move.
     * @return A Bolt type MagicMove.
     */
    MagicMove createBoltMagicMove(String name, String description, double power, int levelToLearn);

    /**
     * Creates a Grass type MagicMove with the specified attributes.
     *
     * @param name        The name of the Grass MagicMove.
     * @param description The description of the Grass MagicMove.
     * @param power       The power of the Grass MagicMove.
     * @param levelToLearn the minimum level needed to learn the move.
     * @return A Grass type MagicMove.
     */
    MagicMove createGrassMagicMove(String name, String description, double power, int levelToLearn);
}
