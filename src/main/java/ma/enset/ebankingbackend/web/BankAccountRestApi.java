package ma.enset.ebankingbackend.web;


import lombok.AllArgsConstructor;
import ma.enset.ebankingbackend.dtos.*;
import ma.enset.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.enset.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestApi {
    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "5")int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }

    @PostMapping("/accounts/current")
    public CurrentBankAccountDTO createCurrentAccount(@RequestBody CreateCurrentAccountRequest request) throws CustomerNotFoundException {
        return bankAccountService.saveCurrentBankAccount(
                request.getInitialBalance(),
                request.getOverdraft(),
                request.getCustomerId()
        );
    }

    @PostMapping("/accounts/saving")
    public SavingBankAccountDTO createSavingAccount(@RequestBody CreateSavingAccountRequest request) throws CustomerNotFoundException {
        return bankAccountService.saveSavingBankAccount(
                request.getInitialBalance(),
                request.getInterestRate(),
                request.getCustomerId()
        );
    }

    @PostMapping("/accounts")
    public BankAccountDTO createAccount(@RequestBody CreateAccountRequest request) throws CustomerNotFoundException {
        if ("current".equalsIgnoreCase(request.getType())) {
            double overdraft = request.getOverdraft() != null ? request.getOverdraft() : 1000.0; // default overdraft
            return bankAccountService.saveCurrentBankAccount(
                    request.getInitialBalance(),
                    overdraft,
                    request.getCustomerId()
            );
        } else if ("saving".equalsIgnoreCase(request.getType())) {
            double interestRate = request.getInterestRate() != null ? request.getInterestRate() : 5.5; // default interest rate
            return bankAccountService.saveSavingBankAccount(
                    request.getInitialBalance(),
                    interestRate,
                    request.getCustomerId()
            );
        } else {
            // Default to current account if type is not specified or unknown
            double overdraft = request.getOverdraft() != null ? request.getOverdraft() : 1000.0;
            return bankAccountService.saveCurrentBankAccount(
                    request.getInitialBalance(),
                    overdraft,
                    request.getCustomerId()
            );
        }
    }

    @DeleteMapping("/accounts/{accountId}")
    public void deleteBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        bankAccountService.deleteBankAccount(accountId);
    }
}
