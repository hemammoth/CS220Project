package application;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Creator {
	private static FetchData server = new FetchData();

	public void create(HashMap stockMap, GeneralTree stockTree) throws JSONException, IOException {
		createMapAndTree(stockMap, stockTree);
	}

	public void create(HashMap stockMap, GeneralTree stockTree, String s) throws JSONException, IOException {
		updateStockMap(stockMap, stockTree);
	}

	private void createMapAndTree(HashMap stockMap, GeneralTree stockTree) throws JSONException, IOException { 
		JSONArray lastPriceArray = server.getLastPriceArray();
		for (int i = 0; i<lastPriceArray.length()/100+1; i++) {
			String tickers = "";
			int values = 0;
			for (int index=100*i; values<100 && index<lastPriceArray.length();index++) {
				JSONObject symbolDictionaries = new JSONObject(lastPriceArray.get(index).toString());

				// Stock's ticker symbol
				String symbol =  (String) symbolDictionaries.get("symbol");
				if (!symbol.contains("+") && !symbol.contains("-") &&!symbol.contains("=") && 
						!symbol.contains(".") && !symbol.contains("#") && !symbol.contains("*") && 
						!symbolDictionaries.get("price").toString().equals("0") && !symbol.contains("^")) {
					tickers += symbol + ",";
					values++;
				}
			}

			JSONObject extraInfoObject = server.getExtraData(tickers);
			for (int ndx = 0; ndx<extraInfoObject.names().length(); ndx++) {
				String symbol = (String) extraInfoObject.names().get(ndx);
				JSONObject symbolData = (JSONObject) extraInfoObject.get(symbol);

				JSONObject quote = (JSONObject) symbolData.get("quote");
				JSONObject company = (JSONObject) symbolData.get("company");
				JSONObject stats = (JSONObject) symbolData.get("stats");

				String day5ChangePercent = null;
				try {
					day5ChangePercent = stats.get("day5ChangePercent").toString();
				} catch (Exception e) {
					day5ChangePercent = null;
				}
				
				String month1ChangePercent = null;
				try {
					month1ChangePercent = stats.get("month1ChangePercent").toString();
				} catch (Exception e) {
					month1ChangePercent = null;
				}
				
				String month3ChangePercent = null;
				try {
					month3ChangePercent = stats.get("month3ChangePercent").toString();
				} catch (Exception e) {
					month3ChangePercent = null;
				}
				
				String month6ChangePercent = null;
				try {
					month6ChangePercent = stats.get("month6ChangePercent").toString();
				} catch (Exception e) {
					month6ChangePercent = null;
				}
				
				String year1ChangePercent = null;
				try {
					year1ChangePercent = stats.get("year1ChangePercent").toString();
				} catch (Exception e) {
					year1ChangePercent = null;
				}
				
				String year2ChangePercent = null;
				try {
					year2ChangePercent = stats.get("year2ChangePercent").toString();
				} catch (Exception e) {
					year2ChangePercent = null;
				}
				
				String year5ChangePercent = null;
				try {
					year5ChangePercent = stats.get("year5ChangePercent").toString();
				} catch (Exception e) {
					year5ChangePercent = null;
				}
				
				String sharesOutstanding = null;
				try {
					sharesOutstanding = stats.get("sharesOutstanding").toString();
				} catch (Exception e) {
					sharesOutstanding = null;
				}
				
				String sharesFloat = null;
				try {
					sharesFloat = stats.get("float").toString();
				} catch (Exception e) {
					sharesFloat = null;
				}
				
				String avgTotalVolume = null;
				try {
					avgTotalVolume = quote.get("avgTotalVolume").toString();
				} catch (Exception e) {
					avgTotalVolume = null;
				}
				
				String returnOnEquity = null;
				try {
					returnOnEquity = stats.get("returnOnEquity").toString();
				} catch (Exception e) {
					returnOnEquity = null;
				}
				
				String day200MovingAvg = null;
				try {
					day200MovingAvg = stats.get("day200MovingAvg").toString();
				} catch (Exception e) {
					day200MovingAvg = null;
				}
				
				String day50MovingAvg = null;
				try {
					day50MovingAvg = stats.get("day50MovingAvg").toString();
				} catch (Exception e) {
					day50MovingAvg = null;
				}
				
				String companyName = null;
				try {
					companyName = company.get("companyName").toString();
				} catch (Exception e) {
					companyName = null;
					
				}String exchange = null;
				try {
					exchange = company.get("exchange").toString();
				} catch (Exception e) {
					exchange = null;
				}
				
				String sector = null;
				try {
					sector = company.get("sector").toString();
				} catch (Exception e) {
					sector = null;
				}
				
				String industry = null;
				try {
					industry = company.get("industry").toString();
				} catch (Exception e) {
					industry = null;
					
				}String website = null;
				try {
					website = company.get("website").toString();
				} catch (Exception e) {
					website = null;
				}
				
				String description = null;
				try {
					description = company.get("description").toString();
				} catch (Exception e) {
					description = null;
				}
				
				String CEO = null;
				try {
					CEO = company.get("CEO").toString();
				} catch (Exception e) {
					CEO = null;
				}
				
				String open = null;
				try {
					open = quote.get("open").toString();
				} catch (Exception e) {
					open = null;
				}
				
				String close = null;
				try {
					close = quote.get("close").toString();
				} catch (Exception e) {
					close = null;
				}
				
				String week52High = null;
				try {
					week52High = quote.get("week52High").toString();
				} catch (Exception e) {
					week52High = null;
				}
				
				String week52Low = null;
				try {
					week52Low = quote.get("week52Low").toString();
				} catch (Exception e) {
					week52Low = null;
				}
				
				String high = null;
				try {
					high = quote.get("high").toString();
				} catch (Exception e) {
					high = null;
				}
				
				String low = null;
				try {
					low = quote.get("low").toString();
				} catch (Exception e) {
					low = null;
				}
				
				String dividendRate = null;
				try {
					dividendRate = stats.get("dividendRate").toString();
				} catch (Exception e) {
					dividendRate = null;
				}

				if (Integer.parseInt(avgTotalVolume) < 100000) {
					
				} else {
					StockInfo newStock = new StockInfo(
							sharesOutstanding, sharesFloat, avgTotalVolume, returnOnEquity,
							day200MovingAvg, day50MovingAvg, year5ChangePercent, year2ChangePercent, 
							year1ChangePercent, month6ChangePercent, month3ChangePercent, 
							month1ChangePercent, day5ChangePercent, companyName, exchange, sector, 
							industry, website, description, CEO, open, close, week52High, 
							week52Low, high, low, dividendRate);
					stockMap.add(symbol, newStock);
					stockTree.insert(symbol);
				}
			}
		}
	}

	private void updateStockMap(HashMap stockMap, GeneralTree stockTree) throws JSONException, IOException { 
		// URLs from iextraiding.com API used to fetch data
		JSONArray lastPriceArray = server.getLastPriceArray();
		for (int index = 0; index < lastPriceArray.length(); index++) {
			JSONObject symbolDictionaries = new JSONObject(lastPriceArray.get(index).toString());

			// Stock's ticker symbol
			String symbol =  (String) symbolDictionaries.get("symbol");
			if (!symbol.contains("+") && !symbol.contains("-") &&!symbol.contains("=") && 
					!symbol.contains(".") && !symbol.contains("#") && !symbol.contains("*") && 
					!symbolDictionaries.get("price").toString().equals("0") && !symbol.contains("^")) {

				try {
					// Stock's last price
					String price =  symbolDictionaries.get("price").toString();
					// Unix time when the data was received
					long time = (long) symbolDictionaries.get("time"); 

					stockMap.get(symbol).setTime(time);
					stockMap.get(symbol).setLastPrice(price);
					stockMap.get(symbol).setHigh(price);
					stockMap.get(symbol).setLow(price);
					stockMap.get(symbol).setWeek52High(price);
					stockMap.get(symbol).setWeek52Low(price);
					stockMap.get(symbol).setChange(price);
					stockMap.get(symbol).setPercentChange(price);
					if (stockMap.get(symbol).getDividendRate() != null) {
						stockMap.get(symbol).setDividendYield(price);
					} 
				} catch (NullPointerException e) {
					stockMap.remove(symbol);
					stockTree.remove(symbol);
				} catch (NumberFormatException e) {
					stockMap.remove(symbol);
					stockTree.remove(symbol);
				} catch (IndexOutOfBoundsException e) {
					stockMap.remove(symbol);
					stockTree.remove(symbol);
				}
			}
		}
	}
	
	public void createDefultWatchlist(CircularDoublyLinkedList watchlistList) {
		watchlistList.add("Default Watchlist");
		watchlistList.getCurrentNode().watchlist.push("AAPL");
		watchlistList.getCurrentNode().watchlist.push("TSLA");
		watchlistList.getCurrentNode().watchlist.push("AMZN");
		watchlistList.getCurrentNode().watchlist.push("GOOG");
		watchlistList.getCurrentNode().watchlist.push("FB");
		watchlistList.getCurrentNode().watchlist.push("MSFT");
		watchlistList.getCurrentNode().watchlist.push("AMD");
		watchlistList.getCurrentNode().watchlist.push("GE");
	}

	public ArrayList<String> mergeSort(HashMap map, GeneralTree tree, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<String> arr = tree.getTickerList("");
		mergeSort(map, arr, 0, arr.size()-1, method); 
        
		return arr;
		/*
		for (int ndx = 0; ndx < arr.size(); ndx++) {
			System.out.println(map.get(arr.get(ndx)).getLastPrice());
		}
		*/

	}

	public void mergeSort(HashMap map, ArrayList<String> arr, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		mergeSort(map, arr, 0, arr.size()-1, method); 
	}

	private void mergeSort(HashMap map, ArrayList<String> arr, int first, int last, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (first < last) {
			int mid = (first + last) / 2;
			mergeSort(map, arr, first, mid, method);
			mergeSort(map, arr, mid+1, last, method);
			merge(map, arr, first, mid, last, method);
		}
	}

	private void merge(HashMap map, ArrayList<String> arr, int first, int mid, int last, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int length1 = mid - first + 1; 
		int length2 = last - mid; 

		String[] L = new String [length1]; 
		String[] R = new String [length2]; 

		for (int i=0; i<length1; ++i) {
			L[i] = arr.get(first + i); 
		}
		for (int j=0; j<length2; ++j) {
			R[j] = arr.get(mid + 1 + j);
		}

		int i = 0, j = 0; 

		int k = first; 
		while (i < length1 && j < length2) { 
			if (Double.parseDouble((String) method.invoke(map.get(L[i]))) <= Double.parseDouble((String) method.invoke(map.get(R[j])))) { 
				arr.set(k, L[i]); 
				i++; 
			} else { 
				arr.set(k, R[j]); 
				j++; 
			} 
			k++; 
		} 

		while (i < length1) { 
			arr.set(k, L[i]); 
			i++; 
			k++; 
		} 

		while (j < length2) { 
			arr.set(k, R[j]);  
			j++; 
			k++; 
		} 
	}

	public int binarySearch(int[] arr, int first, int last, int num) {
		if (last >= first) {
			int mid = first+(last-first)/2;

			if (arr[mid] == num) {
				return mid;
			} else if (arr[mid] < num) {
				return binarySearch(arr, mid+1, last, num);
			} else {
				return binarySearch(arr, first, mid-1, num);
			}

		}

		return -1;
	}
}
