package software.ulpgc.kata6.io;

import software.ulpgc.kata6.model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTitleReader implements TitleReader{
    private final Connection connection;

    public SQLiteTitleReader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Title> read() {
        List<Title> titles = new ArrayList<>();
        String sql = "SELECT * FROM titles";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                titles.add(new Title(
                        resultSet.getString("id"),
                        resultSet.getString("type"),
                        resultSet.getString("title")
                ));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return titles;
    }
}
