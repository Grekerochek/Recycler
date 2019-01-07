package com.alexander.recycler;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyIntentService extends IntentService {

    public static final int MSG_REGISTER_CLIENT = 1;
    public static final int MSG_UNREGISTER_CLIENT = 0;
    public static final int MSG_SET_VALUE = 2;


    private List<Messenger> mClients = new ArrayList<>();
    private Messenger mMessenger = new Messenger(new IncomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return mMessenger.getBinder();
    }

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){}
        for (int i=0;i<100;i++) {
            Log.d("PROVERKA", String.valueOf(i));
            for (Messenger messenger: mClients){
                try {
                    Log.d("PROVERKA", "sdfffff");
                    TimeUnit.SECONDS.sleep(3);
                    messenger.send(Message.obtain(null, MSG_SET_VALUE, WorkerGenerator.generateWorker()));

                } catch (RemoteException | InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_REGISTER_CLIENT:
                    Log.d("PROVERKA", "reg");
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    mClients.remove(msg.replyTo);
                    Log.d("PROVERKA", "un");
                    break;
                case MSG_SET_VALUE:
                    Log.d("PROVERKA", "se");
                    break;
            }
        }
    }
}
