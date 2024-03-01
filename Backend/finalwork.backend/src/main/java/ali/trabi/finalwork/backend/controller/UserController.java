package ali.trabi.finalwork.backend.controller;

import ali.trabi.finalwork.backend.dao.UserDAO;
import ali.trabi.finalwork.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<User> findAllUsers() {
        return userDAO.findAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public HttpStatus addUser(@RequestBody User user) {
        userDAO.save(user);
        return HttpStatus.CREATED;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public HttpStatus updateUser(@RequestBody User updatedUser, @PathVariable Integer id) {
        if (userDAO.existsById(id)) {
            updatedUser.setId(id);
            userDAO.save(updatedUser);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public HttpStatus deleteUser(@PathVariable Integer id) {
        userDAO.deleteById(id);
        return HttpStatus.OK;
    }
}
