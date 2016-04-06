package pool.demo1;


import org.apache.commons.pool.PoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyConnectionPoolableObjectFactory implements PoolableObjectFactory {

    private static Logger logger = LoggerFactory.getLogger(MyConnectionPoolableObjectFactory.class);

    private static int count = 0;

    public Object makeObject() throws Exception {
        MyConnection myConn = new MyConnection("conn_" + (++count));
        myConn.connect();
        System.out.println(myConn.getName());
        return myConn;
    }

    public void activateObject(Object obj) throws Exception {
        MyConnection myConn = (MyConnection) obj;
        System.out.println(myConn.getName());
    }

    public void passivateObject(Object obj) throws Exception {
        MyConnection myConn = (MyConnection) obj;
        System.out.println(myConn.getName());
    }

    public boolean validateObject(Object obj) {
        MyConnection myConn = (MyConnection) obj;
        System.out.println(myConn.getName());
        return myConn.isConnected();
    }

    public void destroyObject(Object obj) throws Exception {
        MyConnection myConn = (MyConnection) obj;
        System.out.println(myConn.getName());
        myConn.close();
    }
}
