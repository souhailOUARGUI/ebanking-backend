package ma.enset.ebankingbackend.dtos;

import lombok.Data;

@Data
public class CreateSavingAccountRequest {
    private double initialBalance;
    private double interestRate;
    private Long customerId;
}
