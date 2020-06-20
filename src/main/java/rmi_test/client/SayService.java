package rmi_test.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-10 17:24
 */
public interface SayService extends Remote {
    String say(String msg) throws RemoteException;
}
