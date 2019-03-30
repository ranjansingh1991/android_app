package Adapters;

/**
 * Created by Wolf Soft on 2/1/2017.
 */

public class ItemData {

    String text;
    Integer imageId;
    public ItemData(String text, Integer imageId){
        this.text=text;
        this.imageId=imageId;
    }

    public ItemData(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public Integer getImageId(){
        return imageId;
    }
}

