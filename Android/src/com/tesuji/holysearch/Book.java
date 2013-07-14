package com.tesuji.holysearch;

import android.content.Context;

public class Book {

	private String name;
	private BookSection[] sections;
	private String assetPath;
	private Context context;
	
	public Book(Context C, String Name, String AssetPath, String[] Sections) {
		context = C;
		name = Name;
		assetPath = AssetPath;
		sections = new BookSection[Sections.length];
		for (int n = 0; n < Sections.length; n++) sections[n] = new BookSection(context, this, Sections[n]);
	}
	
	public int getSectionCount() { return sections.length; }
	public BookSection getSection(int Index) { return sections[Index]; }
	public String getName() { return name; }
	public String getAssetPath() { return assetPath; }
	
}
