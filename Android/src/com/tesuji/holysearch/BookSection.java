package com.tesuji.holysearch;

import java.io.InputStream;

import android.content.Context;

public class BookSection {

	private String name;
	private Book book;
	private Context context;
	private String contentCache;
	
	public BookSection(Context C, Book B, String Name) {
		name = Name;
		book = B;
		context = C;
	}
	
	public String getName() { return name; } 
	public Book getBook() { return book; }
	
	public String getContent() {
		
		if (contentCache != null && contentCache.length() > 0) return contentCache;
		
		String ret = "";
		String path = book.getAssetPath() + getName() + ".txt";
		
		try {
			
		InputStream is = context.getAssets().open(path); 
	       int size = is.available(); 
	        byte[] buffer = new byte[size]; 
	        is.read(buffer); 
	        is.close(); 
	        // Convert the buffer into a string. 
	        ret = new String(buffer); 
		} catch (Exception ex) {
			ret = ex.toString();
		}
		
		contentCache = ret;
		
		return ret;
	}
	
	public String getLineFromPosition(int Position) {
		
		int startPos, endPos;
		boolean c;
		
		// Preparation
		String cont = getContent();
		
		/*
		
		// Move towards begin of line until line break;
		startPos = Position;
		c = true;
		
		while (c) {
			
			if (startPos <= 0) {
				c = false;
			} else {
				String character = cont.substring(startPos, startPos + 1);
				if (character == "\r" || character == "\n") c = false;
				startPos--;
			}
		}
		
		// Move towards end of line until line break
		endPos = Position;
		c = true;
		
		while (c) {
			
			if (endPos >= cont.length()) {
				c = false;
			} else {
				String character = cont.substring(endPos, endPos + 1);
				if (character == "\r" || character == "\n") c = false;
				endPos++;
			}
		}
		*/
		
		if (Position < 100) startPos = 0; else startPos = Position - 100;
		if (Position > cont.length() - 100) endPos = cont.length() - 1; else endPos = Position + 100;
		
		// Return result
		return "(...) " + cont.substring(startPos, endPos) + " (...)";
		
	}
	
}
