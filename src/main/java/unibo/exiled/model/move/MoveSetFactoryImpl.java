package unibo.exiled.model.move;

public class MoveSetFactoryImpl implements MoveSetFactory {
    public MoveSet defaultNormalMoveSet(){
        final MoveSet moveSet = new MoveSetImpl(1);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.COLPACCIO.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultFireMoveSet() {
        final MoveSet moveSet = new MoveSetImpl(1);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.FIREBALL.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultGrassMoveSet() {
        final MoveSet moveSet = new MoveSetImpl(1);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.LEAFBLADE.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultBoltMoveSet() {
        final MoveSet moveSet = new MoveSetImpl(1);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.LIGHTBULB.getName()).get());
        return moveSet;
    }

    @Override
    public MoveSet defaultWaterMoveSet() {
        final MoveSet moveSet = new MoveSetImpl(1);
        moveSet.addMagicMove(Moves.getMoveByName(MoveNames.WATERPISTOL.getName()).get());
        return moveSet;
    }
}
