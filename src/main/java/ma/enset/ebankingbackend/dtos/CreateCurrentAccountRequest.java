package ma.enset.ebankingbackend.dtos;

import lombok.Data;

@Data
public class CreateCurrentAccountRequest {
    private double initialBalance;
    private double overdraft;
    private Long customerId;
}
