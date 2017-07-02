package ru.innopolis.manager;

import ru.innopolis.data.LessonDataStore;
import ru.innopolis.model.Lesson;

/**
 * Created by ibrahim on 7/2/2017.
 */

public class LessonManager {
    private static LessonDataStore lessonDataStore = LessonDataStore.getInstance();

    public static void updateLesson(Lesson lesson) {
        lessonDataStore.updateLesson(lesson);
    }
}
