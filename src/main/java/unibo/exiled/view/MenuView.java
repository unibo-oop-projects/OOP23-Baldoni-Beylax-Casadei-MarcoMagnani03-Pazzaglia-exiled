package unibo.exiled.view;

import unibo.exiled.model.menu.Command;
import unibo.exiled.model.menu.MenuItem;
import unibo.exiled.view.items.GameButton;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.File;
import java.io.Serial;
import java.util.List;
import java.util.Optional;

/**
 * The view of the in-game menu.
 */
public final class MenuView extends JPanel {
    @Serial
    private static final long serialVersionUID = 4L;
    private static final int GRID_INSET = 3;

    /**
     * The constructor of the in-game view.
     *
     * @param gameView The view of the game.
     */
    public MenuView(final List<MenuItem> menuItems, final Optional<GameView> gameView) {
        super();

        // CREATING STANDARD UI
        final JPanel buttonListPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        cnst.insets = new Insets(GRID_INSET, GRID_INSET, GRID_INSET, GRID_INSET);
        cnst.fill = GridBagConstraints.HORIZONTAL;
        final JLabel logoLabel = new JLabel(new ImageIcon("src"
                + File.separator
                + "main" + File.separator
                + "java" + File.separator
                + "unibo" + File.separator
                + "exiled" + File.separator
                + "resources" + File.separator
                + "logo.png"));
        buttonListPanel.add(logoLabel);
        cnst.gridy++;

        for (MenuItem menuItem : menuItems) {
            final GameButton gameButton = new GameButton(menuItem.getItemText());
            gameButton.setActionCommand(menuItem.getItemCommand().getCommandString());
            gameButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals(Command.NEW_GAME.getCommandString())) {
                        final PlayerClassView playerClassView = new PlayerClassView();
                        playerClassView.display();
                        for (Frame f : Frame.getFrames()) {
                            if(!f.getName().equals("Player class")){
                                f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                            }
                        }
                    } else if (e.getActionCommand().equals(Command.CLOSE_MENU.getCommandString())) {
                        gameView.ifPresent(GameView::hideMenu);
                    } else if (e.getActionCommand().equals(Command.QUIT.getCommandString())) {
                        final int dialogResult = JOptionPane.showConfirmDialog(null,
                                "Would you like to quit the game?", "Warning",
                                JOptionPane.YES_NO_OPTION);

                        if (dialogResult == JOptionPane.YES_OPTION) {
                            for (Frame f : Frame.getFrames()) {
                                f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Command is not valid");
                    }
                }

            });

            buttonListPanel.add(gameButton, cnst);
            cnst.gridy++;
        }
        // final ActionListener buttonListener = new MenuItemActionListener(gameView);

        // if (gameView.isPresent()) { // Menu In-Game
        // final GameButton resumeGameButton = new GameButton("RESUME");
        // resumeGameButton.setActionCommand("close_menu");
        // resumeGameButton.addActionListener(buttonListener);
        // buttonListPanel.add(resumeGameButton, cnst);
        // cnst.gridy++;
        // }

        // final GameButton newGameButton = new GameButton("NEW GAME");
        // newGameButton.setActionCommand("new_game");
        // newGameButton.addActionListener(buttonListener);
        // buttonListPanel.add(newGameButton, cnst);
        // cnst.gridy++;

        // final GameButton quitGameButton = new GameButton("QUIT");
        // quitGameButton.setActionCommand("quit");
        // quitGameButton.addActionListener(buttonListener);
        // buttonListPanel.add(quitGameButton, cnst);
        // cnst.gridy++;

        this.setLayout(new BorderLayout());
        this.add(buttonListPanel, BorderLayout.CENTER);
    }

}
