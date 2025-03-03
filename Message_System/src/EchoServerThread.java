import java.io.*;
/**
 * This module is to be used with a concurrent Echo server.
 * Its run method carries out the logic of a client session.
 * @author M. L. Liu
 */

class EchoServerThread implements Runnable {
    static final String endMessage = ".";
    MyStreamSocket myDataSocket;

    EchoServerThread(MyStreamSocket myDataSocket) {
        this.myDataSocket = myDataSocket;
    }

    public void run() {
        boolean done = false;
        String message;

        try {
            while (!done) {
                message = myDataSocket.receiveMessage();

                if (message != null){
                    if (message.equals(endMessage)){
                        myDataSocket.close();
                        done = true;
                    }
                    else{
                        myDataSocket.sendMessage(message);
                        System.out.println("message received: " + message);
                    }
                }
            }
        }
        catch (Exception ex) {
           System.out.println("Exception caught in thread: " + ex);
        }
    }
}
