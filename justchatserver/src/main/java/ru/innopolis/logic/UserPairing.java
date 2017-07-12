package ru.innopolis.logic;

import org.eclipse.jetty.websocket.api.Session;
import ru.innopolis.entity.Language;
import ru.innopolis.entity.State;
import ru.innopolis.entity.User;
import ru.innopolis.entity.UserType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Created by ibrahim on 7/12/2017.
 */
public class UserPairing {
    private static Random random = new Random();
    private ConcurrentLinkedQueue<User> users = new ConcurrentLinkedQueue<>();
    private Map<Language, List<User>> usersByLanguage = new ConcurrentHashMap<>();

    private UserPairing() {}

    private static class Helper {
        private static final UserPairing INSTANCE = new UserPairing();
    }

    public static UserPairing getInstance() {
        return Helper.INSTANCE;
    }

    public void link(User user) {
        if (user.getState() == State.WAITING) {
            users.add(user);
            structurize();
        }
    }

    public boolean unlink(Session session) {
        User user = findUserBySession(session);
        boolean removed = users.remove(user);

        if (removed) {
            usersByLanguage = new ConcurrentHashMap<>();
            structurize();
        }

        return removed;
    }

    public User[] findCouple(Session session) {
        User first = findUserBySession(session);
        User second = null;

        if (first.getType() == UserType.LEARNER) second = findMeAnybody(first.getLanguage(), UserType.TEACHER);
        else second = findMeAnybody(first.getLanguage(), UserType.LEARNER);

        return new User[] {first, second};
    }

    private User findMeAnybody(Language language, UserType type) {
        List<User> userList = usersByLanguage.get(language);
        List<User> anybody = userList.stream()
                .filter(user -> user.getType() == type && (user.getState() == State.WAITING || user.getState() == State.ACTIVE))
                .collect(Collectors.toList());

        if (anybody == null || anybody.size() == 0) return null;

        Collections.shuffle(anybody);

        int randomly = random.nextInt(anybody.size()) + 0;
        User anyone = anybody.get(randomly);

        return anyone;
    }

    public User findUserBySession(Session session) {
        return users.stream().filter(user -> session.equals(user.getSession())).findAny().orElse(null);
    }

    private void structurize() {
        Set<Language> languages = new HashSet<>();
        users.stream().forEach(user -> languages.add(user.getLanguage()));

        languages.stream().forEach(language -> {
            List<User> listOfUsers = new ArrayList<>();

            users.stream().forEach(user -> {
                if (language.getLanguage().equals(user.getLanguage().getLanguage())) {
                    listOfUsers.add(user);
                }
            });

            usersByLanguage.put(language, listOfUsers);
        });
    }
}
