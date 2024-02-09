package unibo.exiled.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import unibo.exiled.controller.GameController;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;

/**
 * The Hud class represents the Heads-up Display (HUD) for the game.
 */
public final class Hud {
    private final GameView gameView;
    private final GameController gameController;
    private static final int HEALTH_CRITICAL_PERCENTAGE = 20;

    /**
     * Constructs a new Hud instance.
     *
     * @param gameView       The GameView associated with the HUD.
     * @param gameController The GameController associated with the HUD.
     */
    public Hud(final GameView gameView, final GameController gameController) {
        this.gameView = gameView;
        this.gameController = gameController;
    }

    /**
     * Initializes the HUD by setting up buttons, panels, and labels.
     */
    public void initialize() {
        final JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());
        final JPanel flowButtonPanelSouth = new JPanel(new FlowLayout());
        gameView.getGameHudPanel().add(flowButtonPanelNorth, BorderLayout.NORTH);
        gameView.getGameHudPanel().add(flowButtonPanelSouth, BorderLayout.SOUTH);

        final GameButton inventoryButton = new GameButton("Inventory");
        inventoryButton.addActionListener(e -> gameView.showInventory());

        final GameButton menuButton = new GameButton("Menu");
        menuButton.addActionListener(e -> gameView.showMenu());

        flowButtonPanelNorth.add(inventoryButton);
        flowButtonPanelNorth.add(menuButton);

        refreshStatusPanel();

        flowButtonPanelSouth.add(gameView.getStatusPanel());
    }

    /**
    * Refreshes the status panel with updated information about the player's health,
    * level, class, and experience.
    */
    public void refreshStatusPanel() {
        gameView.getStatusPanel().removeAll();
        final GameLabel healthBar = getHealthBar();
        final GameLabel levelLabel = new GameLabel("Level: " + gameController.getCharacterController().getPlayerLevel());
        final GameLabel classLabel = new GameLabel("Class: " + gameController.getCharacterController().getPlayerClassName());
        final int currentExperience = gameController.getCharacterController().getPlayerCurrentExperience();
        final int experienceCap = gameController.getCharacterController().getPlayerExperienceCap();
        final GameLabel experienceLabel = new GameLabel("Experience: " + currentExperience + " / " + experienceCap);
        gameView.getStatusPanel().setBorder(BorderFactory.createEtchedBorder());
        gameView.getStatusPanel().add(healthBar);
        gameView.getStatusPanel().add(levelLabel);
        gameView.getStatusPanel().add(classLabel);
        gameView.getStatusPanel().add(experienceLabel);

        gameView.getStatusPanel().revalidate();
        gameView.getStatusPanel().repaint();
    }

    /**
     * Returns the health bar label based on the player's current health,
     * health cap.
     *
     * @return The health bar label.
     */
    private GameLabel getHealthBar() {
        final double playerHealth = gameController.getCharacterController().getPlayerHealth();
        final double playerHealthCap = gameController.getCharacterController().getPlayerHealthCap();
        final GameLabel healthBar = new GameLabel(
                "Health: " + gameController.getCharacterController().getPlayerHealth() + " / "
                        + gameController.getCharacterController().getPlayerHealthCap());
        if (playerHealth <= (playerHealthCap / 100) * HEALTH_CRITICAL_PERCENTAGE) {
            healthBar.setForeground(Color.RED);
        } else {
            healthBar.setForeground(Color.GREEN);
        }
        return healthBar;
    }
}
