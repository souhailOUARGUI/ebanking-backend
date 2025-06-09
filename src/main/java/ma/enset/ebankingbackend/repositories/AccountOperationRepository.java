package ma.enset.ebankingbackend.repositories;

import ma.enset.ebankingbackend.entities.AccountOperation;
import ma.enset.ebankingbackend.entities.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> findByBankAccountId(String accountId);
    Page<AccountOperation> findByBankAccountId(String accountId, Pageable pageable);

    @Modifying
    void deleteByBankAccount(BankAccount bankAccount);
}
