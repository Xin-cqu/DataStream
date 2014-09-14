package Components;

import Components.Streams.OctetStreamBuffer;


public class OctetStreamBufferI implements OctetStreamBuffer {
    private byte [] data = new byte[1024*8];
    private boolean last;
    private boolean hasData;
    public OctetStreamBufferI(){

    }
    public OctetStreamBufferI(byte [] data){
        this.data = data;
    }
    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public void setStreamInfo(byte[] info4s) {

    }

    @Override
    public byte[] getStreamInfo() {
        return new byte[0];
    }

    @Override
    public void setTransportInfo(byte[] info4t) {

    }

    @Override
    public byte[] getTransportInfo() {
        return new byte[0];
    }

    @Override
    public boolean getLast() {
        return last;
    }

    @Override
    public void setLast(boolean last) {
        this.last =last;
    }

    @Override
    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    @Override
    public boolean getHasData() {
        return hasData;
    }

    @Override
    public void release() {

    }

    @Override
    public void take() {

    }
}
