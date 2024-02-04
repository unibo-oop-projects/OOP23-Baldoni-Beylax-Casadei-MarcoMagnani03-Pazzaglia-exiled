package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.view.items.TitleGameLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
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
     * PlayerClassView is a graphical user interface component where the player can choose their character class.
     * It displays buttons representing different elemental classes, allowing the player to make a selection.
     *
     * @param gameController The GameController instance managing the game logic.
     * @param gameView       The GameView instance responsible for rendering the game interface.
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
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalStrut(MARGIN), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(final ElementalType elementalType) {
        final JButton button = new JButton(elementalType.getName(), elementalType.getElementalImage());
        button.setFont(new Font("Arial", Font.BOLD, BUTTON_FONT_SIZE));
        button.setBackground(elementalType.getElementalColor());
        button.addActionListener(e -> classDecision(elementalType));
        return button;
    }

    /**
     * method where the player class is set.
     *
     * @param playerClass the class decided by the user.
     */
    private void classDecision(final ElementalType playerClass) {
        final int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to choose " + playerClass.getName() + " class?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            this.controller.getCharacterController().setPlayerClass(playerClass);
            this.gameView.initializeHUD();
            this.gameView.hidePlayerClass();
        }
    }
}

