package unibo.exiled.model.move;

/**
 * The implementation of a factory of MoveSets.
 */
public final class MoveSetFactoryImpl implements MoveSetFactory {
    @Override
    public MoveSet defaultNormalMoveSet(final int movesNumber) {
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.COLPACCIO.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultFireMoveSet(final int movesNumber) {
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.FIREBALL.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultGrassMoveSet(final int movesNumber) {
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.LEAFBLADE.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultBoltMoveSet(final int movesNumber) {
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.LIGHTBULB.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultWaterMoveSet(final int movesNumber) {
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.WATERPISTOL.getName()).get());
        return moveSet;
    }
}
