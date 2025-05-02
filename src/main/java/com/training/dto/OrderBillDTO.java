package com.training.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.training.model.BillItem;
import com.training.model.Customer;

public class OrderBillDTO {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate billDate;

	
	private int customerId;

	
	private List<OrderBillItemDTO> orderBillItemDTOs;

	
	private double totalAmount;


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


	public List<OrderBillItemDTO> getOrderBillItemDTOs() {
		return orderBillItemDTOs;
	}


	public void setOrderBillItemDTOs(List<OrderBillItemDTO> orderBillItemDTOs) {
		this.orderBillItemDTOs = orderBillItemDTOs;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
