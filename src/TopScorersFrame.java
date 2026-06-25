import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {
    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame(Player currentPlayer) {
        playerService = new PlayerService();

        setTitle("Top 5 Scorers");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        String[] columns = {"Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Get Top 5 players from playerService
        ArrayList<Player> topPlayers = playerService.getTopFiveScorers();

        // Add each player data into the table model
        for (Player p : topPlayers) {
            Object[] row = {
                p.getUsername(),
                p.getWins(),
                p.getLosses(),
                p.getDraws(),
                p.getScore()
            };
            model.addRow(row);
        }

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.addActionListener(e -> {
            MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
            menuFrame.setVisible(true);
            this.dispose();
        });
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
