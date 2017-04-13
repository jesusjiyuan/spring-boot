package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springboot.Uri;
import springboot.entity.Userinfo;
import springboot.repository.NewsResp;
import springboot.repository.UserinfoResp;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.sql.Timestamp;
import java.util.List;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 22:28
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
@Controller
@RequestMapping("/information")
public class Informarion {

    @Autowired
    UserinfoResp userinfoResp;
    @Autowired
    NewsResp newsResp;
    @Autowired
    Uri uri;

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public String log(Model model){
        //输入tomcat日志
        try {
            String time = new Timestamp(System.currentTimeMillis()).toString();
            String docname = "catalina." + time.substring(0,time.length()-13) + ".log";
            FileInputStream inputStream = new FileInputStream(uri.LOGURI + docname);
            FileChannel fileChannel = inputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder builder = new StringBuilder();
            while(fileChannel.read(buffer) != -1){
                buffer.flip();  //锁定空白区
                Charset charset = Charset.forName("GBK");
                CharsetDecoder decoder = charset.newDecoder();  //解码器
                CharBuffer charBuffer = decoder.decode(buffer); //转码
                builder.append(charBuffer);
                buffer.clear();
            }
            model.addAttribute("a",builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "information/log";
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public String person(ModelMap modelMap){
        List<Userinfo> userList = userinfoResp.findAll();
        modelMap.addAttribute("userList", userList);
        return "information/person";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(Model model){
        model.addAttribute("one",newsResp.findTitle(1));
        model.addAttribute("two",newsResp.findTitle(2));
        model.addAttribute("three",newsResp.findTitle(3));
        return "information/send";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(){

        return "information/upload";
    }
}
