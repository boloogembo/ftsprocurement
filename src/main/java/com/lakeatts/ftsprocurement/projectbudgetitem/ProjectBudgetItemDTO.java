package com.lakeatts.ftsprocurement.projectbudgetitem;
 

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProjectBudgetItemDTO {

    private Long id;
    private Long projectId;
    private String itemName;
    private Integer quantity;
    private BigDecimal unitCost;
    private BigDecimal totalCost;

    
}