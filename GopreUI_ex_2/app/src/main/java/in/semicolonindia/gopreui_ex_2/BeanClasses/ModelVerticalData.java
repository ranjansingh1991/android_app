package in.semicolonindia.gopreui_ex_2.BeanClasses;

/**
 * Created by RANJAN SINGH on 10/4/2018.
 */
@SuppressWarnings("ALL")
public class ModelVerticalData {

    Integer image;
    String title;
    String des;

    public ModelVerticalData(Integer image, String title, String des) {
        this.image = image;
        this.title = title;
        this.des = des;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}