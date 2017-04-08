package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.repository.NewsResp;
import springboot.session.MySessionContext;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 21:14
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
@Controller
@RequestMapping("/view")
public class NewsChange {

    @Autowired
    NewsResp newsResp;

    @RequestMapping("/{id}")
    public String change(@CookieValue("JSESSIONID") String cookie, @PathVariable String id, Model model){
        HttpSession session = MySessionContext.getSession(cookie);
        if(session == null){
            return null;
        }
        long Id = Long.parseLong(id);
        model.addAttribute("old",newsResp.findOne(Id));
        return "information/change";
    }

    @RequestMapping(value = "upload/{which}", method = RequestMethod.POST)
    public String upload(@RequestParam(value="image",required=false) MultipartFile image, @PathVariable String which){
        try {
            String url = "/newsPhoto/" + which + ".jpg";
            File file = new File(url);
            image.transferTo(file);
            return "redirect:/index/main";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
