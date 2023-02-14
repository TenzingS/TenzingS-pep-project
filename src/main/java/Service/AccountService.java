package Service;

import Model.Account;

import org.eclipse.jetty.util.security.Password;

import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;
    
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account){
        String accountUsername = account.getUsername();
        Account accountFromDB = this.accountDAO.getAccountByUsername(accountUsername);
        if(account.username != "" && account.password.length() >=4){
            return accountDAO.registerAccount(account);
        }
        return null;
    }

    public Account logIn(Account account){
        String username = account.getUsername();
        String password = account.getPassword();
        accountDAO.userLogin(username, password);
        return account;
    }

}
