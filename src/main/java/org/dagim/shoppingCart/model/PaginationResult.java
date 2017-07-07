package org.dagim.shoppingCart.model;

import java.util.List;

public class PaginationResult<E> {

	   private int totalRecords;
	   private int currentPage;
	   private List<E> list;
	   private int maxResult;
	   private int totalPages;	 
	   private int maxNavigationPage;	 
	   private List<Integer> navigationPages;

	   public PaginationResult() {
		// TODO Auto-generated constructor stub
	}

	public int getTotalRecords() {
		return totalRecords;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<E> getList() {
		return list;
	}
	public int getMaxResult() {
		return maxResult;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getMaxNavigationPage() {
		return maxNavigationPage;
	}
	public List<Integer> getNavigationPages() {
		return navigationPages;
	}
}
