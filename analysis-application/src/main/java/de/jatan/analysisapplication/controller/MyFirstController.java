package de.jatan.analysisapplication.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de.jatan.analysisapplication.services.*;

@RestController
public class MyFirstController {

    @Autowired
    private MyFirstService myFirstService;

    @GetMapping
    public HelloDto hello(@RequestParam(required = false) String user) {
        return myFirstService.hello(user);
    }

}
