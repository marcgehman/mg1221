package com.marcgehman.toolrental.tool;

public class Tool {

    // Unique identifier for a tool instance
    String code;
    // The type of tool. The type also specifies the daily rental charge, and the days for
    // which the daily rental charge applies
    ToolType type;
    // The brand of the tool
    String brand;

    public Tool() { }

    public Tool(String code, ToolType type, String brand) {
        this.code = code;
        this.type = type;
        this.brand = brand;
    }


    public String getCode() { return code; }

    public ToolType getType() { return type; }

    public String getBrand() { return brand; }

}