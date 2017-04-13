package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springboot.entity.Comefrom;
import springboot.repository.ComefromResp;

import java.util.List;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 09/04/2017 17:35
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */

@Configuration
public class SaveLocal {

    @Autowired
    ComefromResp comefromResp;

    public void save(Comefrom comefrom){
        comefromResp.saveAndFlush(comefrom);
    }

    public void constr(String ip,String add,String client,String version,String time){
        Comefrom comefrom = new Comefrom();
        comefrom.setIp(ip);
        comefrom.setAddress(add);
        comefrom.setClient(client);
        comefrom.setVersion(version);
        comefrom.setTime(time);
        this.save(comefrom);
    }

    public List<Comefrom> getCom(){
        List<Comefrom> List = comefromResp.findAllByOrderByTimeDesc();
        return List;
    }
}
