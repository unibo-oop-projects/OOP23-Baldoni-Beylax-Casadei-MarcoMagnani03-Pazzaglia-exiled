package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.controller.GameControllerImpl;
import unibo.exiled.model.character.player.PlayerClassImpl;
import unibo.exiled.model.game.GameModelImpl;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.FontManager;
import unibo.exiled.view.items.TitleGameLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.Serial;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * View where the player decides his class.
 */
public final class PlayerClassView {
    @Serial
    private static final long serialVersionUID = 7L;
    private static final int MARGIN = 20;
    private static final int BUTTON_FONT_SIZE = 40;
    private final transient GameController controller;
    private final JFrame mainFrame;

    /**
     * PlayerClassView is a graphical user interface component where the player can
     * choose their character class.
     * It displays buttons representing different elemental classes, allowing the
     * player to make a selection.
     */
    public PlayerClassView() {
        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        this.mainFrame.setLayout(new BorderLayout());

        this.controller = new GameControllerImpl(new GameModelImpl());

        final JPanel mainPanel = new JPanel(new BorderLayout());
        this.mainFrame.add(mainPanel, BorderLayout.CENTER);

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

    private void classDecision(final ElementalType playerType) {
        final int result = JOptionPane.showConfirmDialog(
                this.mainFrame,
                "Are you sure you want to choose " + playerType.getName() + " class?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            this.controller.getCharacterController().assignPlayerClass(new PlayerClassImpl(playerType));
            new GameView(this.controller).display();
            this.hide();
        }
    }

    public void display(){
        this.mainFrame.setVisible(true);
    }

    public void hide(){
        this.mainFrame.setVisible(false);
    }

    public void close(){
        this.mainFrame.dispose();
    }
}
