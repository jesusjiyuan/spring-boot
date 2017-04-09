package springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 09/04/2017 21:32
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */

@Controller
public class ErrorControll {

    @RequestMapping(value = "/404")
    public String notFind(){
        return "404";
    }

    @RequestMapping(value = "/500")
    public String inError(){
        return "500";
    }

}
