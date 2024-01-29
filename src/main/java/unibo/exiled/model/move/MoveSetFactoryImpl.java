package unibo.exiled.model.move;

import unibo.exiled.model.utilities.ElementalType;

public class MoveSetFactoryImpl implements MoveSetFactory {
    public MoveSet defaultNormalMoveSet(){
        final MoveSet moveSet = new MoveSetImpl(1);
        MagicMoveFactory moveFactory = new MagicMoveFactoryImpl();
        moveSet.addMagicMove(moveFactory.createNormalMagicMove("Colpaccio","This move delivers a little blow",5));
        return moveSet;
    }

    @Override
    public MoveSet defaultFireMoveSet() {
        final MoveSet moveSet = new MoveSetImpl(1);
        MagicMoveFactory moveFactory = new MagicMoveFactoryImpl();
        moveSet.addMagicMove(moveFactory.createFireMagicMove("Fire Ball","Unleashes a scorching fireball",5));
        return moveSet;
    }

    @Override
    public MoveSet defaultGrassMoveSet() {
        final MoveSet moveSet = new MoveSetImpl(1);
        MagicMoveFactory moveFactory = new MagicMoveFactoryImpl();
        moveSet.addMagicMove(moveFactory.createGrassMagicMove("Leef Blade","Swiftly unleashes razor-sharp foliage",5));
        return moveSet;
    }

    @Override
    public MoveSet defaultBoltMoveSet() {
        final MoveSet moveSet = new MoveSetImpl(1);
        MagicMoveFactory moveFactory = new MagicMoveFactoryImpl();
        moveSet.addMagicMove(moveFactory.createBoltMagicMove("Thundershock","Releases a powerful bolt of lightning",5));
        return moveSet;
    }

    @Override
    public MoveSet defaultWaterMoveSet() {
        final MoveSet moveSet = new MoveSetImpl(1);
        MagicMoveFactory moveFactory = new MagicMoveFactoryImpl();
        moveSet.addMagicMove(moveFactory.createBoltMagicMove("Aqua Jet","Propels a high-speed stream of water",5));
        return moveSet;
    }
}
