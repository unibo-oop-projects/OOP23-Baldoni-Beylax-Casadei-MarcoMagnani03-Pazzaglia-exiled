package unibo.exiled.model.move;

import unibo.exiled.model.utilities.ElementalType;

public class MoveSetFactoryImpl implements MoveSetFactory {
    public MoveSet defaultBasicMoveSet(){
        final MoveSet moveSet = new MoveSetImpl();
        moveSet.addMagicMove(new MagicMoveImpl("Colpaccio",5, ElementalType.NORMAL));
        return moveSet;
    }
}
