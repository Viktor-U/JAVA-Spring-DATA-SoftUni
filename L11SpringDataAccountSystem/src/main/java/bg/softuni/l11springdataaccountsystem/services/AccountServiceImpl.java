package bg.softuni.l11springdataaccountsystem.services;

import bg.softuni.l11springdataaccountsystem.models.Account;
import bg.softuni.l11springdataaccountsystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> account = this.accountRepository.findById(id);

        if (account.isEmpty()){
            throw new RuntimeException("Account does not exist");
        }

        BigDecimal balance = account.get().getBalance();

        if (money.compareTo(balance) > 0){
            throw new RuntimeException("Cannot withdraw!");
        }

        account.get().setBalance(balance.subtract(money));
        this.accountRepository.save(account.get());
    }
    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Sorry no account"));
        BigDecimal balance = account.getBalance().add(money);
        account.setBalance(balance);

        this.accountRepository.save(account);


    }
}
