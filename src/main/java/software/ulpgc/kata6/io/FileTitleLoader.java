package software.ulpgc.kata6.io;

import software.ulpgc.kata6.model.Title;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTitleLoader implements TitleLoader {
    private final File file;
    private final TitleDeserializer deserializer;

    public FileTitleLoader(File file, TitleDeserializer deserializer) {
        this.file = file;
        this.deserializer = deserializer;
    }

    @Override
    public List<Title> load() {
        List<Title> titles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Title title = deserializer.deserialize(line);
                if (title != null) {
                    titles.add(title);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return titles;
    }
}

