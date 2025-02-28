package bg.softuni.l11springdataaccountsystem.repositories;

import bg.softuni.l11springdataaccountsystem.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountById(Long id);
}
