package ma.enset.ebankingbackend.dtos;

import lombok.Data;
import ma.enset.ebankingbackend.enums.OperationType;

import java.util.Date;

@Data
public class AccountOperationDTO {
private Long id;
private double amount;
private Date operationDate;
private OperationType operationType;
private String description;
//private BankAccountDTO bankAccount;
}
