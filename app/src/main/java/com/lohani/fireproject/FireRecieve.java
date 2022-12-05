package com.lohani.fireproject;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FireRecieve extends FirebaseMessagingService {

//    @Database(entities = {EventsDatabase.class}, version = 1)
//    public abstract class AppDatabase extends RoomDatabase {
//        public abstract EventsDao eventsDao();
//    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);
        Log.d("MessageRecieved", "TRUE");
        Log.d(String.valueOf(remoteMessage.getData()),"TRUE");
//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "eventsdatabase").build();
//        EventsDao eventsDao =  db.eventsDao();
//        String event = remoteMessage.getData().get("event");
//        String datetime = remoteMessage.getData().get("date")+" "+remoteMessage.getData().get("time");
//        EventsDatabase e = new EventsDatabase();
//        e.setEvent(event,datetime);
//        eventsDao.insertAll(e);
        String messageTitle = remoteMessage.getData().get("title");
        String messageDesc = remoteMessage.getData().get("desc");
        if(messageTitle != null) {
            sendNotification(messageTitle, messageDesc);
        }
        else{
            String pc = remoteMessage.getData().get("personCount");
            SharedPreferences sharedPreferences = this.getSharedPreferences("VALUESTORE",this.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("personCount",pc);
            editor.apply();
        }




    }

    public void sendNotification(String messageTitle, String messageDesc) {
        Log.d("message title", messageTitle);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationManager nm = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        String notifyid = "alarm_channel";
        CharSequence channame = getString(R.string.channel_name);
        String chandesc = getString(R.string.channel_desc);
        NotificationChannel chan = new NotificationChannel(notifyid, channame, importance);
        chan.setDescription(chandesc);
        chan.enableVibration(true);
        nm.createNotificationChannel(chan);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification noti = new Notification.Builder(this)
                .setContentTitle(messageTitle)
                .setContentText(messageDesc)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setChannelId(notifyid)
                .build();
//        Notification noti =
//                new NotificationCompat.Builder(this,"1")
//                        .setSmallIcon(R.drawable.ic_launcher_background)
//                        .setContentTitle("My notification")
//                        .setContentText("Hello World!")
//                        .setChannelId("1").build();
//        NotificationManager nm = NotificationManagerCompat.from(1,noti);
        nm.notify(1, noti);

    }


}
