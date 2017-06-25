package ru.innopolis.utils;

import java.util.UUID;

/**
 * Created by ibrahim on 6/22/2017.
 */

public class UniqueIdGenerator {
    public static Long generateUid() {
        return UUID.randomUUID().getLeastSignificantBits();
    }
}
