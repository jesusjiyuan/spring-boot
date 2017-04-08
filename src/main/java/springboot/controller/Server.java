package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.entity.Users;
import springboot.repository.UserinfoResp;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 21:12
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
@ServerEndpoint(value = "/chat/{loginName}")
public class Server {

    @Autowired
    UserinfoResp userinfoResp;

    /**
     * 开始连接websocket
     *
     * @param session
     * @param loginName 当前登录账号
     */
    @OnOpen
    public void open(Session session, @PathParam("loginName") String loginName) {
        Users.user.put(loginName, session);// 保存当前的对象，用于发送或接收消息
        for (Session session_1 : Users.user.values()) {
            try {
                session_1.getBasicRemote().sendText(String.valueOf(Users.user.size()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息
     * @param message 发送消息
     * @throws IOException
     */
    @OnMessage
    public void message(String message) throws IOException, InterruptedException {
        //Users.user.get("光头强").session.getBasicRemote().sendText(message);
    }

    /**
     * 关闭
     * @param session
     */
    @OnClose
    public void close(Session session) throws IOException {
        int count = Users.user.size();
        Users.del(session);
        try {
            Thread.sleep(5000);
            if (Users.user.size() != count && Users.user.size() != 0) {
                for (Session session_1 : Users.user.values()) {
                    session_1.getBasicRemote().sendText(String.valueOf(Users.user.size()));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 出错
     * @param t
     * @param session
     */
    @OnError
    public void error(Throwable t, Session session) {
        System.out.println("error");
    }
}
