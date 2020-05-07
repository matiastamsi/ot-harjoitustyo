package flyfishinggame.dao;

import java.sql.SQLException;
import java.io.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test the class which is responsible for interaction with the database.
 *
 * @author Matias Tamsi
 */
public class FlyfishingDaoTest {

    FlyfishingDao fD;

    @Before
    public void constructorCreatesNewDao() throws SQLException {
        fD = new FlyfishingDao();
        assertEquals(true, fD.getClass().equals(new FlyfishingDao().getClass()));
    }

    @Test
    public void creationOfTheDatabaseSucceededIfExists() throws SQLException {
        fD.createDatabase();
    }

    @Test
    public void creationOfTheDatabaseSucceededIfNotExists() throws SQLException {
        fD.s.execute("DROP TABLE Scores");
        fD.createDatabase();
    }

    @Test
    public void addingScoreWorks() throws SQLException {
        try {
            fD.addScore("Test", 1);
        } catch (SQLException e) {
            fD = new FlyfishingDao();
            fD.createDatabase();
            fD.addScore("Test", 1);
        }
    }

    @Test
    public void fetchingTheTopScoresWorks() throws SQLException {
        try {
            fD.addScore("Test", 60);
            fD.addScore("Test", 50);
            fD.addScore("Test", 40);
            fD.addScore("Test", 30);
            fD.addScore("Test", 20);
            fD.addScore("Test", 10);
            String score = fD.getTopFive();
            assertEquals(5, score.lines().count(), 0);
        } catch (SQLException e) {
            fD = new FlyfishingDao();
            fD.createDatabase();
            fD.addScore("Test", 60);
            fD.addScore("Test", 50);
            fD.addScore("Test", 40);
            fD.addScore("Test", 30);
            fD.addScore("Test", 20);
            fD.addScore("Test", 10);
            String score = fD.getTopFive();
            assertEquals(5, score.lines().count(), 0);
        }
    }
}
