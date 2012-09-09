package andro.bar.wrappers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.view.View;

public class GifImageView extends View {

    private Movie movie;
    private long moviestart;

    public GifImageView(Context context, int drawableId){
        super(context);
        movie = Movie.decodeStream(getResources().openRawResource(drawableId));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();
        Paint p = new Paint();
        p.setAntiAlias(true);
        if (moviestart == 0) {
            moviestart = now;
        }
        int relTime;
        relTime = (int) ((now - moviestart) % movie.duration());
        movie.setTime(relTime);
        movie.draw(canvas, 0, 0);
        this.invalidate();
    }
}
