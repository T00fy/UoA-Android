package com.example.compscipage;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CourseHandler extends DefaultHandler {
	private boolean inCourseCode;
	private boolean inSemesterField;
	private boolean inCourseDescription;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private Course course = new Course();

	public ArrayList<Course> getCourseList() {
		return courses;
	}

	@Override
	public void startDocument() throws SAXException {
		course = new Course();
	}

	@Override
	public void endDocument() throws SAXException {
		
	}

	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {

		if (localName.equals("codeField")) {
			inCourseCode = true;
		}
		if (localName.equals("semesterField")) {
			inSemesterField = true;
		}
		if (localName.equals("titleField")) {
			inCourseDescription = true;
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {

		if (localName.equals("codeField")) {
			inCourseCode = false;
		}
		if (localName.equals("semesterField")) {
			inSemesterField = false;
		}
		if (localName.equals("titleField")) {
			inCourseDescription = false;
		}	
		if(localName.equals("Course")){
			courses.add(course);
			course = new Course();
		}
	}

	@Override
	public void characters(char ch[], int start, int length) {
		String chars = new String(ch, start, length);
		chars = chars.trim();

		if (inCourseCode) {
			course.setCourseCode(chars);
		} 
		if (inSemesterField) {
			course.setSemesterInfo(chars);
		}
		if (inCourseDescription) {
			course.setDescription(chars);
		}
		
		
	}

}
