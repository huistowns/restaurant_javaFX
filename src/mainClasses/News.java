package mainClasses;

import java.io.Serializable;

public class News implements Serializable {
    private String heading;
    private String description;
    private String date;
    private String time;
    private Long idNews;


    public News(String heading, String description, String date, String time, Long idNews) {
        this.heading = heading;
        this.description = description;
        this.date = date;
        this.time = time;
        this.idNews = idNews;
    }

    public News() {

    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getIdNews() {
        return idNews;
    }

    public void setIdNews(Long idNews) {
        this.idNews = idNews;
    }

    @Override
    public String toString() {
        return "News{" +
                "heading='" + heading + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", idNews=" + idNews +
                '}';
    }
}
