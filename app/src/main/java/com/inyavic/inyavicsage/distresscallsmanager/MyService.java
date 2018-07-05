package com.inyavic.inyavicsage.distresscallsmanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import java.text.DateFormat;
import java.util.Date;

public class MyService extends Service {

    Context context = this;

    private final PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        private String incomingNum;
        private String datetime;
        private boolean callReceived = false;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (incomingNumber != null && incomingNumber.length() > 0) {
                incomingNum = incomingNumber;
                datetime = DateFormat.getDateInstance().format(new Date()) + " " + DateFormat.getTimeInstance().format(new Date());
            } else return;

            if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                callReceived = true;
            } else if (state == TelephonyManager.CALL_STATE_IDLE && callReceived == true) {
                callReceived = false;

                Intent i = new Intent(context, AddDistressCallInfoActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("caller_phone_num", incomingNumber);
                i.putExtra("datetime_received", datetime);
                startActivity(i);
            }
        }
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        // register our listener with TelephonyManager
        telephony.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        return super.onStartCommand(intent, flags, startId);
    }
}
