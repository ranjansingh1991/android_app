package in.semicolonindia.gopremimumtask_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_One;
    private RecyclerView rv_Two;
    private RvVerticalAdapter rvVerticalAdapter;
    private RvHorizontalAdapter rvHorizontalAdapter;
    protected FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentFrame = (FrameLayout) findViewById(R.id.contentFrame);

        List<FirstModel> ebooklistItem = getVerticalData();
        rv_One = (RecyclerView) findViewById(R.id.rv_One);
        rv_One.setHasFixedSize(true);

        rv_One.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvVerticalAdapter = new RvVerticalAdapter(getApplicationContext(), ebooklistItem);
        rv_One.setAdapter(rvVerticalAdapter);
        rv_One.setNestedScrollingEnabled(false);

        List<SecondModel> secondModels = getHorizontal1Data();
        rv_Two = (RecyclerView) findViewById(R.id.rv_Two);
        rv_Two.setHasFixedSize(true);
        rv_Two.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rvHorizontalAdapter = new RvHorizontalAdapter(getApplicationContext(), secondModels);
        rv_Two.setAdapter(rvHorizontalAdapter);
    }

    private List<FirstModel> getVerticalData() {
        List<FirstModel> firstModels = new ArrayList<>();

        firstModels.add(new FirstModel(R.drawable.latestcontent_2, "letest content first in your devices"));
        firstModels.add(new FirstModel(R.drawable.unlock_premium_4, "unlock premimum ebooks"));
        firstModels.add(new FirstModel(R.drawable.printupto_3, "print upto 10* chapter"));
        firstModels.add(new FirstModel(R.drawable.annotations_1, "Annotation"));

        return firstModels;
    }

    private List<SecondModel> getHorizontal1Data() {
        List<SecondModel> ebooklist = new ArrayList<>();
        ebooklist.add(new SecondModel(R.drawable.chemical, "chemical engineering"));
        ebooklist.add(new SecondModel(R.drawable.computer_science, "computer science"));
        ebooklist.add(new SecondModel(R.drawable.electrical_engineering, "electrical engineering"));
        ebooklist.add(new SecondModel(R.drawable.machnical, "mechanical engineering"));
        ebooklist.add(new SecondModel(R.drawable.civil_engineering, "civil engineering"));
        return ebooklist;

    }
}

