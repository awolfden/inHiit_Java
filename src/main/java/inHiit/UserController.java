package inHiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users")
    public Iterable userIndex(){
        return userRepository.findAll();
    }

    //    @PostMapping("/users")
//    public User create(@RequestBody User user){
//        return userService.saveUser(user);
//    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user, HttpSession session){
        User newUser = userService.saveUser(user);
        if(newUser != null){
            session.setAttribute("username", newUser.getUsername());
        }
        return newUser;
    }
//    @GetMapping("/users/{id}")
//    public User show(@PathVariable("id") Long id) throws Exception{
//        Optional<User> response = userRepository.findById(id);
//        if(response.isPresent()){
//            return response.get();
//        } else {
//            throw new Exception("User doesn't exist");
//        }
//    }


    // as another route listed after class variables
    @PostMapping("/login")
    public User login(@RequestBody User login, HttpSession session) throws IOException {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(login.getUsername());
        if(user ==  null){
            throw new IOException("Invalid Credentials");
        }
        boolean valid = bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword());
        if(valid){
            session.setAttribute("username", user.getUsername());
            return user;
        }else{
            throw new IOException("Invalid Credentials");
        }
    }

    //HACKAROUND FOR THE INFINITE LOOP PROBLEM
    @GetMapping("/users/{id}")
    public HashMap<String, Object> findUser(@PathVariable("id") Long id)throws Exception{
        Optional<User> response = userRepository.findById(id);
        if(response.isPresent()){
            User user = response.get();
            Iterable<Workout> workouts = workoutRepository.findByUser(user);
            HashMap<String, Object> result = new HashMap<>();
            result.put("user", user);
            result.put("workouts", workouts);
            return result;
        }
        throw new Exception("no such user");
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "You deleted user: " + id;
    }

    @PutMapping("/users/{id}")
    public User edit(@PathVariable("id") Long id, @RequestBody User formData) throws Exception{
        Optional<User> response = userRepository.findById(id);
        if(response.isPresent()){
            User user = response.get();
            user.setUsername(formData.getUsername());
            user.setPassword(formData.getPassword());
            return userRepository.save(user);
        } else {
            throw new Exception("No such user");
        }
    }
}