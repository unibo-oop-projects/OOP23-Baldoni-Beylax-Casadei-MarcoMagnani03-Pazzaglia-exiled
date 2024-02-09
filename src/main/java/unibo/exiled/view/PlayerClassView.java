package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.controller.GameControllerImpl;
import unibo.exiled.model.character.CharacterClassImpl;
import unibo.exiled.model.game.GameModelImpl;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.FontManager;
import unibo.exiled.view.items.TitleGameLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.Random;

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
    private static final Color RANDOM_BUTTON_COLOR = new Color(255, 255, 255);
    private static final Random RANDOM = new Random();
    private static final int MARGIN = 20;
    private static final int BUTTON_FONT_SIZE = 40;
    private static final String RANDOM_IMAGE_PATH = "src"
            + File.separator + "main"
            + File.separator + "java"
            + File.separator + "unibo"
            + File.separator + "exiled"
            + File.separator + "resources"
            + File.separator + "class"
            + File.separator + "random.png";

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
        this.mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setName("Player class");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        this.mainFrame.setLayout(new BorderLayout());

        this.controller = new GameControllerImpl(new GameModelImpl());

        final JPanel mainPanel = new JPanel(new BorderLayout());
        this.mainFrame.add(mainPanel, BorderLayout.CENTER);

        final JPanel buttonClassPanel = new JPanel(new GridLayout(3, 2, MARGIN, MARGIN));
        final JButton normalButton = createButton(ElementalType.NORMAL);
        final JButton fireButton = createButton(ElementalType.FIRE);
        final JButton waterButton = createButton(ElementalType.WATER);
        final JButton boltButton = createButton(ElementalType.BOLT);
        final JButton grassButton = createButton(ElementalType.GRASS);
        final JButton randomButton = createRandomButton();

        buttonClassPanel.add(fireButton);
        buttonClassPanel.add(waterButton);
        buttonClassPanel.add(boltButton);
        buttonClassPanel.add(grassButton);
        buttonClassPanel.add(normalButton);
        buttonClassPanel.add(randomButton);

        final JLabel titleLabel = new TitleGameLabel("Choose Your Class");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonClassPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a button for selecting the player's class.
     *
     * @param elementalType The elemental type associated with the button.
     * @return The created JButton.
     */
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
     * Creates a button for selecting the random class.
     *
     * @return The created JButton.
     */
    private JButton createRandomButton() {
        final JButton button = new JButton("Random");
        button.setFont(FontManager.getCustomFont(BUTTON_FONT_SIZE));
        button.setBackground(RANDOM_BUTTON_COLOR);
        button.addActionListener(e -> randomClassDecision());
        button.setIcon(new ImageIcon(new ImageIcon(RANDOM_IMAGE_PATH).getImage().getScaledInstance(BUTTON_FONT_SIZE,
                BUTTON_FONT_SIZE, Image.SCALE_SMOOTH)));
        return button;
    }

    /**
     * Handles the decision when the player selects a class.
     *
     * @param playerType The elemental type chosen by the player.
     */
    private void classDecision(final ElementalType playerType) {
        final int result = JOptionPane.showConfirmDialog(this.mainFrame, "Are you sure you want to choose "
                + playerType.getName() + " class?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            this.controller.getCharacterController().assignPlayerClass(new CharacterClassImpl(playerType));
            new GameView(this.controller).display();
            this.hide();
        }
    }

    /**
     * Manages the random decision when the player selects the random option class.
     */
    private void randomClassDecision() {
        final ElementalType randomElementalType =
                ElementalType.values()[RANDOM.nextInt(ElementalType.values().length)];
        final int result = JOptionPane.showConfirmDialog(this.mainFrame, "The "
                        + randomElementalType.getName()
                        + " class was randomly selected, do you want to continue?", "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            this.controller.getCharacterController().assignPlayerClass(new CharacterClassImpl(randomElementalType));
            new GameView(this.controller).display();
            this.hide();
        }
    }

    /**
     * Displays the player class selection view.
     */
    public void display() {
        this.mainFrame.setVisible(true);
    }

    /**
     * Hides the player class selection view.
     */
    public void hide() {
        this.mainFrame.setVisible(false);
    }

    /**
     * Closes the player class selection view.
     */
    public void close() {
        this.mainFrame.dispose();
    }
}
