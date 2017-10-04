package dmterkula.selfstudy.graphs.graphImpl.setup;

import dmterkula.selfstudy.graphs.graphImpl.domain.Edge;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by David Terkula on 5/27/2017.
 */
public class GoogleMapsHandler {

    public GoogleMapsHandler(){

    }

    public double getDistance(String origin, String Destination)throws IOException{
        return useJSONToFindDistance(getJsonResponse(getUrl(origin, Destination)));
    }

    public String getUrl(String origin, String destination) {
        String urlFirstPart = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=";
        String urlSecondPart = "&destinations=";
        String urlThirdPart = "&key=AIzaSyA8E2ac9CCaOK4lwp02ZIcM4_wT929eCq8";

        String url = urlFirstPart + origin + urlSecondPart + destination + urlThirdPart;
        return url;
    }

    public String getJsonResponse(String urlString) throws IOException {
        URL url;
        String jsonString = "";
        try {
            url = new URL(urlString);
            // Open the connection across the network; receive the response
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            System.out.println("Response code: " + con.getResponseCode());
            System.out.println(con.getResponseMessage());
            // the InputStream is the data passed back from the url
            // the next three lines set up a way to read the data
            InputStream ins = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);

            String inputLine;

            while ((inputLine = in.readLine()) != null) // read each line of Json file
            {
                //System.out.println(inputLine);
                jsonString += inputLine;
            }

            in.close(); // closes input stream
        }
        catch (MalformedURLException e) { e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        return jsonString;
    }

    public double useJSONToFindDistance(String jsonString) {
        /**
         A JSON object consists of named pairs. The JSON we get back from the distance
         matrix api is nested a bit so this pulls out the information in a step-by-
         step
         **/

        // first create the JsonObject so we can easily access the data
        JsonObject jsonObj = Json.createReader(new StringReader(jsonString)).readObject();
        //System.out.println(jsonObj.toString());

        // The distancematrix is stored in an array attached to the name rows
        JsonArray distancematrix = jsonObj.getJsonArray("rows"); // lame name!

        // The distance information is stored as the first item in that array
        JsonObject distanceInfo = distancematrix.getJsonObject(0);
        // distanceInfo is an object holding an array of elements objects

        // elements is an object inside of distanceInfo holding an array of distance objects
        JsonArray elements = distanceInfo.getJsonArray("elements");

        // The information we want is in the first item of the array
        JsonObject distanceAndDuration = elements.getJsonObject(0);
        // and specifically we want the distance from that item
        JsonObject distance = distanceAndDuration.getJsonObject("distance");
        // the distance is stored in readable form under the name "text"
        //System.out.println("distance " + distance.getString("text"));

        String text = distance.getString("text");
        if(text.contains(" ")){
            text = text.substring(0, text.indexOf(" "));
        }

        if(text.contains(",")){
            text = text.replace(",", "");
        }

        double distanceVal = Double.parseDouble(text);
        return distanceVal;
    }

}
