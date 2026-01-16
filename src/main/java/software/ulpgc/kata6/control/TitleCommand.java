package software.ulpgc.kata6.control;

import software.ulpgc.kata6.io.SQLiteTitleReader;
import software.ulpgc.kata6.io.TitleReader;
import software.ulpgc.kata6.model.Title;
import software.ulpgc.kata6.viewmodel.Histogram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitleCommand {
    public Map<String, Integer> execute(){
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:titles.db")){
            TitleReader reader = new SQLiteTitleReader(connection);
            List<Title> titles = reader.read();

            HistogramControl control = new HistogramControl();
            Histogram<String> histogram = control.build(titles, Title::getTitleType);

            Map<String, Integer> result = new HashMap<>();
            for(String key : histogram.keys()){
                result.put(key, histogram.get(key));
            }
            return result;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
