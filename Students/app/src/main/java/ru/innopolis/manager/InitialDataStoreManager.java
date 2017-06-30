package ru.innopolis.manager;

import ru.innopolis.data.InitialDataStore;

/**
 * Created by ibrahim on 6/28/2017.
 */

public class InitialDataStoreManager {

    private static InitialDataStore initialData = InitialDataStore.getInstance();

    public static void generateInitialData() {
        initialData.generateAll();
    }
}
