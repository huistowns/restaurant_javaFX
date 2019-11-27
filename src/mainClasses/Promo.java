package mainClasses;

import java.io.Serializable;

public class Promo implements Serializable {
    private String name;
    private Integer percent;

    public Promo(String name, Integer percent) {
        this.name = name;
        this.percent = percent;
    }

    public Promo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Promo{" +
                "name='" + name + '\'' +
                ", percent=" + percent +
                '}';
    }
}
