import java.rmi.*;
import java.util.*;

public interface QueryInterface extends Remote{
	public String queryClassRoster(String x) throws RemoteException;
}
