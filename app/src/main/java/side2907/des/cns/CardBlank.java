package side2907.des.cns;

/**
 * Created by Demo on 21.08.2016.
 */
public class CardBlank {

    int image, id;
    String name, date, time, text;


    public CardBlank() {
    }

    public CardBlank(int image, String name, String date, String time, String text, int id) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.time = time;
        this.text = text;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
