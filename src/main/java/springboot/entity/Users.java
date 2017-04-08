package springboot.entity;

import javax.websocket.Session;
import java.util.*;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 21:13
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
public class Users {

    public static Map<String, Session> user = new HashMap<>();

    private static Collection<Session> col = user.values();

    public static List<String> name(){
        Set<String> i = user.keySet();
        List<String> list = new ArrayList<>(i);
        return list;
    }

    public static void del(Session session){
        col.remove(session);
    }
}
