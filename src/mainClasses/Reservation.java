package mainClasses;

import java.io.Serializable;

public class Reservation implements Serializable {
    private Integer id;
    private String name;
    private String date;
    private Integer stage; // Столик
    private String time;
    private Integer ticket;

    public Reservation(Integer id, String name, String date, Integer stage, String time, Integer ticket) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.stage = stage;
        this.time = time;
        this.ticket = ticket;
    }

    public Reservation() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", stage=" + stage +
                ", time='" + time + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
