package ru.innopolis.data;

import java.util.List;

import ru.innopolis.model.Group;

/**
 * Created by ibrahim on 6/30/2017.
 */

public class GroupDataStore {
    private InitialDataStore dataStore = InitialDataStore.getInstance();

    private GroupDataStore() {}

    private static class Helper {
        private static final GroupDataStore INSTANCE = new GroupDataStore();
    }

    public static GroupDataStore getInstance() {
        return Helper.INSTANCE;
    }

    public Group findGroupById(Long groupId) {
        List<Group> groups = dataStore.getGroups();

        for (Group group : groups) {
            if (group.getGroupId().equals(groupId)) return group;
        }

        return null;
    }

    public List<Group> getAllGroups() {
        return dataStore.getGroups();
    }
}
