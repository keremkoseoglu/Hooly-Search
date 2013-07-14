package com.tesuji.holysearch;

public class SearchResult {

	private Book book;
	private int sectionIndex;
	private String snippet;
	
	public SearchResult(Book Book, int Section, String Snippet) {
		book = Book;
		sectionIndex = Section;
		snippet = Snippet;
	}
	
	public Book getBook() { return book; }
	public BookSection getSection() { return book.getSection(sectionIndex); }
	public String getSnippet() { return snippet; } 
	
}
