package rmi_test.server;

import rmi_test.SayService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-10 17:19
 */
public class SayServiceImpl extends UnicastRemoteObject implements SayService {


    protected SayServiceImpl() throws RemoteException {
    }

    @Override
    public String say(String msg) throws RemoteException {
        System.out.println("服务端收到消息：" + msg);
        return "hello," + msg + "!";
    }
}
