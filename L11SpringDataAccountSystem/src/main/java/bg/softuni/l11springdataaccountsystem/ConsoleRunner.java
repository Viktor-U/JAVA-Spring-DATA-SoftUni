package bg.softuni.l11springdataaccountsystem;

import bg.softuni.l11springdataaccountsystem.models.Account;
import bg.softuni.l11springdataaccountsystem.models.User;
import bg.softuni.l11springdataaccountsystem.services.AccountService;
import bg.softuni.l11springdataaccountsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("Pesho", 20);


        Account account = new Account(new BigDecimal("250000"), user);


        userService.register(user);

        accountService.withdrawMoney(new BigDecimal("20000"),account.getId() );
        accountService.transferMoney(new BigDecimal("30000"),account.getId() );
    }
}
