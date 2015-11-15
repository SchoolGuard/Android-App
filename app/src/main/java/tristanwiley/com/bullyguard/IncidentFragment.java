package tristanwiley.com.bullyguard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.koushikdutta.async.http.AsyncHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Tristan on 11/14/2015.
 */
public class IncidentFragment extends Fragment {

    Spinner buildingSpinner;
    EditText locationText;
    EditText message;
    String schoolName;
    ArrayList<String> schoolBuildings;
    Button reportButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        schoolName = b.getString("school_name");
        schoolBuildings = b.getStringArrayList("school_buildings");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.incident_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buildingSpinner = (Spinner) getActivity().findViewById(R.id.building_spinner);
        reportButton = (Button) getActivity().findViewById(R.id.report_button);
        locationText = (EditText) getActivity().findViewById(R.id.location_et);
        message = (EditText) getActivity().findViewById(R.id.incident_et);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, schoolBuildings);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingSpinner.setAdapter(dataAdapter);

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String url = "http://www.tristanwiley.com/schoolguard/incident.php?building=" + buildingSpinner.getSelectedItem().toString().replace(" ", "") + "&location=" + locationText.getText().toString().replace(" ", "") + "&message=" + message.getText().toString().replace(" ", "");

                    String encodedUrl = URLEncoder.encode(url, "UTF-8");
                    new RequestTask().execute(url);
                }catch(Exception e){

                }
            }
        });
    }

    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Snackbar.make(getView(), "Your report was sent to " + schoolName, Snackbar.LENGTH_LONG).show();
        }
    }

}