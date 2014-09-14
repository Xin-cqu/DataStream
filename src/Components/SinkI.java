package Components;

import Components.Streams.*;
import Ice.*;
import javafx.application.Application;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class SinkI extends _SinkDisp implements SinkContext {
    public static ObjectAdapter _adapter;
    private SinkPort port;
    private Socket s;
    private OctetStreamBuffer buffer;
    private TransportProfile transportProfile;
    private StreamProfile streamProfile;
    private boolean enableNewBuffer;
    private boolean status;
    private DataInputStream inputStream;
    private int offset;
    private int length;
    public SinkI(SinkPort sinkPort){
        this.port = sinkPort;
        buffer = new OctetStreamBufferI();
    }

    @Override
    public void finalizeTransport(boolean now, Source theSource, TransportProfileHolder tranPro, Current __current) throws GenericError {

    }

    @Override
    public void modifyTransport(TransportProfile tpformodify, Current __current) throws GenericError {

    }

    @Override
    public void releaseTransport(Current __current) {

    }

    @Override
    public void startStream(PreviouAction previous, byte[] theMarker, StreamProfile sp, TransportProfile tp, Current __current) throws GenericError {
        //

        this.streamProfile = sp;
        this.transportProfile =tp;
        String []info = tp.properties.split(",");
        if(enableNewBuffer){
            new TCPClient(streamProfile,info[2],info[3]).start();
            this.port.started(sp);

        }else
        {
            try {
                s = new Socket(info[2],Integer.parseInt(info[3]));
                inputStream = new DataInputStream(s.getInputStream());
                this.port.started(sp);
                this.length = Integer.parseInt(streamProfile.properties.split(",")[1]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class TCPClient extends Thread{
        private String ip;
        private String ipPort;
        private int length;
        private int offset;
        TCPClient(StreamProfile streamProfile,String ip, String port) {
            this.ip = ip;
            this.ipPort = port;
            this.length = Integer.parseInt(streamProfile.properties.split(",")[1]);
        }

        @Override
        public void run() {
            try {

                status =true;
                    s = new Socket(ip,Integer.parseInt(this.ipPort));
                    DataInputStream inputStream = new DataInputStream(s.getInputStream());
                    while (true){
                        int size = inputStream.read(buffer.getData());
                        offset+=size;
                        if(offset >=this.length){
                            buffer = new OctetStreamBufferI(Arrays.copyOf(buffer.getData(),buffer.getData().length+this.length -offset));
                            port.newBuffer(buffer);
                            port.finished();
                            break;
                         }
                    port.newBuffer(buffer);
                }
                s.close();
                status = false;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GenericError genericError) {
                genericError.printStackTrace();
            }
        }
    }
    class ICEServer extends Ice.Application implements Runnable{
        private SinkI sinkI;
        public ICEServer(SinkI sinkI){
            this.sinkI = sinkI;
        }
        @Override
        public int run(String[] args) {
            //int status = 0;
            Ice.Communicator ic = null;
            try {
                //初使化连接，args可以传一些初使化参数，如连接超时时间，初使化客户连接池的数量等
                ic = Ice.Util.initialize(args);
                //创建名适配器，并要求适配器使用缺省的协议(TCP/IP侦听端口为10000的请求)
                Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
                        "SendFile", "default -p 10000");
                //实例化一个对象，为接口创建一个服务对象

                SinkI._adapter = adapter;
//                FileManagementImpl._adapter = adapter;
                Ice.Object object = sinkI;


                //将服务单元增加到适配器中，并给服务对象指定名称为S1，该名称用于唯一确定一个服务单元
                adapter.add(object, Ice.Util.stringToIdentity("S1"));
                //adapter.add(object1,Ice.Util.stringToIdentity("S2"));
                //adapter.add(object1,Ice.Util.stringToIdentity("SimpleExchange1"));
                //激活适配器，这样做的好处是可以等到所有资源就位后再触发
                adapter.activate();
                //让服务在退出之前，一直持续对请求的监听
                System.out.println("Communicator is waiting for shutdown.");
                ic.waitForShutdown();
                System.out.println("Communicator is shutdown.");
                // Thread.sleep(30000);
            } catch (Ice.LocalException e) {
                e.printStackTrace();
                // status = 1;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (ic != null)
                    ic.destroy();
            }
            return 0;
        }

        @Override
        public void run() {
            this.run(null);
        }
    }
    @Override
    public void finishStream(byte[] markerForFinish, Current __current) throws GenericError {
//        try {
//            if(status) {
//                s.close();
//                port.finished();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void abortStream(byte[] markerForAbort, Current __current) throws GenericError {
        if(status) {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            port.aborted();
        }
    }

    @Override
    public TransportProfile getTransportProfile() {
        return this.transportProfile;
    }

    @Override
    public StreamProfile getStreamProfile() {
        return this.streamProfile;
    }

    @Override
    public void abort() {
        //
    }

    @Override
    public void setEnableNewBuffer(boolean enableNewBuffer) {
        this.enableNewBuffer = enableNewBuffer;

        //ICEServer server = new ICEServer(this);
       // new Thread().start();
    }

    @Override
    public boolean getEnableNewBuffer() {
        return enableNewBuffer;
    }

    @Override
    public void setBufferTimeout(long timeout) {

    }

    @Override
    public long getBufferTimeout() {
        return 0;
    }

    @Override
    public StreamBuffer getBuffer(long minimumSize, boolean _wait) throws GenericError {
        int size = 0;
        try {
            size = inputStream.read(buffer.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }

        offset+=size;
        if(offset >=this.length){
            buffer = new OctetStreamBufferI(Arrays.copyOf(buffer.getData(),buffer.getData().length+this.length -offset));
            buffer.setLast(true);
        }
        return buffer;

    }
}
