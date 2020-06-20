package rmi_test.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-10 17:15
 */
public interface SayService extends Remote {
    String say(String msg) throws RemoteException;
}
