package unibo.exiled.model.move;

public class MoveSetFactoryImpl implements MoveSetFactory {
    public MoveSet defaultNormalMoveSet(int movesNumber){
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.COLPACCIO.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultFireMoveSet(int movesNumber) {
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.FIREBALL.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultGrassMoveSet(int movesNumber) {
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.LEAFBLADE.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultBoltMoveSet(int movesNumber) {
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.LIGHTBULB.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultWaterMoveSet(int movesNumber) {
        final MoveSet moveSet = new MoveSetImpl(movesNumber);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.WATERPISTOL.getName()).get());
        return moveSet;
    }
}
