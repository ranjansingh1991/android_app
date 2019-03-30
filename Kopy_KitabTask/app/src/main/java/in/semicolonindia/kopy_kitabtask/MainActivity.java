package in.semicolonindia.kopy_kitabtask;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import in.semicolonindia.kopy_kitabtask.adapter.RvEbookAdapter;
import in.semicolonindia.kopy_kitabtask.adapter.RvFeatureAdapter;
import in.semicolonindia.kopy_kitabtask.model.EbookModel;
import in.semicolonindia.kopy_kitabtask.model.FeatureModel;
@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_EbookList;
    private RecyclerView rv_FeatureList;
    private RvEbookAdapter rvEbookAdapter;
    private RvFeatureAdapter rvFeatureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Make to run your application only in portrait mode
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Make to run your application only in LANDSCAPE mode
        setContentView(R.layout.activity_main);

        List<EbookModel> ebooklistItem = getEbookImage();
        rv_EbookList = (RecyclerView) findViewById(R.id.rv_EbookList);
        rv_EbookList.setHasFixedSize(true);
        rv_EbookList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rvEbookAdapter = new RvEbookAdapter(getApplicationContext(), ebooklistItem);
        rv_EbookList.setAdapter(rvEbookAdapter);

        List<FeatureModel> featurelistItem = getFeatureImage();
        rv_FeatureList = (RecyclerView) findViewById(R.id.rv_FeatureList);
        rv_FeatureList.setHasFixedSize(true);
        rv_FeatureList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        rvFeatureAdapter = new RvFeatureAdapter(getApplicationContext(), featurelistItem);
        rv_FeatureList.setAdapter(rvFeatureAdapter);
    }


    private List<EbookModel> getEbookImage() {
        List<EbookModel> ebooklist = new ArrayList<>();
        ebooklist.add(new EbookModel(R.drawable.chemical , "chemical engineering"));
        ebooklist.add(new EbookModel(R.drawable.computer_science, "computer science"));
        ebooklist.add(new EbookModel(R.drawable.electrical_engineering, "electrical engineering"));
        ebooklist.add(new EbookModel(R.drawable.machnical, "mechanical engineering"));
        ebooklist.add(new EbookModel(R.drawable.civil_engineering, "civil engineering"));
        return ebooklist;
    }

    private List<FeatureModel> getFeatureImage() {
        List<FeatureModel> featurelist = new ArrayList<>();

        featurelist.add(new FeatureModel(R.drawable.latestcontent_2, "letest content \n first in your devices"));
        featurelist.add(new FeatureModel(R.drawable.unlock_premium_4, "unlock premimum"));
        featurelist.add(new FeatureModel(R.drawable.printupto_3, "print upto \n 10* chapter"));
        featurelist.add(new FeatureModel(R.drawable.annotations_1, "Annotation"));

        return featurelist;
    }

}
