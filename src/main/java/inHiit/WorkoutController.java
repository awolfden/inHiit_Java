package inHiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController

public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private CorsConfig corsConfig;

    // Get All
    @GetMapping("/workouts")
    public Object hello(){
        return workoutRepository.findAll();
    }

    // Add Item Route POST /:id
    @PostMapping("/workouts")
    public Workout newString(@RequestBody Workout workout, HttpSession session) throws Exception{
        User user = userRepository.findByUsername(session.getAttribute("username").toString());
        if(user == null){
            throw new Exception("you must be logged in");
        }
        workout.setUser(user);
        Workout newWorkout = workoutRepository.save(workout);
        return newWorkout;


    }

    // Show Route GET one /:id
    @GetMapping("/workouts/{id}")
    public Workout show(@PathVariable("id") Long id) throws Exception{
        Optional<Workout> response = workoutRepository.findById(id);
        if(response.isPresent()){
            return response.get();
        }
        throw new Exception("no such post");    // need to add throw error Exception on show route
    }


    // Delete Route DELETE /:id
    @DeleteMapping("/workouts/{id}")
    public String delete(@PathVariable("id") Long id){
        workoutRepository.deleteById(id);
        return("delete route hit, deleted " + id );  // no error exception needed, delete auto errors if not found
    }

    // Edit Item Route PUT /:id
    @PutMapping("/workouts/{id}")
    public Workout edit(@PathVariable("id") Long id, @RequestBody Workout formData) throws Exception {
        Optional<Workout> response = workoutRepository.findById(id);
        if(response.isPresent()){
            Workout workout = response.get();
            workout.setName(formData.getName());
            workout.setIntervalOne(formData.getIntervalOne());
            workout.setIntervalTwo(formData.getIntervalTwo());
            workout.setCycles(formData.getCycles());
            return workoutRepository.save(workout);
        }
        throw new Exception("no such post");
    }
}
