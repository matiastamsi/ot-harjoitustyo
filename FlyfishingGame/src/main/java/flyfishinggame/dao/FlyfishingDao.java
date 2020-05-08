package flyfishinggame.dao;

import java.sql.*;

/**
 * This class is responsible for saving and fetching data in and out from the
 * database.
 *
 * @author Matias Tamsi
 */
public class FlyfishingDao {

    private Connection db;
    public Statement s;

    /**
     * Class defines a new database if not already existing.
     *
     * @throws SQLException whether getting the connection to the JDBC-driver
     * fails.
     */
    public FlyfishingDao() throws SQLException {
        this.db = DriverManager.getConnection(
                "jdbc:sqlite:flyfishinggamescores.db");
        this.s = db.createStatement();
    }

    /**
     * The method creates a database (if not already existing) and adds a table
     * "Scores" in it. In that table will be stored players nickname and m
     *
     */
    public void createDatabase() {
        try {
            s.execute(
                    "CREATE TABLE Scores("
                    + "id INTEGER PRIMARY KEY,"
                    + "nickname TEXT,"
                    + "score INTEGER)");

        } catch (SQLException e) {
            System.out.println("Found already existing database.");
        }
    }

    /**
     * Method adds a new score to the database.
     *
     * @param nickname player's nickname.
     * @param score the points.
     * @throws SQLException informs if something went wrong in insert operation.
     */
    public void addScore(String nickname, int score) throws SQLException {
        PreparedStatement p = db.prepareStatement(
                "INSERT INTO Scores (nickname, score) VALUES (?,?)");
        p.setString(1, nickname);
        p.setInt(2, score);
        p.executeUpdate();
    }

    /**
     * Fetch the top five scores and the nicknames of those who got the scores.
     *
     * @return String of the top five scores. This string is been set later to
     * Text-object.
     * @throws SQLException whether something goes wrong in select operation.
     */
    public String getTopFive() throws SQLException {
        String scores = "";
        int i = 1;
        PreparedStatement p = db.prepareStatement(
                "SELECT * FROM Scores ORDER BY score DESC LIMIT 5");
        ResultSet r = p.executeQuery();
        while (r.next()) {
            scores += i + ". " + r.getInt("score") + ", "
                    + r.getString("nickname") + "\n";
            i++;
        }
        return scores;
    }
}
