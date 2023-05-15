package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    //@Slf4j와 같은 역할 == 로그 찍는 기능
//    Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }
}
