package software.ulpgc.kata6.viewmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Histogram<T> {
    private final Map<T, Integer> map = new HashMap<>();

    public void increment(T key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public int get(T key) {
        return map.getOrDefault(key, 0);
    }

    public Set<T> keys() {
        return map.keySet();
    }
}

