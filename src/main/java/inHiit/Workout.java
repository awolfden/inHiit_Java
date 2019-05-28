package inHiit;

import javax.persistence.*;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer intervalOne;

    private Integer intervalTwo;

    private Integer cycles;

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
        return intervalOne;
    }

    public void setIntervalOne(Integer intervalOne) {
        this.intervalOne = intervalOne;
    }

    public Integer getIntervalTwo() {
        return intervalTwo;
    }

    public void setIntervalTwo(Integer intervalTwo) {
        this.intervalTwo = intervalTwo;
    }

    public Integer getCycles() {
        return cycles;
    }

    public void setCycles(Integer cycles) {
        this.cycles = cycles;
    }


}