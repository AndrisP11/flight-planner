package io.codelex.flightplanner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/testing-api")
    public String getGreetingMessage() {
        return "Hello my fellow user!";
    }


}
