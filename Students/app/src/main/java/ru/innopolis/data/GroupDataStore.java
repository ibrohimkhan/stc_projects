package ru.innopolis.data;

import java.util.List;

import ru.innopolis.model.Group;

/**
 * Created by ibrahim on 6/30/2017.
 */

public class GroupDataStore {
    private static GroupDataStore instance;
    private InitialDataStore dataStore = InitialDataStore.getInstance();

    private GroupDataStore() {}

    public static GroupDataStore getInstance() {
        if (instance == null) {
            synchronized (GroupDataStore.class) {
                if (instance == null) instance = new GroupDataStore();
            }
        }

        return instance;
    }

    public Group findGroupById(Long groupId) {
        List<Group> groups = dataStore.getGroups();

        for (Group group : groups) {
            if (group.getGroupId() == groupId) return group;
        }

        return null;
    }

    public List<Group> getAllGroups() {
        return dataStore.getGroups();
    }
}
