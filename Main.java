package software.ulpgc.kata6;

import com.google.gson.Gson;
import software.ulpgc.kata6.control.HistogramControl;
import software.ulpgc.kata6.control.TitleCommand;
import software.ulpgc.kata6.io.*;
import software.ulpgc.kata6.model.Title;
import software.ulpgc.kata6.viewmodel.Histogram;
import spark.Spark;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        prepareDatabse();
        Spark.port(8080);

        Spark.get("/histogram", ((request, response) -> {
            response.type("aplication/json");
            var data = new TitleCommand().execute();
            return new Gson().toJson(data);
        }));
        System.out.println("Servidor escuchando en: http://localhost:8080/histogram");
    }

    private static void prepareDatabse() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:titles.db");
        File file = new File("titles.tsv");
        List<Title> titles = new FileTitleLoader(file, new TsvTitleDeserializer()).load();
        new SQLiteTitleWriter(connection).write(titles);
        connection.close();
    }
}
