package com.marcgehman.toolrental.checkout;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import com.marcgehman.toolrental.rental.RentalAgreement;
import com.marcgehman.toolrental.rental.RentalRequest;
import com.marcgehman.toolrental.tool.Tool;
import com.marcgehman.toolrental.tool.ToolCharge;
import com.marcgehman.toolrental.tool.ToolChargeMapper;
import com.marcgehman.toolrental.tool.ToolMapper;

import org.springframework.stereotype.Service;


/**
 * Service responsible for handling logic pertaining to checking out a rental and generating a rental agreement.
 */
@Service
public class CheckoutService {
    
    private final ToolMapper toolMapper;
    private final ToolChargeMapper toolChargeMapper;
   
    CheckoutService(ToolMapper toolMapper, ToolChargeMapper toolChargeMapper) {
        this.toolMapper = toolMapper;
        this.toolChargeMapper = toolChargeMapper;
    }

    public RentalAgreement checkout(RentalRequest rentalRequest) {
        try {
            if (rentalRequest != null && rentalRequest.valid()) {
                RentalAgreement rental =  generateRentalAgreement(rentalRequest);
                rental.print();
                return rental;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Generate a RentalAgreement from a RentalRequest
     * @param rentalRequest
     * @return
     */
    private RentalAgreement generateRentalAgreement(RentalRequest rentalRequest) {
        // Try to find the tool identified in the request.
        Tool tool = toolMapper.findById(rentalRequest.getToolCode());
        
        if (tool != null) {
        // Get the charge data for the type of tool being rented.
        ToolCharge toolCharge = toolChargeMapper.findByType(tool.getType());
        // Calculate due date
        java.util.Date tmp = new Date(new java.util.Date(rentalRequest.getCheckoutDate().getTime()).toInstant().plus(rentalRequest.getRentalDays(), ChronoUnit.DAYS).toEpochMilli());
        Date dueDate = new Date(tmp.getTime());
        // Get daily rental charge
        Double dailyRentalCharge = toolCharge.getDailyRentalCharge();
        // Determine # of charge days
        Integer chargeDays = getChargeDays(rentalRequest, toolCharge); 
        // Calculate preDiscountCharge
        Double preDiscountCharge = chargeDays * dailyRentalCharge;
        // Calculate discount amount
        Double discountAmount = (rentalRequest.getDiscountPercentage() / 100.00) * preDiscountCharge;
        // Calculate final charge
        Double finalCharge = preDiscountCharge - discountAmount;
            return new RentalAgreement(tool, rentalRequest.getRentalDays(),
                rentalRequest.getCheckoutDate(), dueDate, dailyRentalCharge, chargeDays,
                preDiscountCharge, rentalRequest.getDiscountPercentage(), discountAmount, finalCharge);
        } else {
            return null;
        }

    }
    
    
    /**
     * Based on a rental request, and associated Tool Charge, determine the number of chargeable days
     * @param rentalRequest
     * @return
     */
    private Integer getChargeDays(RentalRequest rentalRequest, ToolCharge toolCharge) {
       Integer numChargeDays = 0;
    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rentalRequest.getCheckoutDate());
        // Countable charge days starts from day after checkout.
        calendar.add(Calendar.DATE, 1);
        
        for (int i = 0; i < rentalRequest.getRentalDays(); i++) {
            // Is a holiday chargeable
            if (toolCharge.getHolidayCharge() && isHoliday(calendar)) {
                numChargeDays += 1;
            }
            // Is a weekend day chargeable
            else if (toolCharge.getWeekendCharge() && isWeekend(calendar)) {
                numChargeDays += 1;
            }
            // Is a weekday chargeable
            else if (toolCharge.getWeekdayCharge() && !isWeekend(calendar) && !isHoliday(calendar)) {
                numChargeDays += 1;
            }
            // Increment calendar by one day.
            calendar.add(Calendar.DATE, 1);
        }
        return numChargeDays;
    }

    /**
     * Determine if the date is considered one of the holidays observed by the company.
     * @param date
     * @return
     */
    private boolean isHoliday(Calendar calendar) {
        return isIndependenceDayHoliday(calendar) || isLaborDayHoliday(calendar);
    }

    /**
     * Determine if the date is considered to be observed as Independence Day.
     * If falls on weekend, it is observed on the closest weekday
     * (if Saturday, then it is observed the Friday before,
     *  if Sunday then it is observed Monday after)
     * @param calendar
     * @return
     */
    private boolean isIndependenceDayHoliday(Calendar calendar) {
        // If 4th of July is on Saturday and the date is the friday before.
        boolean isFridayBefore = calendar.get(Calendar.DATE) == 3 && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
        // If 4th of July is on Sunday and the date is the monday after.
        boolean isMondayAfter = calendar.get(Calendar.DATE) == 5 && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
        // If 4th of July occurs during the week.
        boolean isWeekday = calendar.get(Calendar.DATE) == 4 && !isWeekend(calendar);
        
        if (calendar.get(Calendar.MONTH) == Calendar.JULY && (isFridayBefore || isMondayAfter || isWeekday)) {
            return true;
        } else {
            return false;
        }  

    }

    /**
     * Determine if the date is on Labor Day (defined as first monday in September)
     * @param calendar
     * @return
     */
    private boolean isLaborDayHoliday(Calendar calendar) {
       if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER 
            && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY 
            && calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1) {
            return true;
        }        
        return false;
    }


    /**
     * Determine if a date occurs on a weekend.
     * @param date
     * @return
     */
    private boolean isWeekend(Calendar cal) {
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }
}
