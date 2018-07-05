package com.inyavic.inyavicsage.distresscallsmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneStateBroadcastReceiver extends BroadcastReceiver {

    Context mContext;
    String incomingNum;
    private boolean callReceived = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        //incomingNum = bundle.getString("incoming_number");

        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        CustomPhoneStateListener customPhoneStateListener = new CustomPhoneStateListener();
        // register our listener with TelephonyManager
        telephony.listen(customPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        mContext = context;

        for (String key : bundle.keySet()) {
            System.out.println(key + ": " + bundle.get(key));
        }
    }

    public class CustomPhoneStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (incomingNumber != null && incomingNumber.length() > 0)
                incomingNum = incomingNumber;
            else return;

            if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                callReceived = true;
            } else if ((state == TelephonyManager.CALL_STATE_IDLE) && (callReceived == true)) {
                callReceived = false;

                Intent i = new Intent(mContext, AddDistressCallInfoActivity.class);
                i.putExtra("caller_phone_num", incomingNumber);
                mContext.startActivity(i);
            }
        }
    }

}