package roadmapsh.project.shortenurl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping("/")
    public String index() {
        return "index"; // looks for templates/index.html
    }
}

