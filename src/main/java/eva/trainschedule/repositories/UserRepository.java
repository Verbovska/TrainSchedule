package eva.trainschedule.repositories;

import eva.trainschedule.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void save(User user);

    User findById(int id);

    User findByName(String name);

    User findByEmail(String email);

}
