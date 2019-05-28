package inHiit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkoutController {
    @GetMapping("/")
    public String hello(){
        return "hello out there";
    }
}
