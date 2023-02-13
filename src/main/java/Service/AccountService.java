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
        Account accountFromDB = this.accountDAO.getAccountByUsername(account.getUsername());
        if(account.username != "" && account.password.length() >=4 && accountFromDB != null){
            return accountDAO.registerAccount(account);
        }
        return null;
    }

    public Account logIn(Account account){
        accountDAO.userLogin(account);
        return account;
    }

}
