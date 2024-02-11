package unibo.exiled.model.move.factory;

import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MagicMoveImpl;
import unibo.exiled.utilities.ElementalType;

/**
 * The MagicMoveFactoryImpl class implements the MagicMoveFactory interface
 * to provide methods for creating different types of magical moves. Each method
 * returns an
 * instance of MagicMoveImpl with specified attributes such as name,
 * description, power,
 * and elemental type.
 */
public class MagicMoveFactoryImpl implements MagicMoveFactory {

    /**
     * Creates a Normal type MagicMove with the specified attributes.
     *
     * @param name        The name of the Normal MagicMove.
     * @param description The description of the Normal MagicMove.
     * @param power       The power of the Normal MagicMove.
     * @return A Normal type MagicMove.
     */
    @Override
    public MagicMove createNormalMagicMove(final String name, final String description, final double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.NORMAL);
    }

    /**
     * Creates a Fire type MagicMove with the specified attributes.
     *
     * @param name        The name of the Fire MagicMove.
     * @param description The description of the Fire MagicMove.
     * @param power       The power of the Fire MagicMove.
     * @return A Fire type MagicMove.
     */
    @Override
    public MagicMove createFireMagicMove(final String name, final String description, final double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.FIRE);
    }

    /**
     * Creates a Water type MagicMove with the specified attributes.
     *
     * @param name        The name of the Water MagicMove.
     * @param description The description of the Water MagicMove.
     * @param power       The power of the Water MagicMove.
     * @return A Water type MagicMove.
     */
    @Override
    public MagicMove createWaterMagicMove(final String name, final String description, final double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.WATER);
    }

    /**
     * Creates a Bolt type MagicMove with the specified attributes.
     *
     * @param name        The name of the Bolt MagicMove.
     * @param description The description of the Bolt MagicMove.
     * @param power       The power of the Bolt MagicMove.
     * @return A Bolt type MagicMove.
     */
    @Override
    public MagicMove createBoltMagicMove(final String name, final String description, final double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.BOLT);
    }

    /**
     * Creates a Grass type MagicMove with the specified attributes.
     *
     * @param name        The name of the Grass MagicMove.
     * @param description The description of the Grass MagicMove.
     * @param power       The power of the Grass MagicMove.
     * @return A Grass type MagicMove.
     */
    @Override
    public MagicMove createGrassMagicMove(final String name, final String description, final double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.GRASS);
    }

}
