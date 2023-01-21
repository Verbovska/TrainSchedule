package eva.trainschedule.repositories;

import eva.trainschedule.models.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface UserRepository {

    User save(User user);

    User findById(int id);

    User findByName(String name);

//    User findByEmail(String email);

    Collection<User> findAll(int page, int size, String emailType);

    User removeById(int id);
}
