package com.training.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "bill_date")
	private LocalDate billDate;

	 @Column(name = "customer_id", nullable = false)
	private int  customerId;

	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
	private List<BillItem> billItems;

	
	private double totalAmount;

	public Bill() {
		super();
	}

	public Bill(int id, LocalDate billDate, int customerId, List<BillItem> billItems, double totalAmount) {
		super();
		this.id = id;
		this.billDate = billDate;
		this.customerId = customerId;
		this.billItems = billItems;
		this.totalAmount = totalAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<BillItem> getBillItems() {
		return billItems;
	}

	public void setBillItems(List<BillItem> billItems) {
		this.billItems = billItems;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	// Utility Methods
	private double calculateTotalAmount() {
		return billItems.stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
	}

	public int calculateRewardPoints() {
		return (int) (this.calculateTotalAmount() / 10); // Example: 1 point per ₹10 spent
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	

	@Override
	public String toString() {
		return "Bill [id=" + id + ", billDate=" + billDate + ", customerId=" + customerId + ", billItems=" + billItems
				+ ", totalAmount=" + totalAmount + "]";
	}
	
	

}