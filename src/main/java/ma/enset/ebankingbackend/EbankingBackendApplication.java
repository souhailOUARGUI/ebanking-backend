package ma.enset.ebankingbackend;

import ma.enset.ebankingbackend.dtos.AccountOperationDTO;
import ma.enset.ebankingbackend.entities.*;
import ma.enset.ebankingbackend.enums.AccountStatus;
import ma.enset.ebankingbackend.enums.OperationType;
import ma.enset.ebankingbackend.repositories.AccountOperationRepository;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import ma.enset.ebankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
//          Stream.of("souhail","youssef","aicha").forEach(name -> {
//              Customer customer = new Customer();
//              customer.setName(name);
//              customer.setEmail(name+"@gmail.com");
//              customerRepository.save(customer);
//          });
//            customerRepository.findAll().forEach(cust -> {
//                CurrentAccount currentAccount = new CurrentAccount();
//                currentAccount.setId(UUID.randomUUID().toString());
//                currentAccount.setCreatedAt(new Date());
//                currentAccount.setBalance(Math.random()*90000);
//                currentAccount.setOverdraft(1000);
//                currentAccount.setStatus(AccountStatus.CREATED);
//                currentAccount.setCurrency("USD");
//                currentAccount.setCustomer(cust);
//                bankAccountRepository.save(currentAccount);
//
//                SavingAccount savingAccount = new SavingAccount();
//                savingAccount.setId(UUID.randomUUID().toString());
//                savingAccount.setCreatedAt(new Date());
//                savingAccount.setBalance(Math.random()*90000);
//                savingAccount.setInterestRate(5.5);
//                savingAccount.setStatus(AccountStatus.CREATED);
//                savingAccount.setCurrency("USD");
//                savingAccount.setCustomer(cust);
//                bankAccountRepository.save(savingAccount);
//            });




            BankAccount bankAccount = bankAccountRepository.findById("01c8eee9-7242-4439-acfd-1be354846ea9").orElse(null);
            System.out.println("********************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getCurrency());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCustomer().getName());
            if (bankAccount instanceof CurrentAccount){
               System.out.println("Over Draft=> "+ ((CurrentAccount) bankAccount).getOverdraft());
            }else if (bankAccount instanceof SavingAccount){
                System.out.println("Interest Rate=> "+  ((SavingAccount) bankAccount).getInterestRate());
            }

//            bankAccountRepository.findAll().forEach(acc -> {
//                for (int i = 0; i <10 ; i++) {
//                    AccountOperation accountOperation = new AccountOperation();
//                    accountOperation.setBankAccount(acc);
//                    accountOperation.setOperationDate(new Date());
//                    accountOperation.setAmount(Math.random()*1000);
//                    accountOperation.setOperationType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
//                    accountOperationRepository.save(accountOperation);
//                }
//            });

            bankAccount.getAccountOperationsList().forEach(accOp -> {
                System.out.println(accOp.getOperationType() );
            });


        };
    }

}
