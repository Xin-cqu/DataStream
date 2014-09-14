package Components;

import Components.Streams.*;
import Ice.Current;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class SourceI extends _SourceDisp implements SourceContext {
    private final byte[] marker;
    private final SourcePort sourcePort;
//    private final OctetStreamBufferI buffer;
    private boolean enableNewBuffer;
    private Socket s;
    private TransportProfile transportProfile;
    private StreamProfile streamProfile;
    private OctetStreamBuffer buffer;
    private SinkPrx sink;
    private boolean status;
    private DataOutputStream ps;
    public SourceI(SourcePort port){
        this.sourcePort = port;
        UUID uuid = UUID.randomUUID();
        marker = uuid.toString().getBytes();
        this.buffer = new OctetStreamBufferI();
    }
    @Override
    public void start(StreamProfile steamPro4S, TransportProfile transportPro4S) throws GenericError {
        this.streamProfile = steamPro4S;
        this.transportProfile = transportPro4S;
        String method = transportPro4S.tag;
        if(!method.equalsIgnoreCase("TCP")) throw new IllegalStateException();
        //get ip and port
        if(enableNewBuffer){
            new StartService(steamPro4S,transportPro4S).start();
        }else {
            String [] ipPort = transportProfile.properties.split(",");
            String ip = ipPort[0];
            String port = ipPort[1];
            String localIp = ipPort[2];
            String localPort = ipPort[3];
            Ice.Communicator communicator = null;
            communicator = Ice.Util.initialize();
            Ice.ObjectPrx base = communicator.stringToProxy("S1: default -h "+ip+" -p "+port);
            sink = SinkPrxHelper.checkedCast(base);
            try {
                new TCPService4Block(streamProfile,localPort).start();
                sink.startStream(PreviouAction.NoAction, marker, streamProfile, transportProfile);
            } catch (GenericError genericError) {
                genericError.printStackTrace();
            }
        }
    }
    class TCPService4Block extends Thread{
        private String port;
        private StreamProfile profile;
        public TCPService4Block(StreamProfile profile,String port){
            this.port = port;
            this.profile = profile;
        }
        @Override
        public void run() {
            try {
                ServerSocket ss = new ServerSocket(Integer.parseInt(this.port));
                s =ss.accept();
                ps = new DataOutputStream(s.getOutputStream());
                while (true) {
                    if(buffer.getHasData()){
                        //sourcePort.newBuffer(buffer);
                        if (!buffer.getHasData()) {
                            break;
                        }
                        buffer.setHasData(false);

                        ps.write(buffer.getData());
                        if(buffer.getLast()){
                            break;
                        }
                    }else {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ps.flush();
                ps.close();
                try {
                    finish();
                } catch (GenericError genericError) {
                    genericError.printStackTrace();
                }
                System.out.print("exit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class StartService extends Thread{
        private StreamProfile streamProfile;
        private TransportProfile transportProfile;
        public StartService(StreamProfile streamProfile,TransportProfile transportProfile){
            this.streamProfile = streamProfile;
            this.transportProfile = transportProfile;
        }

        /**
         * call startStream method on SinkI and start TCP Server wait for transfer file
         */
        @Override
        public void run() {
            String [] ipPort = transportProfile.properties.split(",");
            String ip = ipPort[0];
            String port = ipPort[1];
            String localIp = ipPort[2];
            String localPort = ipPort[3];
            Ice.Communicator communicator = null;
            communicator = Ice.Util.initialize();
            Ice.ObjectPrx base = communicator.stringToProxy("S1: default -h "+ip+" -p "+port);
            sink = SinkPrxHelper.checkedCast(base);
            try {
                new TCPService(streamProfile,localPort).start();
                sink.startStream(PreviouAction.NoAction,marker,streamProfile,transportProfile);
            } catch (GenericError genericError) {
                genericError.printStackTrace();
            }
        }
    }
    class TCPService extends Thread{
        private String port;
        private StreamProfile profile;
        public TCPService(StreamProfile profile,String port){
            this.port = port;
            this.profile = profile;
        }
        @Override
        public void run() {
            try {
                ServerSocket ss = new ServerSocket(Integer.parseInt(this.port));
                s =ss.accept();
                ps = new DataOutputStream(s.getOutputStream());
                while (true) {
                    sourcePort.newBuffer(buffer);
                    if (!buffer.getHasData()) {
                        break;
                    }
                    ps.write(buffer.getData());
                    if(buffer.getLast()){
                        break;
                    }
                }
                ps.flush();
                ps.close();
                try {
                    finish();
                } catch (GenericError genericError) {
                    genericError.printStackTrace();
                }
                System.out.print("exit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void finish() throws GenericError {
        try {
            sink.finishStream(marker);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendBuffer(StreamBuffer buffer, boolean release) {

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
        try {
            sink.abortStream(marker);
        } catch (GenericError genericError) {
            genericError.printStackTrace();
        }
    }

    @Override
    public void setEnableNewBuffer(boolean enableNewBuffer) {
        this.enableNewBuffer = enableNewBuffer;
    }

    @Override
    public boolean getEnableNewBuffer() {
        return false;
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
        if(!buffer.getHasData()){
            return buffer;
        }else {
            return null;
        }
    }

    @Override
    public byte[] abortStream(Current __current) {
        return new byte[0];
    }

    @Override
    public void modifyTransport(TransportProfile theProfile, Current __current) throws GenericError {

    }
}
