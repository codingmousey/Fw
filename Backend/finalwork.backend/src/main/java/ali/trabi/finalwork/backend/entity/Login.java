package ali.trabi.finalwork.backend.entity;

import java.time.LocalDateTime;

public class Login {
    Integer id;
    String email;
    String username;

    public Login(Integer id, String email, String username, String unsafePw, LocalDateTime lastLogin, AccountType accountType) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.unsafePw = unsafePw;
        this.lastLogin = lastLogin;
        this.accountType = accountType;
    }

    String unsafePw;
    LocalDateTime lastLogin;
    AccountType accountType;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnsafePw() {
        return unsafePw;
    }

    public void setUnsafePw(String unsafePw) {
        this.unsafePw = unsafePw;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getId() {
        return id;
    }

}
