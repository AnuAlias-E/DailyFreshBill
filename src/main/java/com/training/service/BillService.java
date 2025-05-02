package com.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.db.BillRepository;
import com.training.dto.BillOrderDTO;
import com.training.dto.BillOrderItemDTO;
import com.training.dto.CustomerBillDTO;
import com.training.dto.OrderBillDTO;
import com.training.dto.OrderBillItemDTO;
import com.training.dto.request.BillAddRequest;
import com.training.dto.request.CustomerRewardUpdateRequest;
import com.training.model.Bill;
import com.training.model.BillItem;

@Service
public class BillService {

	@Autowired
	BillRepository repo;
	@Autowired
	private RestTemplate restTemplate;
	public BillOrderDTO addNewBill(BillAddRequest billAddRequest) {
		OrderBillDTO billDTO = billAddRequest.getOrderBillDTO();
		Bill bill = new Bill();
		bill.setBillDate(billDTO.getBillDate());
		bill.setCustomerId(billDTO.getCustomerId());
		bill.setTotalAmount(billDTO.getTotalAmount());
		 List<BillItem> billItems = new ArrayList<>();
		for(OrderBillItemDTO billItemDTO : billDTO.getOrderBillItemDTOs()) {
			BillItem billItem = new BillItem();
			billItem.setItemName(billItemDTO.getItemName());
			billItem.setPrice(billItemDTO.getPrice());
			billItem.setQuantity(billItemDTO.getQuantity());
			billItems.add(billItem);
		}
		bill.setBillItems(billItems);
		Bill savedBill = repo.save(bill);

        if (savedBill == null) {
            throw new DataIntegrityViolationException("Bill could not be saved.");
        }
			BillOrderDTO billOrderDTO = new BillOrderDTO();
			billOrderDTO.setBillId(savedBill.getId());
			billOrderDTO.setBillDate(savedBill.getBillDate());
			billOrderDTO.setCustomerId(savedBill.getCustomerId());
			billOrderDTO.setTotalAmount(savedBill.getTotalAmount());
			List<BillOrderItemDTO> billOrderItemDTOs = new ArrayList<BillOrderItemDTO>();
			
			for(BillItem billItem : savedBill.getBillItems()) {
				BillOrderItemDTO billOrderItemDTO = new BillOrderItemDTO();
				billOrderItemDTO.setBillItemId(billItem.getId());
				billOrderItemDTO.setItemName(billItem.getItemName());
				billOrderItemDTO.setPrice(billItem.getPrice());
				billOrderItemDTO.setQuantity(billItem.getQuantity());
				billOrderItemDTOs.add(billOrderItemDTO);
				
			}
			billOrderDTO.setBillOrderItemDTOs(billOrderItemDTOs);
			
			updateCustomerRewardPoints(savedBill);

			
			
			return billOrderDTO;

		
		
		
	}

	private void updateCustomerRewardPoints(Bill savedBill) {
		String customerServiceUrl = "http://localhost:8082/api/updateRewardPoints"; // replace with actual
		CustomerRewardUpdateRequest rewardUpdateRequest = new CustomerRewardUpdateRequest();
		CustomerBillDTO customerBillDTO = new CustomerBillDTO();
		customerBillDTO.setCustomerId(savedBill.getCustomerId());
		customerBillDTO.setRewardPoints(savedBill.calculateRewardPoints());
		rewardUpdateRequest.setCustomerBillDTO(customerBillDTO);
		restTemplate.put(customerServiceUrl, rewardUpdateRequest, void.class);
	}

	public List<Bill> getAllBills() {
		return repo.findAll();
	}

}
