package in.semicolonindia.gopremimumtask_2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by RANJAN SINGH on 9/24/2018.
 */

public class BaseActivity extends AppCompatActivity{

    protected FrameLayout contentFrame;
    protected Button fabMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        init();
    }

    private void init() {
        contentFrame = (FrameLayout) findViewById(R.id.contentFrame);
        RelativeLayout rlParent = (RelativeLayout) findViewById(R.id.rlParent);
        fabMsg = (Button) findViewById(R.id.fabMsg);
        fabMsg.bringToFront();
        rlParent.invalidate();
    }
}
