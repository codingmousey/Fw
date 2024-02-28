package ali.trabi.finalwork.backend.dao;

import ali.trabi.finalwork.backend.entity.AccountType;
import ali.trabi.finalwork.backend.entity.Login;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LoginDAO {
    private final List<Login> loginLst = new ArrayList<>();

    public LoginDAO() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        loginLst.add(new Login(1, "basicUser@ehb.com", "basicUser", "pw", currentDateTime, AccountType.BASICUSER));
        loginLst.add(new Login(2, "verifiedUser@ehb.com", "verifiedUser", "pw", currentDateTime, AccountType.VERIFIEDUSER));
        loginLst.add(new Login(3, "admin@ehb.com", "admin", "admin", currentDateTime, AccountType.ADMIN));
    }

    public List<Login> findAll() {
        return loginLst;
    }

    public Login findById(Integer id) {
        for (Login login : loginLst) {
            if (login.getId().equals(id)) {
                return login;
            }
        }
        return null;
    }

    public void create(Login l){
        LocalDateTime currentDateTime = LocalDateTime.now();
        l.setId(loginLst.size() + 1);
        l.setLastLogin(currentDateTime);
        loginLst.add(l);
    }
}
