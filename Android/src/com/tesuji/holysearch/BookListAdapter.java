package com.tesuji.holysearch;

import java.io.InputStream;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class BookListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private Book[] books;
	
	public BookListAdapter(Context C) {
		context = C;
		
		books = new Book[5];
		
		books[0] = new Book(context, "Old Testament", "Books/English/Old.Testament/King.James.Edition/", new String[] {"Daniel", "Deuteronomy", "Esther", "Exodus", "Ezekiel", "Genesis", 
				"Habakkuk", "Haggai", "Isaiah", "Jeremiah", "Job", "Joshua", "Judges", 
				"Levicitus", "Malachi", "Nahum", "Nehemiah", "Numbers", "Psalms", "Ruth", 
				"Zechariah", "Zephaniah"});
		books[1] = new Book(context, "New Testament", "Books/English/New.Testament/King.James.Edition/", new String[]{"John", "Luke", "Mark", "Matthew"});
		books[2] = new Book(context, "Quran (M.H. Shakir)", "Books/English/Quran/M.H.Shakir.Translation/", new String[]{"Abese", "Adiyat", "Ahkaf", "Ahzab", "Ala", "Alak", "AliImran", "Ankebut", "Araf", "Asr", "Bakara", "Beled", "Beyyine", "Buruc", "Casiye", "Cin", "Cuma", "Duha", "Duhan", "Enam", "Enbiya", "Enfal", "Fatiha", "Fatir", "Fecr", "Felak", "Fetih", "Fil", "Furkan", "Fussilet", "Gasiye", "Hacc", "Hadid", "Hakka", "Hasr", "Hicr", "Hucurat", "Hud", "Humeze", "Ibrahim", "Ihlas", "Intifar", "Insan", "Insikak", "Insirah", "Isra", "Kadir", "Kaf", "Kafirun", "Kalem", "Kamer", "Karia", "Kasas", "Kehf", "Kevser", "Kiyamet", "Kureys", "Leheb", "Leyl", "Lokman", "Maide", "MAun", "Mearic", "Meryem", "Mucadele", "Mudessir", "Muhammed", "Mulk", "Mumin", "Muminun", "Mumtahin", "Munafikun", "Murselat", "Mutaffifin", "Muzzemmil", "Nahl", "Nas", "Nasr", "Naziat", "Nebe", "Necm", "Neml", "Nisa", "Nuh", "Nur", "Rad", "rahman", "Rum", "Sad", "Saff", "Saffat", "Sebe", "Secde", "Sems", "Suara", "Sura", "Taha", "Tahrim", "Talak", "Tarik", "Tegabun", "Tekasur", "Tekvir", "Tevbe", "Tin", "Tur", "Vakia", "Yasin", "Yunus", "Yusuf", "Zariyat", "Zilzal", "Zuhruf", "Zumer" });
		books[3] = new Book(context, "Quran (Mohammad Pickthall)", "Books/English/Quran/Mohammad.Pickthall.Translation/", new String[]{"Abese", "Adiyat", "Ahkaf", "Ahzab", "Ala", "Alak", "AliImran", "Ankebut", "Araf", "Asr", "Bakara", "Beled", "Beyyine", "Buruc", "Casiye", "Cin", "Cuma", "Duha", "Duhan", "Enam", "Enbiya", "Enfal", "Fatiha", "Fatir", "Fecr", "Felak", "Fetih", "Fil", "Furkan", "Fussilet", "Gasiye", "Hacc", "Hadid", "Hakka", "Hasr", "Hicr", "Hucurat", "Hud", "Humeze", "Ibrahim", "Ihlas", "Intifar", "Insan", "Insikak", "Insirah", "Isra", "Kadir", "Kaf", "Kafirun", "Kalem", "Kamer", "Karia", "Kasas", "Kehf", "Kevser", "Kiyamet", "Kureys", "Leheb", "Leyl", "Lokman", "Maide", "MAun", "Mearic", "Meryem", "Mucadele", "Mudessir", "Muhammed", "Mulk", "Mumin", "Muminun", "Mumtahin", "Munafikun", "Murselat", "Mutaffifin", "Muzzemmil", "Nahl", "Nas", "Nasr", "Naziat", "Nebe", "Necm", "Neml", "Nisa", "Nuh", "Nur", "Rad", "rahman", "Rum", "Sad", "Saff", "Saffat", "Sebe", "Secde", "Sems", "Suara", "Sura", "Taha", "Tahrim", "Talak", "Tarik", "Tegabun", "Tekasur", "Tekvir", "Tevbe", "Tin", "Tur", "Vakia", "Yasin", "Yunus", "Yusuf", "Zariyat", "Zilzal", "Zuhruf", "Zumer" });
		books[4] = new Book(context, "Quran (Resad Halife)", "Books/English/Quran/Resad.Halife.Translation/", new String[]{"Abese", "Adiyat", "Ahkaf", "Ahzab", "Ala", "Alak", "AliImran", "Ankebut", "Araf", "Asr", "Bakara", "Beled", "Beyyine", "Buruc", "Casiye", "Cin", "Cuma", "Duha", "Duhan", "Enam", "Enbiya", "Enfal", "Fatiha", "Fatir", "Fecr", "Felak", "Fetih", "Fil", "Furkan", "Fussilet", "Gasiye", "Hacc", "Hadid", "Hakka", "Hasr", "Hicr", "Hucurat", "Hud", "Humeze", "Ibrahim", "Ihlas", "Intifar", "Insan", "Insikak", "Insirah", "Isra", "Kadir", "Kaf", "Kafirun", "Kalem", "Kamer", "Karia", "Kasas", "Kehf", "Kevser", "Kiyamet", "Kureys", "Leheb", "Leyl", "Lokman", "Maide", "MAun", "Mearic", "Meryem", "Mucadele", "Mudessir", "Muhammed", "Mulk", "Mumin", "Muminun", "Mumtahin", "Munafikun", "Murselat", "Mutaffifin", "Muzzemmil", "Nahl", "Nas", "Nasr", "Naziat", "Nebe", "Necm", "Neml", "Nisa", "Nuh", "Nur", "Rad", "rahman", "Rum", "Sad", "Saff", "Saffat", "Sebe", "Secde", "Sems", "Suara", "Sura", "Taha", "Tahrim", "Talak", "Tarik", "Tegabun", "Tekasur", "Tekvir", "Tevbe", "Tin", "Tur", "Vakia", "Yasin", "Yunus", "Yusuf", "Zariyat", "Zilzal", "Zuhruf", "Zumer" });
	}
	

	@Override
	public Object getChild(int arg0, int arg1) {
		return books[arg0].getSection(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		TextView tv = new TextView(context);
		tv.setText(books[arg0].getSection(arg1).getName());
		return tv;
	}

	@Override
	public int getChildrenCount(int arg0) {
		return books[arg0].getSectionCount();
	}

	@Override
	public Object getGroup(int arg0) {
		return books[arg0];
	}

	@Override
	public int getGroupCount() {
		return books.length;
	}

	@Override
	public long getGroupId(int arg0) {
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		TextView tv = new TextView(context);
		tv.setText(books[arg0].getName());
		tv.setPadding(40, 20, 0, 0);
		return tv;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	


}
