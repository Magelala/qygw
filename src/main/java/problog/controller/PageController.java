package problog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import problog.service.CarouselService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@ApiIgnore
public class PageController {

    @GetMapping("/user")
    public String user(Model model){
        return "user";
    }

    @GetMapping("/setting")
    public String setting(Model model){
        return "setting";
    }

}
