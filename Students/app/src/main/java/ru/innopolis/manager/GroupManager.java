package ru.innopolis.manager;

import java.util.List;

import ru.innopolis.data.GroupDataStore;
import ru.innopolis.model.Group;

/**
 * Created by ibrahim on 6/30/2017.
 */

public class GroupManager {
    private static GroupDataStore groupDataStore = GroupDataStore.getInstance();

    public static Group findGroupById(Long groupId) {
        return groupDataStore.findGroupById(groupId);
    }

    public static List<Group> getAllGroups() {
        return groupDataStore.getAllGroups();
    }
}
