package unibo.exiled.model.move.factory;

import unibo.exiled.model.move.MoveNames;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.move.Moves;

/**
 * The implementation of a factory of MoveSets.
 */
public final class MoveSetFactoryImpl implements MoveSetFactory {
    @Override
    public MoveSet defaultNormalMoveSet() {
        final MoveSet moveSet = new MoveSetImpl();
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.COLPACCIO.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultFireMoveSet() {
        final MoveSet moveSet = new MoveSetImpl();
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.FIREBALL.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultGrassMoveSet() {
        final MoveSet moveSet = new MoveSetImpl();
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.LEAFBLADE.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultBoltMoveSet() {
        final MoveSet moveSet = new MoveSetImpl();
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.LIGHTBULB.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultWaterMoveSet() {
        final MoveSet moveSet = new MoveSetImpl();
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.WATERPISTOL.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet whirlerMoveset() {
        final MoveSet moveSet = new MoveSetImpl();
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.FLAMEWHIRL.getName()).get());
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.FIREBALL.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet boltBossMoves() {
        final MoveSet moveSet = new MoveSetImpl();
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.THUNDERSTORM.getName()).get());
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.LOCOMOVOLT.getName()).get());
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.THUNDERSTRIKE.getName()).get());
        return moveSet;
    }


}
