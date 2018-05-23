package com.galileo.micromasterandroid.creatingnotifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.with_channel_message)
    EditText withChannelMessage;

    final static int NOTIFICATION_ONE = 10001;
    final static String CHANNELONE = "Channel One";
    final static String CHANNELID = "30201";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void sendWithChannel(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNELID,CHANNELONE, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Default Notification Channel");
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,CHANNELID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notification Title")
                .setContentText(withChannelMessage.getText().toString());
        
        assert notificationManager != null;
        notificationManager.notify(NOTIFICATION_ONE, notificationBuilder.build());
    }

    @OnClick({ R.id.with_channel_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.with_channel_button:
                sendWithChannel(view);
                break;
        }
    }
}
