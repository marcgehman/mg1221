package com.marcgehman.toolrental.tool;

public class ToolCharge {
    // The type of tool. The type also specifies the daily rental charge, and the days for
    // which the daily rental charge applies
    ToolType type;
    Double dailyRentalCharge;
    boolean weekdayCharge;
    boolean weekendCharge;
    boolean holidayCharge;

    public ToolCharge() { }

    public ToolCharge(ToolType type, Double dailyRentalCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.type = type;
        this.dailyRentalCharge = dailyRentalCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }


    public ToolType getType() { return type; }
    
    public Double getDailyRentalCharge() { return dailyRentalCharge; }
    
    public boolean getWeekdayCharge() { return weekdayCharge; }
    
    public boolean getWeekendCharge() { return weekendCharge; }
    
    public boolean getHolidayCharge() { return holidayCharge; }
}
