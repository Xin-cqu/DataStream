package Components.Server;

import Components.SinkI;
import Components.Streams.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xinwen on 27/08/2014.
 */
public class SinkPortI implements SinkPort {
    private SinkI context;
    private File file ;
    private String path;
    private int fileSize;
    private FileOutputStream outputStream;
    public SinkPortI(String path){
        context = new SinkI(this);
        context.setEnableNewBuffer(true);
        this.path = path;
        this.run(null);
    }
    public int run(String[] args) {
        //int status = 0;
        Ice.Communicator ic = null;
        try {
            //初使化连接，args可以传一些初使化参数，如连接超时时间，初使化客户连接池的数量等
            ic = Ice.Util.initialize();
            //创建名适配器，并要求适配器使用缺省的协议(TCP/IP侦听端口为10000的请求)
            Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
                    "SendFile", "default -p 10000");
            //实例化一个对象，为接口创建一个服务对象

            SinkI._adapter = adapter;
//                FileManagementImpl._adapter = adapter;
            Ice.Object object = context;


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
            //status = 1;
        } finally {
            if (ic != null)
                ic.destroy();
        }
        return 0;
    }

    @Override
    public void started(StreamProfile streamPro4sink) throws GenericError {
        String [] fileInfo = streamPro4sink.properties.split(",");
        fileSize = Integer.parseInt(fileInfo[1]);
        file = new File(path,fileInfo[0]);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("started");
        before = System.currentTimeMillis();
    }
    private long before;
    private long after;
    @Override
    public void finished() throws GenericError {
        try {

            outputStream.close();
            outputStream.flush();
            after = System.currentTimeMillis();
            System.out.println(after-before);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.length()+":"+fileSize);
        System.out.println("finished");
    }

    @Override
    public void newBuffer(StreamBuffer theBuffer) {
        OctetStreamBuffer buffer = (OctetStreamBuffer) theBuffer;
        try {
            outputStream.write(buffer.getData());
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
