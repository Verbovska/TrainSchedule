package eva.trainschedule.services;

import eva.trainschedule.models.User;

public interface UserService {
    void save(User user);
    User findByName(String username);
    User findById(int id);
}
