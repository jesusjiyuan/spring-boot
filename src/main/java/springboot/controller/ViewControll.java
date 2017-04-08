package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springboot.entity.Salary;
import springboot.repository.InformResp;
import springboot.repository.NewsResp;
import springboot.repository.SalaryResp;
import springboot.session.MySessionContext;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 18:54
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
@Controller
@RequestMapping("/index")
public class ViewControll {

    @Autowired
    NewsResp newsResp;
    @Autowired
    InformResp informResp;
    @Autowired
    SalaryResp salaryResp;

    @RequestMapping("/main")
    public String mainview(ModelMap model, @CookieValue("JSESSIONID") String fooCookie){
        HttpSession session = MySessionContext.getSession(fooCookie);
        if(session == null){
            return "redirect:/";
        }
        model.addAttribute("name",session.getAttribute("name"));
        model.addAttribute("id",session.getAttribute("id"));
        return "/admin/detailview";
    }

    @RequestMapping(value = "/view/one", method = RequestMethod.POST)
    public String one(Model model, @CookieValue("JSESSIONID") String fooCookie){
        HttpSession session = MySessionContext.getSession(fooCookie);
        if(session == null){
            return null;
        }
        Map<String,String> map = new HashMap<>();
        map.put("message",newsResp.findTitle(1));
        map.put("message1",newsResp.findTitle(2));
        map.put("message2",newsResp.findTitle(3));
        map.put("info",informResp.findMessage(1));
        model.addAllAttributes(map);
        return "admin/mainview";
    }

    @RequestMapping(value = "/view/two", method = RequestMethod.POST)
    public String two(@CookieValue("JSESSIONID") String fooCookie){
        HttpSession session = MySessionContext.getSession(fooCookie);
        if(session == null){
            return null;
        }
        return "admin/emailview";
    }

    @RequestMapping(value = "view/son/information", method = RequestMethod.POST)
    public String information(@CookieValue("JSESSIONID") String fooCookie){
        HttpSession session = MySessionContext.getSession(fooCookie);
        if(session == null){
            return null;
        }
        return "admin/information";
    }

    @RequestMapping(value = "view/son/entity", method = RequestMethod.POST)
    public String entity(@CookieValue("JSESSIONID") String fooCookie, ModelMap modelMap){
        HttpSession session = MySessionContext.getSession(fooCookie);
        if(session == null){
            return null;
        }
        List<Salary> salaryList = salaryResp.findAll();
        modelMap.addAttribute("salaryList", salaryList);
        return "admin/entityview";
    }

    @RequestMapping("/newsDetail/{id}")
    public String newsDetail(@CookieValue("JSESSIONID") String fooCookie, @PathVariable String id, Model model){
        HttpSession session = MySessionContext.getSession(fooCookie);
        if(session == null){
            return "redirect:/";
        }
        model.addAttribute("content",newsResp.findOne(Long.parseLong(id)));
        return "admin/newsview";
    }

}
