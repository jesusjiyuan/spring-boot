package springboot.json;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 09/04/2017 16:21
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
public class Local {

    public String resultcode;
    public String reason;
    public int error_code;
    public Result result;

    public class Result {

        public String area;
        public String location;

    }
}
