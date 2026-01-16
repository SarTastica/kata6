package software.ulpgc.kata6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseLoader {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:titles.db";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            statement.execute("DROP TABLE IF EXISTS titles");

            statement.execute("CREATE TABLE titles (" +
                    "id TEXT PRIMARY KEY," +
                    "type TEXT," +
                    "title TEXT)");

            String insertSQL = "INSERT INTO titles (id, type, title) VALUES " +
                    "('tt0001', 'movie', 'Carmencita')," +
                    "('tt0002', 'short', 'Le clown et ses chiens')," +
                    "('tt0003', 'short', 'Pauvre Pierrot')," +
                    "('tt0004', 'short', 'Un bon bock')," +
                    "('tt0005', 'short', 'Blacksmith Scene')," +
                    "('tt0006', 'movie', 'Chinese Opium Den')," +
                    "('tt0007', 'tvSeries', 'Coronation Street')," +
                    "('tt0008', 'tvEpisode', 'Episode #1.1')," +
                    "('tt0009', 'tvSeries', 'EastEnders')," +
                    "('tt0010', 'videoGame', 'The Legend of Zelda')," +
                    "('tt0011', 'movie', 'The Matrix')," +
                    "('tt0012', 'movie', 'Avatar')," +
                    "('tt0013', 'tvMiniSeries', 'Chernobyl')," +
                    "('tt0014', 'tvMovie', 'Sharknado')," +
                    "('tt0015', 'videoGame', 'Grand Theft Auto V')," +
                    "('tt0016', 'movie', 'Inception')," +
                    "('tt0017', 'short', 'Luxo Jr.')," +
                    "('tt0018', 'tvSeries', 'Breaking Bad')," +
                    "('tt0019', 'tvEpisode', 'Ozymandias')," +
                    "('tt0020', 'videoGame', 'Super Mario Bros')," +
                    "('tt0021', 'movie', 'Titanic')," +
                    "('tt0022', 'tvSeries', 'Game of Thrones')," +
                    "('tt0023', 'tvSpecial', 'Oscars 2024')," +
                    "('tt0024', 'movie', 'Avengers: Endgame')," +
                    "('tt0025', 'short', 'Bao')," +
                    "('tt0026', 'tvSeries', 'The Wire')," +
                    "('tt0027', 'videoGame', 'Tetris')," +
                    "('tt0028', 'movie', 'The Godfather')," +
                    "('tt0029', 'tvMovie', 'High School Musical')," +
                    "('tt0030', 'movie', 'Pulp Fiction')";

            statement.execute(insertSQL);

            System.out.println("Base de datos creada.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
