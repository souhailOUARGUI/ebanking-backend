package ma.enset.ebankingbackend.services;


import jakarta.transaction.Transactional;
import ma.enset.ebankingbackend.entities.BankAccount;
import ma.enset.ebankingbackend.entities.CurrentAccount;
import ma.enset.ebankingbackend.entities.SavingAccount;
import ma.enset.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService
{
    @Autowired
    BankAccountRepository bankAccountRepository;

    public void consulter(){
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

        bankAccount.getAccountOperationsList().forEach(accOp -> {
            System.out.println(accOp.getOperationType() );
        });
    }
}
