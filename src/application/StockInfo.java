package application;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class StockInfo {
	// Long term data
	private String sharesOutstanding = "N/A";
	private String sharesFloat = "N/A";
	private String averageVolume = "N/A";
	private String dividendRate = "N/A";
	private String returnOnEquity = "N/A";
	private String day200MovingAvg = "N/A";
	private String day50MovingAvg = "N/A";
	private String year5ChangePercent = "N/A";
	private String year2ChangePercent = "N/A";
	private String year1ChangePercent = "N/A";
	private String month6ChangePercent = "N/A";
	private String month3ChangePercent = "N/A";
	private String month1ChangePercent = "N/A";
	private String day5ChangePercent = "N/A";
	private String companyName = "N/A";
	private String exchange = "N/A";
	private String sector = "N/A";
	private String industry = "N/A";
	private String website = "N/A";
	private String discription = "N/A";
	private String CEO = "N/A";
	private String open = "N/A";
	private String close = "N/A";

	// Updated data
	private String time  = "N/A";
	private String lastPrice = "N/A";
	private String high = "N/A";
	private String low = "N/A";
	private String week52High = "N/A";
	private String week52Low = "N/A";
	private String change = "N/A";
	private String percentChange = "N/A";
	private String dividendYield = "N/A";
	
	private DecimalFormat df = new DecimalFormat("#.00");

	public StockInfo(String sharesOutstanding, String sharesFloat, String averageVolume, String returnOnEquity, 
			String day200MovingAvg, String day50MovingAvg, String year5ChangePercent, String year2ChangePercent, 
			String year1ChangePercent, String month6ChangePercent, String month3ChangePercent, String month1ChangePercent, 
			String day5ChangePercent, String companyName, String exchange, String sector, String industry, 
			String website, String discription, String CEO, String open, String close, String week52High, 
			String week52Low, String high, String low, String dividendRate) {
		this.sharesOutstanding = sharesOutstanding;
		this.sharesFloat = sharesFloat;
		this.averageVolume = averageVolume;
		this.open = open;
		this.close = close;
		this.returnOnEquity = returnOnEquity;
		this.day200MovingAvg = day200MovingAvg;
		this.day50MovingAvg = day50MovingAvg;
		this.year5ChangePercent = year5ChangePercent;
		this.year2ChangePercent = year2ChangePercent;
		this.year1ChangePercent = year1ChangePercent;
		this.month6ChangePercent = month6ChangePercent;
		this.month3ChangePercent = month3ChangePercent;
		this.month1ChangePercent = month1ChangePercent;
		this.day5ChangePercent = day5ChangePercent;
		this.companyName = companyName;
		this.exchange = exchange;
		this.sector = sector;
		this.industry = industry;
		this.website = website;
		this.discription = discription;
		this.CEO = CEO;
		this.high = "0";
		this.low = low;
		this.week52High = week52High;
		this.week52Low = week52Low;
		this.dividendRate = dividendRate;
		time = null;
		lastPrice = "0";
		change = null;
		percentChange = null;
		dividendYield = null;
	}

	public String getSharesOutstanding() {
		return sharesOutstanding;
	}

	public String getSharesFloat() {
		return sharesFloat;
	}

	public String getAverageVolume() {
		return averageVolume;
	}

	public String getOpen() {
		return open;
	}

	public String getClose() {
		return close;
	}

	public String getDividendRate() {
		return dividendRate;
	}

	public String getReturnOnEquity() {
		return returnOnEquity;
	}

	public String getDay200MovingAvg() {
		return day200MovingAvg;
	}

	public String getDay50MovingAvg() {
		return day50MovingAvg;
	}

	public String getYear5ChangePercent() {
		return df.format(Double.parseDouble(year5ChangePercent));
	}

	public String getYear2ChangePercent() {
		return df.format(Double.parseDouble(year2ChangePercent));
	}

	public String getYear1ChangePercent() {
		return df.format(Double.parseDouble(year1ChangePercent));
	}

	public String getMonth6ChangePercent() {
		return df.format(Double.parseDouble(month6ChangePercent));
	}

	public String getMonth3ChangePercent() {
		return df.format(Double.parseDouble(month3ChangePercent));
	}

	public String getMonth1ChangePercent() {
		return df.format(Double.parseDouble(month1ChangePercent));
	}

	public String getDay5ChangePercent() {
		return df.format(Double.parseDouble(day5ChangePercent));
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getExchange() {
		return exchange;
	}

	public String getSector() {
		return sector;
	}

	public String getIndustry() {
		return industry;
	}

	public String getWebsite() {
		return website;
	}

	public String getDiscription() {
		return discription;
	}

	public String getCEO() {
		return CEO;
	}

	public String getTime() {
		return time;
	}

	public void setTime(long unixTime) {
		//convert seconds to milliseconds
		Date date = new Date(unixTime); 
		// format of the date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-6"));
		time = dateFormat.format(date);
	}

	public String getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String lastPrice) {
		if (Double.parseDouble(high) < Double.parseDouble(lastPrice)) {
			high = lastPrice;
		}
	}

	public String getLow() {
		return low;
	}

	public void setLow(String lastPrice) {
		if (Double.parseDouble(low) > Double.parseDouble(lastPrice)) {
			low = lastPrice;
		}
	}

	public String getWeek52High() {
		return week52High;
	}

	public void setWeek52High(String lastPrice) {
		if (Double.parseDouble(week52High) < Double.parseDouble(lastPrice)) {
			week52High = lastPrice;
		}
	}

	public String getWeek52Low() {
		return week52Low;
	}

	public void setWeek52Low(String lastPrice) {
		if (Double.parseDouble(week52Low) > Double.parseDouble(lastPrice)) {
			week52Low = lastPrice;
		}
	}

	public String getChange() {
		return change;
	}

	public void setChange(String lastPrice) {
		change = Double.toString(Double.parseDouble(lastPrice) - 
				Double.parseDouble(open));
		change = df.format(Double.parseDouble(change));
	}

	public String getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(String lastPrice) {
		percentChange = Double.toString(((Double.parseDouble(lastPrice) - 
				Double.parseDouble(open))/ 
				Double.parseDouble(open))*100);
		percentChange = df.format(Double.parseDouble(percentChange));			
	}

	public String getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(String lastPrice) {
		dividendYield = Double.toString(Double.parseDouble(lastPrice) / 
				Double.parseDouble(dividendRate));
	}

}
