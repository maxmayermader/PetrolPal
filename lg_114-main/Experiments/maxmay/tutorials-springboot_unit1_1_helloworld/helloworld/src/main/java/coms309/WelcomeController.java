package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello and welcome to COMS 309";
    }

    @GetMapping("/{name}/{major}")
    public String welcome(@PathVariable String name, @PathVariable String major) {
        return "Hello and welcome to COMS 309: " + name +" studies " + major;
    }

//    @PostMapping("/")
//    public String


//    @GetMapping("/{major}")
//    public String welcome(@PathVariable String major) {
//        return "Hello and welcome to COMS 309: " + major;
//    }
}
