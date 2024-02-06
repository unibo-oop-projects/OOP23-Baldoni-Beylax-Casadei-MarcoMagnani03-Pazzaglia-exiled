package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.model.character.player.PlayerClassImpl;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.FontManager;
import unibo.exiled.view.items.TitleGameLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * View where the player decides his class.
 */
public final class PlayerClassView extends JPanel {
    private static final long serialVersionUID = 7L;
    private static final int MARGIN = 20;
    private static final int BUTTON_FONT_SIZE = 40;
    private final transient GameController controller;
    private final transient GameView gameView;

    /**
     * PlayerClassView is a graphical user interface component where the player can
     * choose their character class.
     * It displays buttons representing different elemental classes, allowing the
     * player to make a selection.
     *
     * @param gameController The GameController instance managing the game logic.
     * @param gameView       The GameView instance responsible for rendering the
     *                       game interface.
     */
    public PlayerClassView(final GameController gameController, final GameView gameView) {
        this.controller = gameController;
        this.gameView = gameView;
        setLayout(new BorderLayout());

        final JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel(new GridLayout(2, 2, MARGIN, MARGIN));

        final JButton fireButton = createButton(ElementalType.FIRE);
        final JButton waterButton = createButton(ElementalType.WATER);
        final JButton boltButton = createButton(ElementalType.BOLT);
        final JButton grassButton = createButton(ElementalType.GRASS);

        buttonPanel.add(fireButton);
        buttonPanel.add(waterButton);
        buttonPanel.add(boltButton);
        buttonPanel.add(grassButton);

        final JLabel titleLabel = new TitleGameLabel("Choose Your Class");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalStrut(MARGIN), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(final ElementalType elementalType) {
        final JButton button = new JButton(elementalType.getName());
        button.setFont(FontManager.getCustomFont(BUTTON_FONT_SIZE));
        button.setBackground(elementalType.getElementalColor());
        button.addActionListener(e -> classDecision(elementalType));
        final Image scaledImage = elementalType.getElementalImage().getImage().getScaledInstance(BUTTON_FONT_SIZE,
                BUTTON_FONT_SIZE, Image.SCALE_SMOOTH);
        final ImageIcon scaledIcon = new ImageIcon(scaledImage);
        button.setIcon(scaledIcon);

        return button;
    }

    /**
     * Method where the player class is set.
     *
     * @param playerClass the class decided by the user.
     */
    private void classDecision(final ElementalType playerType) {
        final int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to choose " + playerType.getName() + " class?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            this.controller.getCharacterController().assignPlayerClass(new PlayerClassImpl(playerType));
            this.gameView.initializeHUD();
            this.gameView.hidePlayerClass();
        }
    }
}
