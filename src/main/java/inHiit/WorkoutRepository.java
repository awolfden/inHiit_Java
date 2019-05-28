package inHiit;

import org.springframework.data.repository.CrudRepository;

public interface WorkoutRepository extends CrudRepository<Workout, Long> { //what model to modify, what id type it is

}