package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;

import javax.swing.*;
import java.awt.*;

public class Hud {
    private final GameView gameView;
    private final GameController gameController;

    public Hud(GameView gameView, GameController gameController) {
        this.gameView = gameView;
        this.gameController = gameController;
    }

    public void initialize() {
        JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());
        JPanel flowButtonPanelSouth = new JPanel(new FlowLayout());
        gameView.getGameHudPanel().add(flowButtonPanelNorth, BorderLayout.NORTH);
        gameView.getGameHudPanel().add(flowButtonPanelSouth, BorderLayout.SOUTH);

        GameButton inventoryButton = new GameButton("Inventory");
        inventoryButton.addActionListener(e -> gameView.showInventory());

        GameButton menuButton = new GameButton("Menu");
        menuButton.addActionListener(e -> gameView.showMenu());

        flowButtonPanelNorth.add(inventoryButton);
        flowButtonPanelNorth.add(menuButton);

        refreshStatusPanel();

        flowButtonPanelSouth.add(gameView.getStatusPanel());
    }

    public void refreshStatusPanel() {
        gameView.getStatusPanel().removeAll();
        double playerHealth = gameController.getCharacterController().getPlayerHealth();
        double playerHealthCap = gameController.getCharacterController().getPlayerHealthCap();
        GameLabel healthBar = new GameLabel("Health: " + playerHealth + " / " + playerHealthCap);
        if (playerHealth <= (playerHealthCap / 100) * GameView.HEALTH_CRITICAL_PERCENTAGE) {
            healthBar.setForeground(Color.RED);
        } else {
            healthBar.setForeground(Color.GREEN);
        }
        GameLabel levelLabel = new GameLabel("Level: " + gameController.getCharacterController().getPlayerLevel());
        GameLabel classLabel = new GameLabel("Class: " + gameController.getCharacterController().getPlayerClassName());
        int currentExperience = gameController.getCharacterController().getPlayerCurrentExperience();
        int experienceCap = gameController.getCharacterController().getPlayerExperienceCap();
        GameLabel experienceLabel = new GameLabel("Experience: " + currentExperience + " / " + experienceCap);
        gameView.getStatusPanel().setBorder(BorderFactory.createEtchedBorder());
        gameView.getStatusPanel().add(healthBar);
        gameView.getStatusPanel().add(levelLabel);
        gameView.getStatusPanel().add(classLabel);
        gameView.getStatusPanel().add(experienceLabel);

        gameView.getStatusPanel().revalidate();
        gameView.getStatusPanel().repaint();
    }
}
