package ma.enset.ebankingbackend;

import ma.enset.ebankingbackend.dtos.*;
import ma.enset.ebankingbackend.entities.*;
import ma.enset.ebankingbackend.enums.AccountStatus;
import ma.enset.ebankingbackend.enums.OperationType;
import ma.enset.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankingbackend.repositories.AccountOperationRepository;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import ma.enset.ebankingbackend.repositories.CustomerRepository;
import ma.enset.ebankingbackend.services.BankAccountService;
import ma.enset.ebankingbackend.services.BankService;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }
@Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {

//            Stream.of("amine","aziz","Mohamed").forEach(name->{
//                CustomerDTO customer=new CustomerDTO();
//                customer.setName(name);
//                customer.setEmail(name+"@gmail.com");
//                bankAccountService.saveCustomer(customer);
//            });

            bankAccountService.bankAccountList().forEach(bankAccountDTO -> {
                System.out.println(bankAccountDTO.getType());
            });



//            bankAccountService.listCustomers().forEach(customer->{
//                try {
//                    bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
//                    bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());
//
//                } catch (CustomerNotFoundException e) {
//                    e.printStackTrace();
//                }
//            });
//
//            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
//            for (BankAccountDTO bankAccount:bankAccounts){
//                for (int i = 0; i <10 ; i++) {
//                    String accountId;
//                    if(bankAccount instanceof SavingBankAccountDTO){
//                        accountId=((SavingBankAccountDTO) bankAccount).getId();
//                    } else{
//                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
//                    }
//                    bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
//                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
//                }
//            }
//        };
//}
//}







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

        };
    }

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Fix: Don't convert list to string
        config.addAllowedOrigin("http://localhost:4200");
        
        config.setAllowedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type",
                "Accept","Authorization","Origin,Accept","X-Request-With","Access-Control-Request-Method",
                "Access-Control-Request-Headers"));
        config.setExposedHeaders(Arrays.asList("Origin","Content-Type","Accept","Authorization","Access-Control-Allow-Origin",
                "Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",config);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
