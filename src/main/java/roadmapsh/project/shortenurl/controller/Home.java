package roadmapsh.project.shortenurl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping("/")
    public String loginPage() {
        return "login"; // login.html
    }

    @GetMapping("/home")
    public String homePage() {
        return "index"; // index.html// looks for templates/index.html
    }
}

