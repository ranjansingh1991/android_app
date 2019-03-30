package in.semicolonindia.gopremimumtask_2;

/**
 * Created by RANJAN SINGH on 9/18/2018.
 */
@SuppressWarnings("ALL")
public class FirstModel {

    private int ebookImage;
    private String ebookName;

    public FirstModel(int ebookImage, String ebookName) {
        this.ebookImage = ebookImage;
        this.ebookName = ebookName;
    }

    public int getEbookImage() {
        return ebookImage;
    }

    public String getEbookName() {
        return ebookName;
    }
}