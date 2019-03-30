package in.semicolonindia.recomendationrv;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    // 1. Vertical data..........
    private RecyclerView rvVerticalList;
    private RvAdapterVerticalList rvAdapterVerticalList;
    private ArrayList<ModelVerticalData> modelVerticalList;
    Integer image[] = {
            R.drawable.white_img,
            R.drawable.white_img,
            R.drawable.white_img,
            R.drawable.white_img,
    };
    String title[] = {
            "Print notes + highlight",
            "Annotations",
            "Unlock premium ebooks",
            "Premium Updated Content",
    };


    String des[] = {"Print your notes & Highlights with goPremium.\n" + "You can now print upto 100 notes anytime you want.",
            "Use annotation feature to Highlight, Underline & Strike-through important points and definitions. You can browse your annotations anytime using the explorer. ",
            "With goPremium get upto 15 premium ebooks that can take  your learning curve to its peak.\n" + "goPremium comes with its own perks,",
            "Going Premium as its own perks Unlock regular content updates & receive massive discounts on our product range.\n" + "Your Search for materials ends here.",
    };

    // 2. Horizontal data..........
    private RecyclerView rvHorizontalList;
    private RvAdapterHorizontalList rvAdapterHorizontalList;
    private ArrayList<ModelHorizontalData> modelHorizontalList;

    Integer image1[] = {
            R.drawable.white_img,
            R.drawable.white_img,
            R.drawable.white_img,
            R.drawable.white_img,
            R.drawable.white_img
    };
    String price1[] = {
            "Rs 350",
            "Rs 200",
            "Rs 550",
            "Rs 400",
            "Rs 250"
    };


    String price2[] = {
            "Rs 150",
            "Rs 200",
            "Rs 250",
            "Rs 300",
            "Rs 350"
    };

    // 1. Buttom data..........
    private RecyclerView rvButtomList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Make to run your application only in portrait mode
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Make to run your application only in LANDSCAPE mode
        setContentView(R.layout.activity_main);

        //.....Here we take all images and price and set RvAdapterHorizontalList.....

        rvHorizontalList = (RecyclerView) findViewById(R.id.rvHorizontalList);
        rvHorizontalList.setHasFixedSize(true);
        rvHorizontalList.setNestedScrollingEnabled(false);
        modelHorizontalList = new ArrayList<>();

        for (int i = 0; i < image1.length; i++) {
            ModelHorizontalData modelHorizontalData = new ModelHorizontalData(image1[i], price1[i], price2[i]);

            this.modelHorizontalList.add(modelHorizontalData);
        }

        rvAdapterHorizontalList = new RvAdapterHorizontalList(MainActivity.this, modelHorizontalList);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvHorizontalList.setLayoutManager(mLayoutManager1);
        rvHorizontalList.setItemAnimator(new DefaultItemAnimator());
        rvHorizontalList.setAdapter(rvAdapterHorizontalList);

        //.....Here we take all images and price and set RvAdapterVerticalList.....

        rvVerticalList = (RecyclerView) findViewById(R.id.rvVerticalList);
        rvVerticalList.setHasFixedSize(true);
        rvVerticalList.setNestedScrollingEnabled(false);
        modelVerticalList = new ArrayList<>();

        for (int i = 0; i < image.length; i++) {
            ModelVerticalData modelVerticalData = new ModelVerticalData(image[i], title[i], des[i]);


            this.modelVerticalList.add(modelVerticalData);
        }

        rvAdapterVerticalList = new RvAdapterVerticalList(MainActivity.this, modelVerticalList);
        rvVerticalList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvVerticalList.setAdapter(rvAdapterVerticalList);



        //.....Here we take all images and price and set RvAdapterButtomList.....

        rvButtomList = (RecyclerView) findViewById(R.id.rvButtomList);
        rvButtomList.setHasFixedSize(true);
        rvButtomList.setNestedScrollingEnabled(false);
        modelVerticalList = new ArrayList<>();

        for (int i = 0; i < image.length; i++) {
            ModelVerticalData modelVerticalData = new ModelVerticalData(image[i], title[i], des[i]);


            this.modelVerticalList.add(modelVerticalData);
        }

        rvAdapterVerticalList = new RvAdapterVerticalList(MainActivity.this, modelVerticalList);
        rvButtomList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvButtomList.setAdapter(rvAdapterVerticalList);


    }

}
