package com.example.compscipage;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class StaffHandler extends DefaultHandler {
	private boolean inUPIField;
	private ArrayList<String> staffUPIs = new ArrayList<String>();
	
	
	public ArrayList<String> getAllStaffUPI() {
		return staffUPIs;
	}
	
	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
	}

	
	
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {

		if (localName.equals("uPIField")) {
			inUPIField = true;
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {

		if (localName.equals("uPIField")) {
			inUPIField = false;
		}
		
		if(localName.equals("Person")){
			
		}
	}

	@Override
	public void characters(char ch[], int start, int length) {
		String chars = new String(ch, start, length);
		chars = chars.trim();
		
		if (inUPIField) {
			staffUPIs.add(chars);
		} 
		
	}

}
