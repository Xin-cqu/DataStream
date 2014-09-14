package Components.Client;

import Components.SourceI;
import Components.Streams.*;
import Components.TransportProfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by xinwen on 28/08/2014.
 */
public class SourcePortI4Block implements SourcePort {
    private StreamProfile streamProfile;
    private TransportProfile transportProfile;
    private RandomAccessFile inputStream;
    private SourceContext context;
    private int offset;
    public SourcePortI4Block(File fi,String remoteIp) throws InterruptedException, GenericError {

        this.transportProfile = new TransportProfile();
        this.transportProfile.tag="TCP";
        this.streamProfile = new StreamProfile();
        context = new SourceI(this);
        context.setEnableNewBuffer(false);
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
        try {
            context.start(streamProfile,transportProfile);
        } catch (GenericError genericError) {
            genericError.printStackTrace();
        }
        startLoop();
    }
    private void startLoop() throws GenericError, InterruptedException {
        while(true){
            StreamBuffer theBuffer = context.getBuffer(1,false);
            if(theBuffer == null){ Thread.sleep(1);continue;}
            if(theBuffer.getHasData()){
                Thread.sleep(1);
                continue;
            }
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
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    public void newBuffer(StreamBuffer theBuffer) {

    }

    @Override
    public void aborted() {

    }

    @Override
    public void failed() {

    }
}
