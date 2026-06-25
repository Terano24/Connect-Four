-- Database: game_project

-- Create the users table to handle both credentials and game statistics
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    games_played INT DEFAULT 0,
    games_won INT DEFAULT 0,
    games_lost INT DEFAULT 0,
    games_drawn INT DEFAULT 0,
    score INT DEFAULT 0
);
