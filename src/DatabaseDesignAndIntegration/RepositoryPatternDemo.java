package DatabaseDesignAndIntegration;

import java.util.*;

class User {

    int id;
    String name;

    User(int id, String name) {

        this.id = id;
        this.name = name;
    }
}

class UserRepository {

    private final List<User> database = new ArrayList<>();

    public void save(User user) {

        database.add(user);

    }

    public List<User> findAll() {

        return database;

    }
}

public class RepositoryPatternDemo {

    public static void main(String[] args) {

        UserRepository repository = new UserRepository();

        repository.save(new User(1, "Nayeem"));
        repository.save(new User(2, "Rahul"));

        for (User user : repository.findAll()) {

            System.out.println(user.id + " " + user.name);

        }
    }
}