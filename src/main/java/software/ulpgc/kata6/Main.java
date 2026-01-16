package software.ulpgc.kata5;

import software.ulpgc.kata6.control.HistogramControl;
import software.ulpgc.kata6.io.*;
import software.ulpgc.kata6.model.Title;
import software.ulpgc.kata6.view.HistogramDisplay;
import software.ulpgc.kata6.view.JFreeChartHistogramDisplay;
import software.ulpgc.kata6.viewmodel.Histogram;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:titleS:db");
        File file = new File("titles.tsv");

        List<Title> titlesFromFile = new FileTitleLoader(file, new TsvTitleDeserializer()).load();

        TitleWriter writer = new SQLiteTitleWriter(connection);
        writer.write(titlesFromFile);
        System.out.println("Datos guardados en bd");


        TitleReader reader = new SQLiteTitleReader(connection);
        List<Title> titlesFromDb = reader.read();
        System.out.println("Datos leidos en bd" + titlesFromDb.size());

        HistogramControl control = new HistogramControl();

        Histogram<String> histogram = control.build(titlesFromDb, Title::getTitleType);

        new JFreeChartHistogramDisplay(histogram).display();
    }
}
