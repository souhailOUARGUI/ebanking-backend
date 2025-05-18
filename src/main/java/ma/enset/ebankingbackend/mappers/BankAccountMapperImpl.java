package ma.enset.ebankingbackend.mappers;


import ma.enset.ebankingbackend.dtos.AccountOperationDTO;
import ma.enset.ebankingbackend.dtos.CurrentBankAccountDTO;
import ma.enset.ebankingbackend.dtos.CustomerDTO;
import ma.enset.ebankingbackend.dtos.SavingBankAccountDTO;
import ma.enset.ebankingbackend.entities.AccountOperation;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
public CustomerDTO fromCustomer(Customer customer){
    CustomerDTO customerDTO = new CustomerDTO();
    BeanUtils.copyProperties(customer,customerDTO);
    return customerDTO;
}

public Customer fromCustomerDTO(CustomerDTO customerDTO){
    Customer customer = new Customer();
    BeanUtils.copyProperties(customerDTO,customer);
    return customer;
}

public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
    SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
    BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
    savingBankAccountDTO.setCustomer(fromCustomer(savingAccount.getCustomer()));
    savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
    return savingBankAccountDTO;
}

public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
    SavingAccount savingAccount = new SavingAccount();
    BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
    return savingAccount;
}

public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
    CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
    BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
    currentBankAccountDTO.setCustomer(fromCustomer(currentAccount.getCustomer()));
    currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
    return currentBankAccountDTO;
}



public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
    CurrentAccount currentAccount = new CurrentAccount();
    BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
    return currentAccount;
}

public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
    AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
    BeanUtils.copyProperties(accountOperation, accountOperationDTO);

    return accountOperationDTO;
}



}