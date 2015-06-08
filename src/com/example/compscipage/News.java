package com.example.compscipage;


public class News {
	private String title;
	private String link;
	private String description = "";
	private String pubDate;
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setLink (String link){
		this.link = link;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setPubDate(String pubDate){
		this.pubDate = pubDate;
	}
	
	public String getTitle(){
		return title;
	}
	
	
	public String getLink(){
		return link;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getPubDate(){
		return pubDate;
	}
	
	public String getFormattedString(){
		return pubDate + "\n" + "\n" + description;
	}
	
	
	public String toString(){
		return title + "\n" + link + "\n" + description + "\n" + pubDate;
	}
}
