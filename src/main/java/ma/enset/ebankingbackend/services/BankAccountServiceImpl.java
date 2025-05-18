package ma.enset.ebankingbackend.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.entities.*;
import ma.enset.ebankingbackend.enums.OperationType;
import ma.enset.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.enset.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.enset.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankingbackend.repositories.AccountOperationRepository;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import ma.enset.ebankingbackend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl  implements BankAccountService{

    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    private AccountOperationRepository accountOperationRepository;


    @Override
    public Customer saveCustomer(Customer customer) {
       log.info("Saving new Customer");
       Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        //log.info("Saving new BankAccount");
        Customer customer  = customerRepository.findById(customerId).orElse(null);
        if (customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }
        CurrentAccount currentAccount = new CurrentAccount() ;
        currentAccount.setCreatedAt(new java.util.Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCurrency("USD");
        currentAccount.setOverdraft(overDraft);
        currentAccount.setCustomer(customer);

       CurrentAccount savedbkAcc =   bankAccountRepository.save(currentAccount);
        return savedbkAcc;
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer  = customerRepository.findById(customerId).orElse(null);
        if (customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }
        SavingAccount savingAcc = new SavingAccount() ;
        savingAcc.setCreatedAt(new java.util.Date());
        savingAcc.setBalance(initialBalance);
        savingAcc.setCurrency("USD");
        savingAcc.setInterestRate(interestRate);
        savingAcc.setCustomer(customer);

        SavingAccount savedbkAcc =   bankAccountRepository.save(savingAcc);
        return savedbkAcc;
    }


    @Override
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public BankAccount getBankAccountById(String id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()-> new BankAccountNotFoundException("Bank Account not found"));

        return bankAccount;
    }

    @Override
    public void debit(String id, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = getBankAccountById(id);
        if (bankAccount.getBalance() < amount){
            throw new BalanceNotSufficientException("Balance not sufficient");
        }
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new java.util.Date());
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void credit(String id, double amount, String description) throws BankAccountNotFoundException{
        BankAccount bankAccount = getBankAccountById(id);
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new java.util.Date());
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void Transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource, amount, "Transfer to"+accountIdDestination );
        credit(accountIdDestination, amount, "Transfer from" + accountIdSource);
    }
}
