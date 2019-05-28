package inHiit;

import javax.persistence.*;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String name;

    private Integer interval_one;

    private Integer interval_two;

    private Integer cycles;

    public User getUser() {return user;}

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameInput){
        this.name = nameInput;
    }

    public Integer getIntervalOne() {
        return interval_one;
    }

    public void setIntervalOne(Integer intervalOne) {
        this.interval_one = intervalOne;
    }

    public Integer getIntervalTwo() {
        return interval_two;
    }

    public void setIntervalTwo(Integer intervalTwo) {
        this.interval_two = intervalTwo;
    }

    public Integer getCycles() {
        return cycles;
    }

    public void setCycles(Integer cycles) {
        this.cycles = cycles;
    }


}