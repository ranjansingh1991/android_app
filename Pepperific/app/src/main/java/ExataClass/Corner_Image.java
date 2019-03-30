package ExataClass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Wolf Soft on 8/3/2017.
 */
public class Corner_Image extends ImageView {

    private float radius = 18.0f;
    private Path path;
    private RectF rect;

    public Corner_Image(Context context) {
        super(context);
        init();
    }

    public Corner_Image(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Corner_Image(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        path = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        path.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}