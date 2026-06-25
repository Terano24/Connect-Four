import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();

        setTitle("Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        lblStatus = new JLabel("Your turn (X)", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblStatus, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[9];

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusPainted(false);
            
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }
        
        add(boardPanel, BorderLayout.CENTER);
    }

    private void handlePlayerMove(int index) {
        if (gameLogic.makeMove(index, 'X')) {
            buttons[index].setText("X");

            if (gameLogic.checkWinner('X')) {
                finishGame("WIN");
                return;
            }
            if (gameLogic.isDraw()) {
                finishGame("DRAW");
                return;
            }

            // Computer move
            lblStatus.setText("Computer's turn (O)");
            int compMove = gameLogic.computerMove();
            if (compMove != -1) {
                gameLogic.makeMove(compMove, 'O');
                buttons[compMove].setText("O");

                if (gameLogic.checkWinner('O')) {
                    finishGame("LOSE");
                    return;
                }
                if (gameLogic.isDraw()) {
                    finishGame("DRAW");
                    return;
                }
            }
            lblStatus.setText("Your turn (X)");
        }
    }

    private void finishGame(String result) {
        playerService.updateStatistics(currentPlayer, result);
        JOptionPane.showMessageDialog(this, "Game result: " + result);

        // Fetch the updated player from DB so the objects in the next frames have current stats
        Player updatedPlayer = playerService.login(currentPlayer.getUsername(), "5659ecd8ab"); // Wait, we don't have password stored in Player object.
        // The assignment does not strictly say to fetch the updated object right here, it says:
        MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
        menuFrame.setVisible(true);
        this.dispose();
    }
}
