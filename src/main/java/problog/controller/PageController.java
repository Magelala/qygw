package problog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import problog.entity.carousel.Carousel;

import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@ApiIgnore
public class PageController {

//    @Autowired
//    private CarouselService carouselService;
//
//    @RequestMapping("/advertise")
//    public String advertise(Model model){
//        List<Carousel> list = carouselService.selectAllList();
//        model.addAttribute("lists",list);
//        return "advertisement";
//    }

    @GetMapping("/user")
    public String user(Model model){
        return "user";
    }

    @GetMapping("/setting")
    public String setting(Model model){
        return "setting";
    }
}
