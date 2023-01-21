package eva.trainschedule.services.implementation;

import eva.trainschedule.models.User;
import eva.trainschedule.repositories.UserRepository;
import eva.trainschedule.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }
}
