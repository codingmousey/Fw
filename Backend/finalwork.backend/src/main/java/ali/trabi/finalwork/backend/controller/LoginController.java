package ali.trabi.finalwork.backend.controller;

import ali.trabi.finalwork.backend.dao.LoginDAO;
import ali.trabi.finalwork.backend.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {
    private final LoginDAO loginDAO;

    @Autowired
    public LoginController(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Login> findAllLogins() {
        return loginDAO.findAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public HttpStatus insertLogin(@RequestBody Login login) {

        loginDAO.save(login);
        return HttpStatus.CREATED;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public HttpStatus updateLogin(@RequestBody Login updatedLogin, @PathVariable Integer id) {
        Optional<Login> exists = loginDAO.findById(id);
        if (exists.isPresent()) {
            Login l = exists.get();
            l.setEmail(updatedLogin.getEmail());
            l.setUsername(updatedLogin.getUsername());
            l.setUnsafePw(updatedLogin.getUnsafePw());
            l.setLastLogin(updatedLogin.getLastLogin());
            l.setAccountType(updatedLogin.getAccountType());
            loginDAO.save(l);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public HttpStatus deleteLogin(@PathVariable Integer id) {
        loginDAO.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, String> register(@RequestBody Map<String, String> registerData) {
        String username = registerData.get("username");
        String pw = registerData.get("pw");
        String pwCheck = registerData.get("pwCheck");

        Map<String, String> responseData = new HashMap<>();
        if (username == null || username.length() < 10) {
            responseData.put("error", "username must be at least 10 characters long");
        } else if (pw == null || pw.isEmpty()) {
            responseData.put("error", "pw cant be empty");
        } else if (!pw.equals(pwCheck)) {
            responseData.put("error", "pws arent the same");
        } else {
            // Process registration
            responseData.put("message", "Registration successful");
        }
        return responseData;
    }
}
