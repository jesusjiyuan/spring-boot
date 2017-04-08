package springboot.session;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 16:20
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
public class MySessionContext {

    private static HashMap<String,HttpSession> mymap = new HashMap<>();   //设置一个静态的HashMap
    public static synchronized void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }
    public static synchronized void DelSession(String session_id) {
        if (session_id != null) {
            mymap.remove(session_id);
        }
    }
    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null) {
            return null;
        }
        return mymap.get(session_id);
    }
}
