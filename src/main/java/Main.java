import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {


	public static void main(String[] args) throws IOException, InterruptedException {

		JSONObject gne = new JSONObject();
		JSONObject gneInfo = new JSONObject();
		JSONArray stationIds = new JSONArray();
		stationIds.put(1);
		stationIds.put(2);
		gneInfo.put("numberOfStationsOnline", 0);
		gneInfo.put("stationsIds", stationIds);
		gne.put("GremoNaElektriko", gneInfo);
		//Write JSON file
		try (FileWriter file = new FileWriter("CurrentFiles.json")) {
			file.write(gne.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}


/*
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://onecharge.eu/DuskyWebApi//noauthlocations?UserGPSaccessLatitude=46.03981500356593&UserGPSaccessLongitude=14.47101279598912&poiTypes=&searchRadius=200000&showAlsoRoaming=false"))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());



		//JSONObject jsonObject = jsonArray.getJSONObject(0);
		//System.out.println(jsonArray);

		*/
	}

}
