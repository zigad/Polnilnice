import org.json.JSONArray;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		int numOfOldStations = getNumOfStationsFromFile("Petrol");
		JSONArray apiData = getApiData();
		int numOfStationsOnline = getApiData().length();

		System.out.println("");

		//getNumOfStationsOnline();

		/*
		JSONObject gne = new JSONObject();
		JSONObject gneInfo = new JSONObject();
		JSONArray stationIds = new JSONArray();
		stationIds.put(1);
		stationIds.put(2);
		gneInfo.put("numberOfStationsOnline", 0);
		gneInfo.put("stationsIds", stationIds);
		gne.put("GremoNaElektriko", gneInfo);


		//Write JSON file
		try (FileWriter file = new FileWriter("currentInfoPerProvider.json")) {
			file.write(gne.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
*/
	}

	private static JSONArray getApiData() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://onecharge.eu/DuskyWebApi//noauthlocations?UserGPSaccessLatitude=46.03981500356593&UserGPSaccessLongitude=14.47101279598912&poiTypes=&searchRadius=200000&showAlsoRoaming=false"))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return new JSONArray(response.body());
	}

	private static int getNumOfStationsFromFile(String provider) {
		try (FileInputStream fis = new FileInputStream("currentInfoPerProvider.json")) {
			JsonReader reader = Json.createReader(fis);

			JsonObject obj = (JsonObject) reader.readObject();
			reader.close();
			return obj.getJsonObject(provider).getInt("numberOfStationsOnline");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
