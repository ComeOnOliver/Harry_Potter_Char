package com.example.harry_potter_char;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
////Reason of Using AsyncTask:
//AsyncTask enables the proper and easy use of the UI thread.
// This class allows you to perform background operations and publish results
// on the UI thread without having to manipulate threads and/or handlers.

public class fetchData extends AsyncTask<String, Bitmap, Void> {
    private String data = "";
    private String jokeParsed = "";
    private String nameParsed = "";
    private String roleParsed = "";
    private String houseParsed = "";
    private String schoolParsed = "";
    private String speciesParsed = "";
    private String bloodParsed = "";
    private HttpsURLConnection httpsURLConnection;
    private InputStream inputStream;
    private BufferedReader bufferedReader;



    @Override
    protected Void doInBackground(String... strings) {

        // this is an external call and basically the process is: open url connection, pass parameters into the connection,
        // get the input stream and then read it in the bufferedreader. Put everything in a String. Transform the string to Json Object to get the information we need.


        try {
            String id = strings[0];//Step 1
            String key = strings[1];
            URL url = new URL("https://www.potterapi.com/v1/characters/" + id + "/?key=" + key);
            //Step 2
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            inputStream = httpsURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JO = new JSONObject(data);//Step 3

            nameParsed = "" + JO.get("name");
            roleParsed = "" + JO.get("role");
            if (id == "5a1098653dc2080021cd876c") {
                houseParsed = "Unknown";
                schoolParsed = "Unknown";
            }
            else {
            houseParsed = "" + JO.get("house");
            schoolParsed = "" + JO.get("school");
            }
            speciesParsed = "" + JO.get("species");
            bloodParsed = "" + JO.get("bloodStatus");

            jokeParsed = "" + randomJoke();

        }
        //Catch the exceptions and print out the problems

        catch (JSONException | IOException e){
                e.printStackTrace();
            }
        //clean up
        finally {
            try {
            httpsURLConnection.disconnect();
            inputStream.close();
            bufferedReader.close();}
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;


    }



    private String randomJoke() {
        //function of calling another api to get some random jokes without any authendication. Follows the same process to get the string.
        //Return the string of joke
        String joke = "";

        try {
            URL url = new URL("http://api.icndb.com/jokes/random?exclude=nerdy");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();//Step 2
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            String data = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject JO = new JSONObject(data);
            joke = "" + JO.getJSONObject("value").getString("joke");
            Log.i("Main", joke);

        }
        catch (JSONException| IOException e){
            e.printStackTrace();
        }
        return joke;
    }



    @Override
    protected void onPostExecute(Void aVoid) {
//execute the task, and transfer the information from the sub-thread to the UI thread
        super.onPostExecute(aVoid);


        DisplayPerson.hpName.setText(this.nameParsed);
        DisplayPerson.hpRole.setText(this.roleParsed);
        DisplayPerson.hpHouse.setText(this.houseParsed);
        DisplayPerson.hpSchool.setText(this.schoolParsed);
        DisplayPerson.hpSpecies.setText(this.speciesParsed);
        DisplayPerson.hpBlood.setText(this.bloodParsed);
        DisplayPerson.randJoke.setText(this.jokeParsed);

    }
}
