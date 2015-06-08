package com.example.compscipage;

public class Course {
	private String courseCode;
	private String semesterInfo;
	private String description;
	
	public void setCourseCode(String code) {
		courseCode = code;
		
	}

	public void setSemesterInfo(String semesterInfo) {
		this.semesterInfo = semesterInfo;
		
	}

	public void setDescription(String description) {
		this.description = description;
		
	}
	
	public String getCourseCode(){
		return courseCode;
	}
	
	public String getSemesterInfo(){
		return semesterInfo;
	}
	
	public String description(){
		return description;
	}
	
	public String toString(){
		String s = ("\n" + courseCode + ": " + description +  "\n" + semesterInfo + "\n");
		return s;
	}

}
