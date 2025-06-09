package ma.enset.ebankingbackend.dtos;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String type; // "current" or "saving"
    private double initialBalance;
    private Long customerId;
    
    // For current accounts
    private Double overdraft;
    
    // For saving accounts  
    private Double interestRate;
}
