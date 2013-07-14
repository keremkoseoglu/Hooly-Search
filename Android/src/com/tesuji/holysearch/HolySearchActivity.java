package com.tesuji.holysearch;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HolySearchActivity extends Activity {
    
	private BookListAdapter bla;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Setup book list view
        bla = new BookListAdapter(this);
        ExpandableListView lstBooks = (ExpandableListView) findViewById(R.id.lstBooks);
        lstBooks.setAdapter(bla);
        lstBooks.setOnChildClickListener(BookClickListener);
        
        // Setup search results
        ListView lv = (ListView) findViewById(R.id.lstResult);
        lv.setVisibility(View.INVISIBLE);
        lv.setOnItemClickListener(ResultClickListener);
        
        // Setup button
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(ButtonClickListener);
        
        // Setup Textbox
        ((EditText) findViewById(R.id.editText1)).setKeyListener(SearchListener);
    }
    
    ExpandableListView.OnChildClickListener BookClickListener = new ExpandableListView.OnChildClickListener() {
		
		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
	        
			ListView lv = (ListView) findViewById(R.id.lstResult);
	        lv.setVisibility(View.GONE);
			
			EditText t = (EditText) findViewById(R.id.txtContent);
			t.setText(((BookSection)bla.getChild(groupPosition, childPosition)).getContent());
			t.setVisibility(View.VISIBLE);
			
			TextView tv = (TextView) findViewById(R.id.txtTitle);
			tv.setText(((BookSection)bla.getChild(groupPosition, childPosition)).getName());

			
			return false;
		}
	};
	
	View.OnClickListener ButtonClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			// Make textbox invisible
			EditText t = (EditText) findViewById(R.id.txtContent);
			t.setVisibility(View.GONE);
	        
	        // Get phrase
	        EditText p = (EditText) findViewById(R.id.editText1);
	        
	        // Toast
	        Toast.makeText(v.getRootView().getContext(), "Searching \"" + p.getText().toString() + "\"...", 3).show();
	        
	        // Search
	        SearchResultAdapter sra = new SearchResultAdapter(p.getText().toString(), bla, v.getContext());
	        ListView lv = (ListView) findViewById(R.id.lstResult);
	        lv.setVisibility(View.VISIBLE);
	        lv.setAdapter((ListAdapter) sra);
	        
	        // Title
	        TextView tv = (TextView) findViewById(R.id.txtTitle);
	        tv.setText("\"" + p.getText().toString() + "\"");
		}
	};
	
	KeyListener SearchListener = new KeyListener() {

		@Override
		public void clearMetaKeyState(View view, Editable content, int states) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getInputType() {
			
			return InputType.TYPE_CLASS_TEXT;
		}

		@Override
		public boolean onKeyDown(View view, Editable text, int keyCode,
				KeyEvent event) {
			
			if (keyCode == KeyEvent.KEYCODE_ENTER) {
				ButtonClickListener.onClick((Button)findViewById(R.id.button1));
				return true;
			}
			
			return false;
		}

		@Override
		public boolean onKeyOther(View view, Editable text, KeyEvent event) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onKeyUp(View view, Editable text, int keyCode,
				KeyEvent event) {
		
			
			return false;
		}
		

	};
	
	AdapterView.OnItemClickListener ResultClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			// Get selected item
			ListView lv = (ListView) findViewById(R.id.lstResult);
			SearchResult sr = (SearchResult) lv.getItemAtPosition(arg2);
			
			// Setup form
			lv.setVisibility(View.GONE);
			
			EditText t = (EditText) findViewById(R.id.txtContent);
			t.setVisibility(View.VISIBLE);
			t.setText(sr.getSection().getContent());
			
			TextView tv = (TextView) findViewById(R.id.txtTitle);
			tv.setText(sr.getSection().getName());			
		}
		
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			((EditText) findViewById(R.id.txtContent)).setVisibility(View.GONE);
			((ListView)findViewById(R.id.lstResult)).setVisibility(View.VISIBLE);
			
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	
    	
}
