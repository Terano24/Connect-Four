import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        setTitle("Main Menu - Welcome " + player.getUsername());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        btnStartGame = new JButton("Start Game");
        btnStatistics = new JButton("View My Statistics");
        btnTopScorers = new JButton("View Top 5 Scorers");
        btnExit = new JButton("Exit");

        panel.add(btnStartGame);
        panel.add(btnStatistics);
        panel.add(btnTopScorers);
        panel.add(btnExit);

        add(panel);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            this.dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
            this.dispose();
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame(currentPlayer);
            topFrame.setVisible(true);
            this.dispose();
        });

        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }
}
