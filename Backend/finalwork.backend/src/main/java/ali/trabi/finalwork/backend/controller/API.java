package ali.trabi.finalwork.backend.controller;

import ali.trabi.finalwork.backend.dao.JobListingDAO;
import ali.trabi.finalwork.backend.dao.UserDAO;
import ali.trabi.finalwork.backend.entity.JobListing;
import ali.trabi.finalwork.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class API {

    // alle DAO's
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JobListingDAO jobListingDAO;

    @Autowired
    public API(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder, JobListingDAO jobListingDAO) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.jobListingDAO = jobListingDAO;
    }

    // registering a new user
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> registerData) {
        System.out.println("register request: " + registerData);
        String firstName = registerData.get("firstName");
        String lastName = registerData.get("lastName");
        String username = registerData.get("username");
        String email = registerData.get("email");
        String pw = registerData.get("pw");
        String pwCheck = registerData.get("pwCheck");

        Map<String, String> responseData = new HashMap<>();
        System.out.println("Response data: " + responseData);

        if (email == null || email.isEmpty()) {
            responseData.put("error", "email cannot be empty");
        } else if (pw == null || pw.isEmpty()) {
            responseData.put("error", "password cannot be empty");
        } else if (pwCheck == null || pwCheck.isEmpty()) {
            responseData.put("error", "password check cannot be empty");
        } else if (!pw.equals(pwCheck)) {
            responseData.put("error", "passwords do not match");
        } else if (firstName == null || firstName.isEmpty()) {
            responseData.put("error", "first name cannot be empty");
        } else if (lastName == null || lastName.isEmpty()) {
            responseData.put("error", "last name cannot be empty");
        } else if (username == null || username.isEmpty()) {
            responseData.put("error", "username cannot be empty");
        } else if (username.length() < 4) {
            responseData.put("error", "username must be at least 4 characters long");
        } else {
            String encryptedPw = passwordEncoder.encode(pw);
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(email);
            user.setPasswordHash(encryptedPw);
            userDAO.save(user);
            responseData.put("message", "registration successful");
            return ResponseEntity.ok(responseData);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    // login a user
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
        System.out.println("login request: " + loginData);
        String username = loginData.get("username");
        String pw = loginData.get("pw");
        Map<String, String> responseData = new HashMap<>();

        User user = userDAO.getUserByUsername(username);
        System.out.println(user);

        if (user != null) {
            System.out.println("pw hash in db: " + user.getPasswordHash());
            System.out.println("pw van request hashed: " + passwordEncoder.encode(pw));

            if (passwordEncoder.matches(pw, user.getPasswordHash())) {
                responseData.put("message", "login successful");
                responseData.put("id", String.valueOf(user.getId()));
                responseData.put("username", user.getUsername());
                responseData.put("email", user.getEmail());
                responseData.put("role", user.getRole());
                System.out.println("responseData: " + responseData);
                return ResponseEntity.ok(responseData);
            }
        }
        responseData.put("error", "invalid username or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
    }


    // get all joblistings
    @GetMapping("/joblistings")
    @ResponseBody
    public Iterable<JobListing> findAllLJobListings() {
        return jobListingDAO.findAll();
    }

    @GetMapping("/langById/{id}")
    @ResponseBody
    public Iterable<String> findLangById(@PathVariable Integer id) {
        return jobListingDAO.findProgrammingLanguagesByCompanyId(id);
    }

    // add a new job listing
    @PostMapping("/add_joblisting")
    @ResponseBody
    public HttpStatus insertJobListing(@RequestBody JobListing jobListing) {
        System.out.println("add joblisting api call called");
        System.out.println("joblisting : " + jobListing);
        Integer companyId = jobListing.getCompanyId();
        System.out.println("companyID: " + companyId);

        // Set the extracted company ID in the JobListing object
        jobListing.setCompanyId(companyId);
        System.out.println("joblisting : " + jobListing);
        jobListing.setCreatedDate(LocalDateTime.now());

        jobListingDAO.save(jobListing);
        return HttpStatus.CREATED;
    }

    // get all prefs from a user
    @GetMapping("/prefs")
    @ResponseBody
    public ResponseEntity<List<String>> getAllPreferences(@RequestHeader("userId") Integer userId) {
        System.out.println("api called to get preferences for user with id: ' + userId");
        User user = userDAO.getUserById(userId);
        if (user != null) {
            List<String> preferences = user.getPreferences();
            System.out.println("prefs sent success");
            return ResponseEntity.ok(preferences);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // add a new pref for a user
    @PostMapping("/prefs")
    @ResponseBody
    public ResponseEntity<String> insertPreference(@RequestBody Map<String, String> prefData) {
        Integer userId = Integer.parseInt(prefData.get("userId"));
        System.out.println("api called to post a new pref for user with id: ' + userId");
        String pref = prefData.get("preference");
        User user = userDAO.getUserById(userId);
        if (user != null) {
            List<String> preferences = user.getPreferences();
            preferences.add(pref);
            user.setPreferences(preferences);
            userDAO.save(user);
            System.out.println("pref added success");
            return ResponseEntity.ok("pref added success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // delete pref from a user
    @DeleteMapping("/prefs")
    @ResponseBody
    public ResponseEntity<String> deletePreference(@RequestBody Map<String, String> prefData) {
        Integer userId = Integer.parseInt(prefData.get("userId"));
        String pref = prefData.get("preference");
        System.out.println("api called to delete pref: " + pref + " for user with id: " + userId);
        User user = userDAO.getUserById(userId);
        if (user != null) {
            List<String> preferences = user.getPreferences();
            if (preferences.contains(pref)) {
                preferences.remove(pref);
                user.setPreferences(preferences);
                userDAO.save(user);
                System.out.println("pref deleted success");
                return ResponseEntity.ok("pref deleted success");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
