package ma.enset.ebankingbackend;

import ma.enset.ebankingbackend.dtos.AccountOperationDTO;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.repositories.AccountOperationRepository;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import ma.enset.ebankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
          Stream.of("souhail","youssef","aicha").forEach(name -> {
              Customer customer = new Customer();
              customer.setName(name);
              customer.setEmail(name+"@gmail.com");
              customerRepository.save(customer);
          });
        };
    }

}
