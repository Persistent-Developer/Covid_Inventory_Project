
package com.psl.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity(name="inventory")
@JsonIgnoreProperties({"hibernateLazyInitializer","Handler"})
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "product_code",unique = true)
	private String productCode;
	
	@NotNull
	@Column(name = "product_name")
	private String productName;
	
	@NotNull
	@Column(name = "price")
	private double price;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "product_group")
	private String productGroup;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "low_stock_indicator")
	private int lowStockIndicator;
	
	@Column(name = "in_stock")
	private String inStock;
	
	@Column(name = "item_type")
	private String itemType;
	
	@Column(name = "monthly_quota_per_user")
	private String monthlyQuotaPerUser;
	
	@Column(name = "yearly_quota_per_user")
	private String yearlyQuotaPerUser;
	
	@ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="OrderDetails",
			joinColumns = {@JoinColumn(name="fk_product_id")},
			inverseJoinColumns = {@JoinColumn (name="fk_order_id")}
			)
	private List<Orders> odList= new ArrayList<>();
	
	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name ="store_id")
	private Store store;
	
	public Inventory() {
		super();
	}

	public Inventory(int id, String productCode, String productName, double price, int stock, String productGroup,
			String category, int lowStockIndicator, String inStock, String itemType, String monthlyQuotaPerUser,
			String yearlyQuotaPerUser, List<Orders> odList, Store store) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.productGroup = productGroup;
		this.category = category;
		this.lowStockIndicator = lowStockIndicator;
		this.inStock = inStock;
		this.itemType = itemType;
		this.monthlyQuotaPerUser = monthlyQuotaPerUser;
		this.yearlyQuotaPerUser = yearlyQuotaPerUser;
		this.odList = odList;
		this.store = store;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getLowStockIndicator() {
		return lowStockIndicator;
	}

	public void setLowStockIndicator(int lowStockIndicator) {
		this.lowStockIndicator = lowStockIndicator;
	}

	public String getInStock() {
		return inStock;
	}

	public void setInStock(String inStock) {
		this.inStock = inStock;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getMonthlyQuotaPerUser() {
		return monthlyQuotaPerUser;
	}

	public void setMonthlyQuotaPerUser(String monthlyQuotaPerUser) {
		this.monthlyQuotaPerUser = monthlyQuotaPerUser;
	}

	public String getYearlyQuotaPerUser() {
		return yearlyQuotaPerUser;
	}

	public void setYearlyQuotaPerUser(String yearlyQuotaPerUser) {
		this.yearlyQuotaPerUser = yearlyQuotaPerUser;
	}

	public List<Orders> getOdList() {
		return odList;
	}

	public void setOdList(List<Orders> odList) {
		this.odList = odList;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", productCode=" + productCode + ", productName=" + productName + ", price="
				+ price + ", stock=" + stock + ", productGroup=" + productGroup + ", category=" + category
				+ ", lowStockIndicator=" + lowStockIndicator + ", inStock=" + inStock + ", itemType=" + itemType
				+ ", monthlyQuotaPerUser=" + monthlyQuotaPerUser + ", yearlyQuotaPerUser=" + yearlyQuotaPerUser
				+ ", odList=" + odList + ", store=" + store + "]";
	}	
}
