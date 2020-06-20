package rmi_test.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-10 17:25
 */
public class SayServiceTest {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        SayService service = (SayService) Naming.lookup("rmi://localhost:9999/say");

        System.out.println(service.say("world"));
    }
}
