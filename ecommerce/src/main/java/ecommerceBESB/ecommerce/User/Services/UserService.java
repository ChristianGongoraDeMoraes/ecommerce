package ecommerceBESB.ecommerce.User.Services;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ecommerceBESB.ecommerce.Errors.Exceptions.UserNotFoundException;
import ecommerceBESB.ecommerce.User.User;
import ecommerceBESB.ecommerce.User.Repositories.UserRepository;
import ecommerceBESB.ecommerce.User.Requests.UserSaveRequest;
import ecommerceBESB.ecommerce.User.Requests.UserUpdateRequest;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAllUsers();
    }

    public User getUserByEmail(String email) throws UserNotFoundException{
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not found!"));
    }

    public User saveUser(UserSaveRequest userReq){
        User user = User.builder()
            .name(userReq.name())
            .email(userReq.email())
            .password(userReq.password())
            .build();
        return userRepository.save(user);
    }

    public User updateUser(UUID id, UserUpdateRequest userReq) throws UserNotFoundException{
        User user = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User Not found!"));
        user = User.builder()
            .id(user.getId())
            .name(userReq.name())
            .email(userReq.email())
            .password(userReq.password())
            .build();
        return userRepository.save(user);
    }

    public String deleteUser(UUID id) throws UserNotFoundException{
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "User Deleted";
        }else{
            throw new UserNotFoundException("User Not found!");
        }
    }
}


