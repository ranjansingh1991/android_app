package in.semicolonindia.gopreui_ex_2.BeanClasses;

/**
 * Created by RANJAN SINGH on 9/25/2018.
 */
@SuppressWarnings("ALL")
public class ModelHorizontalData {
    Integer image;
    String priceOne;
    String priceTwo;

    public ModelHorizontalData(Integer image, String priceOne, String priceTwo) {
        this.image = image;
        this.priceOne = priceOne;
        this.priceTwo = priceTwo;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getPriceOne() {
        return priceOne;
    }

    public void setPriceOne(String priceOne) {
        this.priceOne = priceOne;
    }

    public String getPriceTwo() {
        return priceTwo;
    }

    public void setPriceTwo(String priceTwo) {
        this.priceTwo = priceTwo;
    }
}