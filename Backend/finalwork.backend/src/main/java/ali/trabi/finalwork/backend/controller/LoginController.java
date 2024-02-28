package ali.trabi.finalwork.backend.controller;

import ali.trabi.finalwork.backend.dao.LoginDAO;
import ali.trabi.finalwork.backend.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.List;

@RestController
public class LoginController {

    private final LoginDAO db;

    @Autowired
    public LoginController(LoginDAO db) {
        this.db = db;
    }

    @GetMapping("/login")
    public String login() {
        return "Login API call success";
    }

    @GetMapping("/getLogins")
    public List<Login> getAllLogins() {
        return db.findAll();
    }

    @GetMapping("/login/{id}")
    public Login getLoginById(@PathVariable Integer id) {
        Login login = db.findById(id);
        return (Login) Objects.requireNonNullElse(login, "login with specific id not found");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login/add")
    public void createLogin(@RequestBody Login newLogin) {
        db.create(newLogin);
    }

    @PutMapping("/login/update/{id}")
    public void updateLogin(@RequestBody Login updatedLogin, @PathVariable Integer id) {
        Login l = db.findById(id);
        if (l != null) {
            // Update the existing login with the updated login details
            l.setEmail(updatedLogin.getEmail());
            l.setUsername(updatedLogin.getUsername());
            l.setUnsafePw(updatedLogin.getUnsafePw());
            l.setAccountType(updatedLogin.getAccountType());
            db.update(l);
        }
    }

    @DeleteMapping("login/delete/{id}")
    public void deleteLogin(@PathVariable Integer id){
        db.delete(id);
    }
}
