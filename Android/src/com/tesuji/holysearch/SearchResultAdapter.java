package com.tesuji.holysearch;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchResultAdapter extends BaseAdapter {

	private BookListAdapter bla;
	private String phrase;
	private SearchResult[] results;
	private Context context;
	
	public SearchResultAdapter(String Phrase, BookListAdapter BLA, Context C) {
		phrase = Phrase.toLowerCase();
		bla = BLA;
		context = C;
		doSearch();
	}
	
	private void doSearch() {
		
		for (int b = 0; b < bla.getGroupCount(); b++) {
			Book book = (Book) bla.getGroup(b);
			
			for (int s = 0; s < book.getSectionCount(); s++) {
				BookSection bs = (BookSection) bla.getChild(b, s);
				String bookContent = bs.getContent().toLowerCase();
				boolean cont = true;
				int pos = 0;
				
				while (cont) {
					int resPos = bookContent.indexOf(phrase, pos);	
					if (resPos >= 0) {
						String snippet = bs.getLineFromPosition(resPos);
						addSearchResult(new SearchResult(book, s, snippet));
					}  else cont = false;
					
					pos = resPos + 1;
					if (pos >= bookContent.length()) cont = false;
				}
				
			}
		}
	}
	
	private void addSearchResult(SearchResult S) {
		if (results == null) {
			results = new SearchResult[1];
			results[0] = S;
		}
		else {
			SearchResult[] temp = new SearchResult[results.length + 1];
			for (int n = 0; n < results.length; n++) temp[n] = results[n];
			temp[results.length] = S;
			results = temp;
		}
	}
	
	@Override
	public int getCount() {
		if (results == null) return 0; else return results.length;
	}

	@Override
	public Object getItem(int arg0) {
		if (results == null) return null; else return results[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		if (results == null) return 0; else return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		TextView titleView = new TextView(context);
		titleView.setText(results[arg0].getBook().getName() + " - " + results[arg0].getSection().getName());
		titleView.setPadding(40, 10, 0, 0);
		titleView.setTypeface(null, 1);
		
		TextView tv = new TextView(context);
		tv.setText(results[arg0].getSnippet());
		tv.setPadding(40, 0, 0, 10);
		
		LinearLayout ll = new LinearLayout(context);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.addView(titleView, 0);
		ll.addView(tv, 1);
		
		return ll;
	}

}
