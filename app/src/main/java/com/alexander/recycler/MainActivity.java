package com.alexander.recycler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private LinearLayoutManager manager;
    private List<BaseItem> workers;

    private Messenger mService;

    final Messenger mMessenger = new Messenger(new IncomingHandler());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        startService(new Intent(MainActivity.this, MyIntentService.class));
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        workers = new ArrayList<>();
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new CustomAdapter(workers);
        recyclerView.setAdapter(adapter);
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mService = new Messenger(service);
            Message message = Message.obtain(null,
                    MyIntentService.MSG_REGISTER_CLIENT);
            message.replyTo = mMessenger;
            try {
                mService.send(message);
            }
            catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        bindService();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBindService();
    }

    public void bindService(){

        bindService(new Intent(MainActivity.this, MyIntentService.class), mServiceConnection, Context.BIND_AUTO_CREATE);

    }
    public void unBindService(){
        Message msg = Message.obtain(null,
                MyIntentService. MSG_UNREGISTER_CLIENT);
        msg.replyTo = mMessenger;

        try {
            mService.send(msg);
        } catch (RemoteException e){
            e.printStackTrace();
        }

        unbindService(mServiceConnection);
    }

    private class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message message){
            super.handleMessage(message);

            Log.d("PROVERKA", "handl");
            switch (message.what){

                case MyIntentService.MSG_SET_VALUE:
                    workers = new ArrayList<>(workers);
                    workers.add((BaseItem)message.obj);
                    adapter.onNewData(workers);
            }
        }

    }
}
