package org.dagim.shoppingCart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "Order_Details")
public class OrderDetail implements Serializable{

    private String id;
    private Order order; 
    private Product product;
    private int quanity;
    private double price;
    private double amount;

    
    public OrderDetail() {
		// TODO Auto-generated constructor stub
	}
        
    @Id
    @Column(name = "ID", length = 50, nullable = false)
    public void getId(){
    	
    }
    
    public void setId(String id){
    	this.id = id;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false, //
    foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORD_FK") )
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order){
    	this.order = order;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, //
    foreignKey = @ForeignKey(name = "ORDER_DETAIL_PROD_FK") )
    public Product getProduct() {
        return product;
    }
 
    public void setProduct(Product product) {
        this.product = product;
    }
    
    @Column(name = "Quanity", nullable = false)
    public int getQuanity() {
        return quanity;
    }
 
    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
 
    @Column(name = "Price", nullable = false)
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    @Column(name = "Amount", nullable = false)
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }

}
