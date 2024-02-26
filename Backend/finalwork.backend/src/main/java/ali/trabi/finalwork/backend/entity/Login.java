package ali.trabi.finalwork.backend.entity;

import java.time.LocalDateTime;

public class Login {
    Integer id;
    String email;
    String username;
    String unsafePw;
    LocalDateTime lastLogin;
    AccountType accountType;

    public Integer getId() {
        return id;
    }

}
