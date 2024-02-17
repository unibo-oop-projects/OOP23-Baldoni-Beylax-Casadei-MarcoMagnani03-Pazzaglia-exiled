package unibo.exiled.model.combat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import unibo.exiled.controller.GameController;
import unibo.exiled.controller.GameControllerImpl;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.game.GameModelImpl;
import unibo.exiled.utilities.FontManager;
import unibo.exiled.utilities.Position;
import unibo.exiled.view.CombatView;
import unibo.exiled.view.GameView;

class TestCombat {
    private static GameModel gameModel;
    private static CombatModel combatModel;
    private static GameController gameController;
    private static final int COLPACCIO_POWER = 6;

    @BeforeAll
    static void initialize() {
        gameModel = new GameModelImpl();
        combatModel = new CombatModelImpl(gameModel);
        gameController = new GameControllerImpl(gameModel);
        gameModel.getCharacterModel().getPlayer().get().move(new Position(0, 0));
        combatModel.newCombat();
        FontManager.loadFont();
    }

    @Test
    void testInitCombat() {
        assertEquals(new Position(0, 0), combatModel.getCombatPosition());
        assertEquals(CombatStatus.IDLE, combatModel.getCombatStatus());
        assertNotNull(combatModel.getEnemy());
        assertNotNull(combatModel.getPlayer());
        assertFalse(combatModel.needsPlayerToChangeMove());
    }

    @Test
    void testAttack() {
        final GameView gameView = new GameView(gameController);
        final CombatView combatView = new CombatView(gameController, gameView);
        gameView.initializeCombat();
        final double enemyHealth = gameController.getCombatController().getEnemyHealth();
        gameController.getCombatController().attack(true, Optional.of("COLPACCIO"), gameController, combatView);

        assertEquals(gameController.getCombatController().getEnemyHealth(), enemyHealth - COLPACCIO_POWER);
    }
}
