package application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchData {
	private final String USER_AGENT = "Mozilla/5.0";
	private String urlPrefix;
    
    public FetchData() {
    	urlPrefix = "https://api.iextrading.com/1.0/";
    }

	private String getRawData(String url) throws IOException {
		String rawData = null;
        URL object = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) object.openConnection();

        //Request header
        connection.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        rawData = in.readLine();
        in.close();
        return rawData;
    }
	
    public JSONArray getLastPriceArray() throws JSONException, IOException {  
    	return new JSONArray(getRawData(urlPrefix + "tops/last?filter=symbol,price,time"));
    }
    
    public JSONObject getExtraData(String tickers) throws JSONException, IOException {
    	return new JSONObject(getRawData(urlPrefix + "stock/market/batch?symbols=" + tickers + "&types=quote,company,stats"));
    }
    
}
