package com.example.compscipage;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;


public class NewsHandler extends DefaultHandler {
	@SuppressWarnings("unused")
	private boolean inItem; //the main tag
	private boolean inTitle;
	private boolean inLink;
	private boolean inDescription;
	private boolean inPubDate;
	private ArrayList<News> newsList = new ArrayList<News>();
	private News newsItem;
	
	
	public ArrayList<News> getNewsList(){
		return newsList;
	}
	
	@Override
	public void startDocument() throws SAXException {
		newsItem = new News();
		newsList = new ArrayList<News>();
	}
	
	@Override
	public void endDocument() throws SAXException {
		
	}
	
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {

		if (localName.equals("item")) {
			inItem = true;
		}
		if (localName.equals("title")) {
			inTitle = true;
		}
		if (localName.equals("link")) {
			inLink = true;
		}
		if (localName.equals("description")) {
			inDescription = true;
		}
		if (localName.equals("pubDate")) {
			System.out.print("reached start of pubdate");
			inPubDate = true;
		}
	}
	
	
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {

		if (localName.equals("title")) {
			inTitle = false;
		}
		if (localName.equals("link")) {
			inLink = false;
		}	
		if(localName.equals("description")){
			System.out.print("reached end of description");
			inDescription = false;
		}
		if (localName.equals("pubDate")) {
			inPubDate = false;
		}
		if (localName.equals("item")) {
			newsList.add(newsItem);
			newsItem = new News();
			inItem = false;
		}
		
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
		String chars = new String(ch, start, length);
		chars = chars.trim();

		if (inTitle) {
			newsItem.setTitle(chars);
		} 
		if (inLink) {
			newsItem.setLink(chars);
		}
		if (inDescription) {
			Log.d("MyApp","print description: " + chars);
			if(chars.length() > newsItem.getDescription().length()){
				newsItem.setDescription(chars);
			}	
		}
		if (inPubDate) {
			newsItem.setPubDate(chars);
		}
		
		
	}

	
	
	
	
	
	
}
