package software.ulpgc.kata6.io;

import software.ulpgc.kata6.model.Title;

public interface TitleDeserializer {
    Title deserialize(String line);
}

