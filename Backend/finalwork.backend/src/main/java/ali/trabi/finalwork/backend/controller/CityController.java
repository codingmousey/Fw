package ali.trabi.finalwork.backend.controller;

import ali.trabi.finalwork.backend.dao.CityDAO;
import ali.trabi.finalwork.backend.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/cities")
public class CityController {
    private final CityDAO cityDAO;

    @Autowired
    public CityController(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<City> getAllCities() {
        return cityDAO.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public City getCityById(@PathVariable Integer id) {
        return cityDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "couldnt find city"));
    }
}
