package com.training.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BillOrderDTO {

private int billId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate billDate;

	
	private  int customerId;

	
	private List<BillOrderItemDTO> billOrderItemDTOs;

	
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


	public List<BillOrderItemDTO> getBillOrderItemDTOs() {
		return billOrderItemDTOs;
	}


	public void setBillOrderItemDTOs(List<BillOrderItemDTO> billOrderItemDTOs) {
		this.billOrderItemDTOs = billOrderItemDTOs;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public int getBillId() {
		return billId;
	}


	public void setBillId(int billId) {
		this.billId = billId;
	}
	
	



}
