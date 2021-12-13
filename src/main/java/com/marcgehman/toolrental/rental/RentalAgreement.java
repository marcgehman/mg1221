package com.marcgehman.toolrental.rental;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.marcgehman.toolrental.tool.Tool;

public class RentalAgreement {
  
    // The tool being rented.
    private Tool tool;
    // Total number of days (including any non-charge days) for which a tool is rented.
    private Integer rentalDays;
    // The date from which the rental starts.
    private Date checkoutDate;
    //The date the tool is due.
    private Date dueDate;
    // Amount charged per day, specified by the tool type.
    private Double dailyRentalCharge;
    // The total number of chargeable days, from day after checkout through and including due date,
    // excluding "no charge" days as specified by the tool type.
    private Integer chargeDays;
    //Calculated as charge days * daily charge. Resulting total rounded half up to cents.
    private Double preDiscountCharge;
    // Discount percentage (between 0-100).
    private Integer discountPercentage;
    //Discount Amount - Calculated from discount % and pre-discount charge.
    // Resulting amount round half up to cents.
    private Double discountAmount;
    //Final charge - Calculated as pre-discount charge - discount amount.
    private Double finalCharge; 

    public RentalAgreement() { }

    public RentalAgreement(Tool tool, Integer rentalDays, Date checkoutDate, Date dueDate, Double dailyRentalCharge, Integer chargeDays,
                           Double preDiscountCharge, Integer discountPercentage, Double discountAmount, Double finalCharge) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.dueDate = dueDate;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }
    

	/**
     * Formats a date to mm/dd/yy
     * @param date
     * @return
     */
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        return dateFormat.format(date);
    }

    /**
     * Prints the rental agreement in a formatted human-readable form.
     * e.g.
     * Tool Code: LADW
     * Tool Type: Ladder
     * ...
     * Final Charge: $9.99
     */
    public void print() {
        System.out.println(
            "Tool Code: " + tool.getCode() + "\n" +
            "Tool Type: " + tool.getType() + "\n" +
            "Tool Brand: " + tool.getBrand() + "\n" +
            "Rental Days: " + rentalDays + "\n" + 
            "Checkout Date: " + this.formatDate(checkoutDate) + "\n" +
            "Due Date: " + this.formatDate(dueDate) + "\n" +
            "Daily Rental Charge: " + "$" + String.format("%.2f",dailyRentalCharge) + "\n" +
            "Charge Days: " + chargeDays + "\n" +
            "Pre-Discount Charge: " + "$" + String.format("%.2f",preDiscountCharge) + "\n" + 
            "Discount Percentage: " + discountPercentage + "%" + "\n" +
            "Discount Amount: " + "$" + String.format("%.2f", discountAmount) + "\n" +
            "Final Charge: " + "$" + String.format("%.2f", finalCharge) + "\n" 
            );
    }

    public Tool getTool() { return tool; }
    public void setTool(Tool tool) { this.tool = tool; }

    public Integer getRentalDays() { return rentalDays; }
    public void setRentalDays(Integer rentalDays) { this.rentalDays = rentalDays; }

    public Date getCheckoutDate() { return checkoutDate; }
    public void setCheckoutDate(Date checkoutDate) { this.checkoutDate = checkoutDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate;}

    public Double getDailyRentalCharge() { return dailyRentalCharge; }
    public void setDailyRentalCharge(Double dailyRentalCharge) { this.dailyRentalCharge = dailyRentalCharge; }

    public Integer getChargeDays() { return chargeDays; }
    public void setChargeDays(Integer chargeDays) { this.chargeDays = chargeDays;}

    public Double getPreDiscountCharge() { return preDiscountCharge; }
    public void setPreDiscountCharge(Double preDiscountCharge) { this.preDiscountCharge = preDiscountCharge; }

    public Integer getDiscountPercentage() { return discountPercentage; }
    public void setDiscountCharge(Integer discountPercentage) { this.discountPercentage = discountPercentage; }

    public Double getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(Double discountAmount) { this.discountAmount = discountAmount; }

    public Double getFinalCharge() { return finalCharge; }
    public void setFinalCharge(Double finalCharge) { this.finalCharge = finalCharge; }
}

