package com.example.compscipage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.graphics.Bitmap;

public class VCard {
	/**
	 * 
	 */
	private String name;
	private String office;
	private String email;
	private String pic;
	private String number;

	public VCard(String name, String office, String email, String pic, String number) {
		this.name = name;
		this.office = office;
		this.email = email;
		this.pic = pic;
		this.number = number;
		
	}

	public void setName(String name) {
		this.name = name;
		
	}
	
	public void setNumber(String number){
		this.number = number;
	}

	public void setOffice(String office) {
		this.office = office;
		
	}

	public void setEmail(String email) {
		this.email = email;
		
	}

	public void setPic(String pic) {
		this.pic = pic;
		
	}
	
	public String getName(){
		return name;
	}
	
	
	public String getOffice(){
		return office;
	}
	
	
	public String getEmail(){
		return email;
	}
	
	public String getNumber(){
		return number;
	}
	

	public String getPicEncoding(){
		return pic;
	}
	
	public Bitmap getPicture(Activity activity){
			InputStream stream = new ByteArrayInputStream(Base64.decode(pic, Base64.DEFAULT)); //possibly converting bytes into bytes
			Bitmap bitmap = BitmapFactory.decodeStream(stream);
			if(pic.length() > 1){
				bitmap = Bitmap.createScaledBitmap(bitmap, 199, 300, false);
				return bitmap;	
			}
			Bitmap defaultPic = BitmapFactory.decodeResource(activity.getResources(), R.drawable.asd);
			defaultPic = Bitmap.createScaledBitmap(defaultPic, 199, 300, false);
		return defaultPic;
			
	}
	
	public String getDetailSummary(){
		return name + "\n" + "\n" + email + "\n" + getClickableNumber() + "\n" + office;
	}

	private String getClickableNumber() {
		String clickableNumber = number;
		if(clickableNumber.contains("=20")){
			clickableNumber = clickableNumber.replace("=20", " extension not available");
		}//+64 9 3737599, 86831
		clickableNumber = clickableNumber.replace("(", "");
		clickableNumber = clickableNumber.replace(")", "");
		clickableNumber = clickableNumber.replace("-", "");
		clickableNumber = clickableNumber.replace(" x", ",");
		return clickableNumber;
	}

}
