package com.training.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.training.dto.BillOrderDTO;
import com.training.dto.request.BillAddRequest;
import com.training.model.Bill;
import com.training.model.Customer;
import com.training.service.BillService;

@RestController
@RequestMapping(value = "/api")
public class BillController {

	@Autowired
	BillService service;

	

	@PostMapping(value = "/addBill")
	public ResponseEntity<String> f1(@RequestBody BillAddRequest addRequest) {
		BillOrderDTO billOrderDTO = this.service.addNewBill(addRequest);
		if(billOrderDTO!=null) {
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/showAll")
	public ResponseEntity<Bill> f4() {
		List<Bill> bills = this.service.getAllBills();
		return new ResponseEntity(bills, HttpStatus.OK);
	}

}
