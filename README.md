# Simple Tic-Tac-Toe Game with Java Swing, Login, and Statistics

## Student Information
Name: [Your Name]
Student ID: [Your Student ID]
Class: [Your Class]

## Project Description
This project is a simple Tic-Tac-Toe game built using Java Swing. The application includes login, game statistics, and Top 5 scorer feature. It connects to a single-table MySQL database using JDBC to persist player data and scores.

## Features
- Login using database
- Play Tic-Tac-Toe using Swing GUI
- Record wins, losses, draws, and score
- Display personal statistics
- Display Top 5 scorers using JTable

## Database
Database used: MySQL 

## How to Run
1. Create the database in your MySQL environment (e.g., XAMPP phpMyAdmin).
2. Import `schema.sql` located in the `database/` folder to create the `players` table.
3. Open the Java project in your IDE.
4. Add JDBC driver (`library/mysql-connector-j-9.7.0.jar`) to your project classpath.
5. Configure `DatabaseManager.java` (Ensure your database port, username, and password are correct. The default in the code is port 3307).
6. Run `Main.java`.

## Class Explanation
Main: Starts the program and opens the Login Window by launching the Swing Event Dispatch Thread.
DatabaseManager: Handles JDBC database connection configuration and provisioning.
Player: A model class that stores player data such as id, username, wins, losses, draws, and score.
PlayerService: Handles login, retrieving player data, updating statistics after a game, and retrieving the Top 5 scorers from the database.
GameLogic: Handles move validation, winner checking (horizontal, vertical, diagonal), draw checking, and simple random computer moves on the 3x3 board.
LoginFrame: Swing window for username and password input, as well as new user registration.
MainMenuFrame: Swing window for the main menu navigation after a successful login.
GameFrame: Swing window for playing the game, featuring a 3x3 grid of JButtons. Connects to `GameLogic` and updates the database via `PlayerService`.
StatisticsFrame: Swing window for showing personal statistics of the currently logged-in player.
TopScorersFrame: Swing window for showing Top 5 scorers using a JTable parsed directly from the database.

## Screenshots
*(Add screenshots here)*
- `screenshots/login-window.png`
- `screenshots/game-window.png`
- `screenshots/top-scorers-window.png`

## Video Link
YouTube: [Insert your YouTube video link here]
