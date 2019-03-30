package in.semicolonindia.kopy_kitabtask.model;

/**
 * Created by RANJAN SINGH on 9/18/2018.
 */
@SuppressWarnings("ALL")
public class EbookModel {

    private int ebookImage;
    private String ebookName;

    public EbookModel(int ebookImage, String ebookName) {
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