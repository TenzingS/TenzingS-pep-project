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
        Account accountUsername = account.getUsername();
        Account accountFromDB = this.accountDAO.getAccountByUsername(accountUsername);
        if(accountFromDB != null){
            return null;
        } if(account.username != "" && account.password.length() >=4){
            return accountDAO.registerAccount(account);
        }
        return null;
    }

    public Account logIn(Account account, String username, String password){

        accountDAO.userLogin(username, password);
        return account;
    }

}
