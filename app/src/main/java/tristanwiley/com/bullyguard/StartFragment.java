package tristanwiley.com.bullyguard;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Tristan on 11/14/2015
 */
public class StartFragment extends Fragment {

    FloatingActionButton fabDone;
    LinearLayout layoutMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fabDone = (FloatingActionButton) getActivity().findViewById(R.id.fab_done);
        layoutMain = (LinearLayout) getActivity().findViewById(R.id.startFragment);

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animate_right = AnimationUtils.loadAnimation(getActivity(), R.anim.animate_right);
                fabDone.startAnimation(animate_right);

                animate_right.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        final OvershootInterpolator interpolator = new OvershootInterpolator();
                        ViewCompat.animate(fabDone).rotation(360f).withLayer().setDuration(800).setInterpolator(interpolator).start();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation animate_up = AnimationUtils.loadAnimation(getActivity(), R.anim.animate_up);
                        layoutMain.startAnimation(animate_up);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

    }
}
