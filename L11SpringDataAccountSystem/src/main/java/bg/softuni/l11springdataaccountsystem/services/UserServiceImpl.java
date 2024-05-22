package bg.softuni.l11springdataaccountsystem.services;

import bg.softuni.l11springdataaccountsystem.models.Account;
import bg.softuni.l11springdataaccountsystem.models.User;
import bg.softuni.l11springdataaccountsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        if (user.getUsername().isBlank() || user.getAge() < 18) {
            throw new RuntimeException("Validation failed");
        }

        Optional<User> byUsername = this.userRepository.getByUsername(user.getUsername());
        if (byUsername.isPresent()) {
            throw new RuntimeException("Username already in use");
        }

        Account account = new Account();
        user.addAccount(account);

        this.userRepository.save(user);


    }
}
