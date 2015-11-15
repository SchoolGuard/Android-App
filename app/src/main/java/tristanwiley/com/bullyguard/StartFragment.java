package tristanwiley.com.bullyguard;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Tristan on 11/14/2015
 */
public class StartFragment extends Fragment {

    FloatingActionButton fabDone;
    LinearLayout layoutMain;
    EditText schoolEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fabDone = (FloatingActionButton) getActivity().findViewById(R.id.fab_done);
        layoutMain = (LinearLayout) getActivity().findViewById(R.id.startFragment);
        schoolEditText = (EditText) getActivity().findViewById(R.id.editText);

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation animate_right = AnimationUtils.loadAnimation(getActivity(), R.anim.animate_right);
                fabDone.startAnimation(animate_right);

                animate_right.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation animate_up = AnimationUtils.loadAnimation(getActivity(), R.anim.animate_up);
                        layoutMain.startAnimation(animate_up);

                        animate_up.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
//                                Ion.with(getActivity())
//                                        .load("http://tristanwiley.com/schoolguard/buildings.json")
//                                        .asJsonObject()
//                                        .setCallback(new FutureCallback<JsonObject>() {
//                                            @Override
//                                            public void onCompleted(Exception e, JsonObject result) {
//                                                JsonArray schools = result.getAsJsonArray("schools");
//                                                String schoolName = "";
//                                                ArrayList<String> sBuildings = new ArrayList<>();
//                                                JsonArray buildings = new JsonArray();
//                                                for(int i=0; i < schools.size(); i++){
//                                                    String schoolCode = schools.get(1).getAsString();
//                                                    if(schoolCode == schoolEditText.getText().toString()){
//                                                        schoolName = schools.get(0).getAsString();
//                                                        buildings = schools.get(2).getAsJsonArray();
//                                                    }else{
//                                                        Snackbar.make(getView(), "We couldn't find your school, try again!", Snackbar.LENGTH_SHORT).show();
//                                                    }
//
//                                                }
//
//                                                if(buildings != null){
//                                                    for(int i=0; i < buildings.size(); i++) {
//                                                        sBuildings.add(buildings.get(i).getAsString());
//                                                    }
//                                                }else{
//                                                    Snackbar.make(getView(), "Buildings was null", Snackbar.LENGTH_SHORT).show();
//                                                }
//
//
//                                                Bundle bundle = new Bundle();
//                                                bundle.putString("school_name", schoolName);
//                                                bundle.putStringArrayList("school_buildings", sBuildings);
//
//                                                IncidentFragment incidentFragment = new IncidentFragment();
//                                                incidentFragment.setArguments(bundle);
//
//                                                getActivity().getSupportFragmentManager()
//                                                        .beginTransaction()
//                                                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
//                                                        .replace(R.id.container, incidentFragment)
//                                                        .commit();
//                                            }
//                                        });
                                ArrayList<String> sBuildings = new ArrayList<>();
                                sBuildings.add("High School");
                                sBuildings.add("Middle School");
                                sBuildings.add("Other Building");

                                Bundle bundle = new Bundle();
                                bundle.putString("school_name", "Marcellus High School");
                                bundle.putStringArrayList("school_buildings", sBuildings);

                                IncidentFragment incidentFragment = new IncidentFragment();
                                incidentFragment.setArguments(bundle);

                                getActivity().getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                        .replace(R.id.container, incidentFragment)
                                        .commit();

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

    }
}
