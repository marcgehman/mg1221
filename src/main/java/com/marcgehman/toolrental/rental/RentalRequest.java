package com.marcgehman.toolrental.rental;

import java.sql.Date;

/**
 * Represents a request to rent a tool
 */
public class RentalRequest {
  
    // The tool being rented.
    private String toolCode;
    // Total number of days (including any non-charge days) for which a tool is rented.
    private Integer rentalDays;
    // The date from which the rental starts.
    private Date checkoutDate;
    // Discount percentage (between 0-100).
    private Integer discountPercentage;

    public RentalRequest() { }

    public RentalRequest(String toolCode, Integer rentalDays, Date checkoutDate, Integer discountPercentage) {

    }
    

    /**
     * Determine if this request is valid
     * @return
     * @throws Exception
     */
    public boolean valid() throws Exception {
        
        if (discountPercentage < 0 || discountPercentage > 100) throw new Exception("Discount percentage must be in the range 0-100.");
        
        if (rentalDays < 1) throw new Exception("Rental days must be 1 or greater");

        return toolCode != null &&
            rentalDays != null &&
            checkoutDate != null &&
            discountPercentage != null;
    }
    
    public void print() {
        System.out.println(
            "Tool Code: " + getToolCode() + "\n" +
            "Rental Days: " + rentalDays + "\n" + 
            "Checkout Date: " + checkoutDate + "\n" +
            "Discount Percentage: " + discountPercentage + "%" + "\n"
            );
    }

   
    public String getToolCode() { return toolCode; }
    public void setTool(String toolCode) { this.toolCode = toolCode; }

    public Integer getRentalDays() { return rentalDays; }
    public void setRentalDays(Integer rentalDays) { this.rentalDays = rentalDays; }

    public Date getCheckoutDate() { return checkoutDate; }
    public void setCheckoutDate(Date checkoutDate) { this.checkoutDate = checkoutDate; }

    public Integer getDiscountPercentage() { return discountPercentage; }
    public void setDiscountCharge(Integer discountPercentage) { this.discountPercentage = discountPercentage; }
}