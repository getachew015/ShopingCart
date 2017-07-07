package org.dagim.shoppingCart.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo {
	
	private int orderNum;
	private CustomerInfo customerInfo;
	private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

	public CartInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public int getOrderNum(){
		return orderNum;
	}
	public void setOrderNum(int orderNum){
		this.orderNum = orderNum;
	}
	public CustomerInfo getcustomerInfo(){
		return customerInfo;
	}
	public void setcustomerInfo(CustomerInfo customerInfo){
		this.customerInfo = customerInfo;
	}
	public List<CartLineInfo> getCartLInes(){
		return this.cartLines;
	}
	public CartLineInfo findLineByCode(String code){
		for(CartLineInfo line : this.cartLines){
			if (line.getProductInfo().getCode().equals(code)) {
                return line;
		}
			return null;
	}
}
