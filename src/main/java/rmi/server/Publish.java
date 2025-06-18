package rmi.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-10 17:21
 */
public class Publish {
    public static void main(String[] args)
            throws RemoteException, AlreadyBoundException, MalformedURLException {
        LocateRegistry.createRegistry(9999);

        Naming.bind("rmi://localhost:9999/say", new SayServiceImpl());
    }
}
