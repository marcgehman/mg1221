package com.marcgehman.toolrental.rental;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import com.marcgehman.toolrental.checkout.CheckoutService;
import com.marcgehman.toolrental.rental.*;
import com.marcgehman.toolrental.tool.Tool;
import com.marcgehman.toolrental.tool.ToolCharge;
import com.marcgehman.toolrental.tool.ToolChargeMapper;
import com.marcgehman.toolrental.tool.ToolMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentalResource {
    
    private final RentalAgreementMapper rentalAgreementMapper;
    private final ToolMapper toolMapper;
    private final ToolChargeMapper toolChargeMapper;
    private final CheckoutService checkoutService;

    RentalResource(RentalAgreementMapper rentalAgreementMapper, ToolMapper toolMapper, ToolChargeMapper toolChargeMapper, CheckoutService checkoutService) {
        this.rentalAgreementMapper = rentalAgreementMapper;
        this.toolMapper = toolMapper;
        this.toolChargeMapper = toolChargeMapper;
        this.checkoutService = checkoutService;
    }


    /**
     * List all of the rental agreements.
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity all() {
        return ResponseEntity
            .ok()
            .body(rentalAgreementMapper.findAll());
    }

    /**
     * Find a rental agreement for a specific tool code.
     * @return
     */
    @GetMapping("/one/{code}")
    public ResponseEntity one(@PathVariable String code) {
        RentalAgreement rentalAgreement = rentalAgreementMapper.findById(code);
        if (rentalAgreement != null) {
            rentalAgreement.print();
            return ResponseEntity
                .ok()
                .body(rentalAgreement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Handle a checkout for a tool rental, generate rental agreement, and add to the database.
     * @param rentalRequest 
     * @return
     * @throws Exception
     */
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody RentalRequest rentalRequest) throws Exception {
        if (rentalRequest != null && rentalRequest.valid()) {
            RentalAgreement rental = checkoutService.checkout(rentalRequest);
           //Integer result = rentalAgreementMapper.insert(rental);    
           Integer result = 1; 
           return ResponseEntity
                .ok()
                .body(result);
        } else {
            return ResponseEntity
                .badRequest()
                .body("Request is invalid");
        }


    } 
        
}
