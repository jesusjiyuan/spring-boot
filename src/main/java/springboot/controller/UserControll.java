package springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.session.MySessionContext;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 16:20
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */

@Controller
public class UserControll {

    @RequestMapping(value = "/")
    public String init(@CookieValue(value = "JSESSIONID", defaultValue = "none") String fooCookie) {
        if(MySessionContext.getSession(fooCookie) != null) {
            return "redirect:index/main";
        }
        return "index";
    }
}
