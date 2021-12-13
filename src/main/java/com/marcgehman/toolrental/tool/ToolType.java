package com.marcgehman.toolrental.tool;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Enum responsible for representing different types of tools.
 * Automatically serialized/deserialized appropriately.
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ToolType {
   @JsonProperty("Ladder")
   LADDER("Ladder"),
   
   @JsonProperty("Chainsaw")
   CHAINSAW("Chainsaw"),

   @JsonProperty("Jackhammer")
   JACKHAMMER("Jackhammer");

   public final String label;
   private ToolType(String label) {
       this.label = label;
   }

   @JsonValue
   public String toValue() {
       return label;
   }
}
