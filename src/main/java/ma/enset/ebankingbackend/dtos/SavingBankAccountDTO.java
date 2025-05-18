package ma.enset.ebankingbackend.dtos;


import lombok.Data;
import ma.enset.ebankingbackend.enums.AccountStatus;

import java.util.Date;

@Data
public class SavingBankAccountDTO extends BankAccountDTO{
private String id;
private String currency;
private Date createdAt;
private AccountStatus status;
private CustomerDTO customer;
//private double balance;
private double interestRate;

}
