package service;

import java.util.HashMap;

public interface BaseBallService {
    void register(HashMap<String, String> map);

    void show();
    default void show(HashMap<String, String> map) {

    };
}
