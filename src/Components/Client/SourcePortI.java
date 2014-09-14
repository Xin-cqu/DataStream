package Components.Client;

import Components.OctetStreamBufferI;
import Components.SourceI;
import Components.Streams.*;
import Components.TransportProfile;

import java.io.*;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by xinwen on 27/08/2014.
 */
public class SourcePortI implements SourcePort {
    private StreamProfile streamProfile;
    private TransportProfile transportProfile;
    private RandomAccessFile inputStream;
    private SourceContext context;
    private int offset;
    public SourcePortI(File fi,String remoteIp){
        this.transportProfile = new TransportProfile();
        this.transportProfile.tag="TCP";
        this.streamProfile = new StreamProfile();
        context = new SourceI(this);
        context.setEnableNewBuffer(true);
        StringBuilder builder = new StringBuilder();
        builder.append(remoteIp).append(",").append("10000").append(",");
        try {
            builder.append(Inet4Address.getLocalHost().getHostAddress()).append(",").append("9000");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        transportProfile.properties = builder.toString();
        builder = new StringBuilder();
        builder.append(fi.getName()).append(",").append(fi.length());
        streamProfile.properties = builder.toString();
        try {
             this.inputStream = new RandomAccessFile(fi,"r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void start() throws GenericError {
        context.start(this.streamProfile,this.transportProfile);
    }
    @Override
    public void newBuffer(StreamBuffer theBuffer) {
        OctetStreamBuffer buffer = (OctetStreamBuffer)theBuffer;
        try {
            inputStream.seek(offset);
            int length  = inputStream.read(buffer.getData());
            offset+=length;
            if(length!=0){
                buffer.setHasData(true);
            }
            if(offset >=inputStream.length()){

                buffer.setLast(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void aborted() {

    }

    @Override
    public void failed() {

    }
}
