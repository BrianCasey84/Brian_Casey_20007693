package ie.app.brian_casey_20007693;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Brian Casey 2017
 */

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash);
        //Create image view and find by ID
        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        //Create new animation and find by ID
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        //Fade out animation when finished
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);
        //Start animation
        iv.startAnimation(an);
        //Set animation Listner
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            //Notifies the start of the animation
            public void onAnimationStart(Animation animation) {

            }

            @Override
            //Notifies the end of the animation
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();
                //When animations ends start MainActivity
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}