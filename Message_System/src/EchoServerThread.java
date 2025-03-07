import java.io.*;


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
                        System.out.println(message);
                    }
                }
            }
        }
        catch (Exception ex) {
           System.out.println("Exception caught in thread: " + ex);
        }
    }
}
