package side2907.des.cns;

/**
 * Created by Demo on 21.08.2016.
 */
public class CardBlank {

    int image, id;
    String name, time;

    public CardBlank(){}

    public CardBlank(int image, String name, String time, int id) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.id=id;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
