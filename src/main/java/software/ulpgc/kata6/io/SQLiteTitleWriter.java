package software.ulpgc.kata6.io;

import software.ulpgc.kata5.model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLiteTitleWriter implements TitleWriter{
    private final Connection connection;

    public SQLiteTitleWriter(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void write(List<Title> titles) {
        try {
            createTable();
            insertData(titles);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException{
        String sql = """
                CREATE TABLE IF NOT EXISTS titles(
                    id TEXT PRIMARY KEY.
                    type TEXT,
                    title TEST
                )
                """;
        connection.createStatement().execute(sql);
    }

    private void insertData(List<Title> titles) throws SQLException{
        String sql = "INSERT OR IGNORE INTO titles(id, type, title) VALUES(?,?,?)";

        connection.setAutoCommit(false);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            for (Title title : titles){
                pstmt.setString(1, title.getId());
                pstmt.setString(2, title.getTitleType());
                pstmt.setString(3, title.getPrimaryTitle());
            }
            pstmt.executeBatch();
            connection.commit();
        }
    }
}