package Adapters;

/**
 * Created by Wolf Soft on 2/1/2017.
 */

public class ItemData_Cusine {

    String text;

    public ItemData_Cusine(String text, Integer imageId){
        this.text=text;

    }

    public ItemData_Cusine(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }


}

