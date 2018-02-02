package fr.mrfern.pumpmycord.server;

public class BanData {

	private String author;
	private String author_UUID;
	
	private String raison;
	
	private String banType;
	
	private int year_end;
	private int month_end;
	private int day_end;
	private int hour_end;
	private int minute_end;
	
	private int day;
	private int hour;
	private int minute;
	
	private boolean end;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor_UUID() {
		return author_UUID;
	}

	public void setAuthor_UUID(String author_UUID) {
		this.author_UUID = author_UUID;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	public int getYear_end() {
		return year_end;
	}

	public void setYear_end(int year_end) {
		this.year_end = year_end;
	}

	public int getMonth_end() {
		return month_end;
	}

	public void setMonth_end(int month_end) {
		this.month_end = month_end;
	}

	public int getDay_end() {
		return day_end;
	}

	public void setDay_end(int day_end) {
		this.day_end = day_end;
	}

	public int getHour_end() {
		return hour_end;
	}

	public void setHour_end(int hour_end) {
		this.hour_end = hour_end;
	}

	public int getMinute_end() {
		return minute_end;
	}

	public void setMinute_end(int minute_end) {
		this.minute_end = minute_end;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getBanType() {
		return banType;
	}

	public void setBanType(String banType) {
		this.banType = banType;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
	
}
