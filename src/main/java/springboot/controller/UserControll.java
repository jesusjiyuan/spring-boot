package springboot.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import springboot.entity.Comefrom;
import springboot.json.Local;
import springboot.session.MySessionContext;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 16:20
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */

@Controller
public class UserControll {

    @Autowired
    SaveLocal saveLocal;

    @RequestMapping(value = "/web")
    public String init(@CookieValue(value = "JSESSIONID", defaultValue = "none") String fooCookie) {
        if (MySessionContext.getSession(fooCookie) != null) {
            return "redirect:index/main";
        }
        return "index";
    }

    @RequestMapping(value = "/")
    public String mainview(HttpServletRequest request, ModelMap modelMap) {
        Pattern pattern = Pattern.compile("Mozilla.*?\\)");
        Matcher matcher = pattern.matcher(request.getHeader("User-Agent"));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        HttpHeaders headers = new HttpHeaders();
        headers.add("ip", request.getRemoteAddr());
        headers.add("key", "af18a9d69ae1cbfb8d4a2d2bb497fe72");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(
                "http://apis.juhe.cn/ip/ip2addr?ip={ip}&key={key}",
                HttpMethod.GET,
                entity,
                String.class,
                request.getRemoteAddr(),
                "af18a9d69ae1cbfb8d4a2d2bb497fe72");
        Local local = new Gson().fromJson(result.getBody(), Local.class);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        String time = d.toString();
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            if (matcher.group(0).contains("Windows Phone")) {
                String str = matcher.group(0).substring(13);
                Pattern windows = Pattern.compile("[0-9]+.[0-9]+");
                Matcher windows1 = windows.matcher(str);
                if (windows1.find()) {
                    saveLocal.constr(
                            request.getRemoteAddr(),
                            local.result.area + " " + local.result.location,
                            "Windows Phone",
                            windows1.group(0),
                            time);
                }
            } else if (matcher.group(0).contains("Windows")) {
                String str = matcher.group(0).substring(13);
                Pattern windows = Pattern.compile("[0-9]+.[0-9]+");
                Matcher windows1 = windows.matcher(str);
                if (windows1.find()) {
                    saveLocal.constr(
                            request.getRemoteAddr(),
                            local.result.area + " " + local.result.location,
                            "Windows",
                            windows1.group(0),
                            time);
                }
            } else if (matcher.group(0).contains("iPhone")) {
                modelMap.addAttribute("sig", "phone");
                Pattern iphone = Pattern.compile("[0-9]+_[0-9]+_?[0-9]?");
                Matcher iphone1 = iphone.matcher(matcher.group(0));
                if (iphone1.find()) {
                    saveLocal.constr(
                            request.getRemoteAddr(),
                            local.result.area + " " + local.result.location,
                            "iPhone",
                            iphone1.group(0).replaceAll("_","."),
                            time);
                }
            } else if (matcher.group(0).contains("iPad")) {
                Pattern ipad = Pattern.compile("[0-9]+_[0-9]+_?[0-9]?");
                Matcher ipad1 = ipad.matcher(matcher.group(0));
                if (ipad1.find()) {
                    saveLocal.constr(
                            request.getRemoteAddr(),
                            local.result.area + " " + local.result.location,
                            "iPad",
                            ipad1.group(0).replaceAll("_","."),
                            time);
                }
            } else if (matcher.group(0).contains("Mac OS X")) {
                Pattern mac = Pattern.compile("[0-9]+_[0-9]+_?[0-9]?");
                Matcher mac1 = mac.matcher(matcher.group(0));
                if (mac1.find()) {
                    saveLocal.constr(
                            request.getRemoteAddr(),
                            local.result.area + " " + local.result.location,
                            "Mac",
                            mac1.group(0).replaceAll("_","."),
                            time);
                }
            } else if (matcher.group(0).contains("android")) {
                modelMap.addAttribute("sig", "phone");
                System.out.println(matcher.group(0));
                System.out.println("android");
            }
        }
        return "mainindex";
    }

    @RequestMapping(value = "log")
    public String log(ModelMap modelMap, HttpServletRequest request) {
        List<Comefrom> list = saveLocal.getCom();
        modelMap.addAttribute("List", list);
        modelMap.addAttribute("ip", request.getRemoteAddr());
        return "log";
    }

    @RequestMapping(value = "download")
    public String download() {
        return "download";
    }
}
