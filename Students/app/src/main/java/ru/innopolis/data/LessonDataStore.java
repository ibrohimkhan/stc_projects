package ru.innopolis.data;

import java.util.List;
import java.util.Map;

import ru.innopolis.model.Group;
import ru.innopolis.model.Lesson;

/**
 * Created by ibrahim on 7/2/2017.
 */

public class LessonDataStore {
    private static LessonDataStore instance;
    private InitialDataStore dataStore = InitialDataStore.getInstance();
    private GroupDataStore groupDataStore = GroupDataStore.getInstance();

    private LessonDataStore() {}

    public static LessonDataStore getInstance() {
        if (instance == null) {
            synchronized (LessonDataStore.class) {
                if (instance == null) instance = new LessonDataStore();
            }
        }
        return instance;
    }

    public void updateLesson(Lesson lesson) {
        Group group = groupDataStore.findGroupById(lesson.getGroupId());
        Map<String, List<Lesson>> map = dataStore.getMapLessons();
        List<Lesson> lessons = map.get(group.getGroupName());

        for (Lesson lesson1 : lessons) {
            if (lesson1.getLessonId().equals(lesson.getLessonId())) {
                lesson1.setDate(lesson.getDate());
                lesson1.setSubject(lesson.getSubject());
                lesson1.setDescription(lesson.getDescription());
                lesson1.getJournal().setDate(lesson.getDate());

                break;
            }
        }
    }
}
