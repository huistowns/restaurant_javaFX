package mainClasses;


import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private Integer cost;
    private Long id;

    public Food() {

    }

    public Food(String name, int cost, Long id) {
        this.name = name;
        this.cost = cost;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " " + cost + " " + id;
    }

}
