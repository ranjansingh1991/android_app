package in.semicolonindia.pepperificdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

import in.semicolonindia.pepperificdemo.Adapters.SpinnerCustomAdapter;
import in.semicolonindia.pepperificdemo.Adapters.FirstvpPagerAdapter;
import in.semicolonindia.pepperificdemo.Adapters.RecycleAdapter_Cusine;
import in.semicolonindia.pepperificdemo.Adapters.SecondvpPagerAdapter;
import in.semicolonindia.pepperificdemo.BeanClasses.BeanClassForCusine;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //....Spinner data.....
    String[] sNames={"C++","C","Java","Android","Php","Testing"};


    private FirstvpPagerAdapter firstvpPagerAdapter;
    private SecondvpPagerAdapter secondvpPagerAdapter;

    private ViewPager vpfirst;
    private ViewPager vpsecond;


    private ArrayList<BeanClassForCusine> beanClassForCusines;

    private RecyclerView recyclerView_cusine;
    private RecycleAdapter_Cusine mAdapter_cusine;

    Integer image1[] = {
            R.drawable.white_img,
            R.drawable.white_img,
            R.drawable.white_img,
            R.drawable.white_img,
            R.drawable.white_img
            };
    String price1[] ={
            "Rs 350",
            "Rs 200",
            "Rs 550",
            "Rs 400",
            "Rs 250"
            };


    String price2[] ={
            "Rs 150",
            "Rs 200",
            "Rs 250",
            "Rs 300",
            "Rs 350"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here Spinner to be set....
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spData);
        spin.setOnItemSelectedListener(this);

        SpinnerCustomAdapter spinnerCustomAdapter =new SpinnerCustomAdapter(getApplicationContext(),sNames);
        spin.setAdapter(spinnerCustomAdapter);


        //        Cusine Recyclerview Code

        recyclerView_cusine = (RecyclerView) findViewById(R.id.recyclerview_cusine);
        beanClassForCusines = new ArrayList<>();



        for (int i = 0; i < image1.length; i++) {
            BeanClassForCusine beanClassForCusine = new BeanClassForCusine(image1[i],price1[i],price2[i]);


            beanClassForCusines.add(beanClassForCusine);
        }


        mAdapter_cusine = new RecycleAdapter_Cusine(MainActivity.this,beanClassForCusines);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_cusine.setLayoutManager(mLayoutManager1);
        recyclerView_cusine.setItemAnimator(new DefaultItemAnimator());
        recyclerView_cusine.setAdapter(mAdapter_cusine);



        /*Banner ViewPager Code*/

        vpfirst = (ViewPager) findViewById(R.id.vpfirst);
        firstvpPagerAdapter = new FirstvpPagerAdapter(getSupportFragmentManager());
        vpfirst.setAdapter(firstvpPagerAdapter);

        vpsecond = (ViewPager) findViewById(R.id.vpsecond);
        secondvpPagerAdapter = new SecondvpPagerAdapter(getSupportFragmentManager());
        vpsecond.setAdapter(secondvpPagerAdapter);

    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(), sNames[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}


