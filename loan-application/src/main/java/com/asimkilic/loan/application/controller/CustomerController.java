package com.asimkilic.loan.application.controller;

import com.asimkilic.loan.application.dto.credit.CreditResultRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerDeleteRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerDto;
import com.asimkilic.loan.application.dto.customer.CustomerSaveRequestDto;
import com.asimkilic.loan.application.dto.customer.CustomerUpdateRequestDto;
import com.asimkilic.loan.application.gen.entity.BaseCreditResponse;
import com.asimkilic.loan.application.service.CustomerService;
import com.squareup.okhttp.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Operations")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Returns all customers")
    public ResponseEntity<List<CustomerDto>> getAllUsers() {
        List<CustomerDto> customerDtoList = customerService.findAllUsers();
        return ResponseEntity.ok(customerDtoList);
    }

    @PostMapping
    @Operation(summary = "Saves new customer")
    public ResponseEntity<BaseCreditResponse> saveNewCustomer(@RequestBody @Valid CustomerSaveRequestDto customerSaveRequestDto) {
        BaseCreditResponse newCustomer = customerService.saveNewCustomer(customerSaveRequestDto);
        return ResponseEntity.ok(newCustomer);
    }

    @PutMapping
    @Operation(summary = "Updates customer")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody @Valid CustomerUpdateRequestDto customerUpdateRequestDto) {
        CustomerDto newCustomer = customerService.updateCustomer(customerUpdateRequestDto);
        return ResponseEntity.ok(newCustomer);
    }

    @DeleteMapping
    @Operation(summary = "Deletes customer by Turkish Republic Id No")
    public ResponseEntity deleteCustomer(@RequestBody @Valid CustomerDeleteRequestDto customerDeleteRequestDto) {
        customerService.deleteCustomer(customerDeleteRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Returns a customer by customer id")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id) {
        CustomerDto customerDto = customerService.findCustomerById(id);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/trid/{trid}")
    @Operation(summary = "Returns a customer by customer Turkish Republic Id No")
    public ResponseEntity<CustomerDto> getCustomerByTurkishRepublicIdNo(@PathVariable String trid) {
        CustomerDto customerDto = customerService.findCustomerByTurkishRepublicIdNo(trid);
        return ResponseEntity.ok(customerDto);
    }

    @PostMapping("/credit")
    @Operation(summary = "Returns the loan application result")
    public ResponseEntity<BaseCreditResponse> getCreditResult(@RequestBody @Valid CreditResultRequestDto creditResultRequestDto) {
        BaseCreditResponse response = customerService.findCreditResult(creditResultRequestDto);
        return ResponseEntity.ok(response);
    }

}
