package com.example.cm.clothes.ACTIVITIES;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cm.clothes.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class Pay_Paypal extends AppCompatActivity {

    PayPalConfiguration payPalConfiguration;
    final String client_id="AdkNG4PW10Pj3-ZcliW9qPS6uddZig6AtiSYVergZQH2F-VBo_jd9FW5gMaQJxvAhxPah62yL7h1yZ1L";
    Intent service;
    int code = 9999;
    int price , count;
    static int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay__paypal);

        payPalConfiguration = new PayPalConfiguration();
        payPalConfiguration.environment(PayPalConfiguration.ENVIRONMENT_SANDBOX);
        payPalConfiguration.clientId(client_id);

        service = new Intent(this, PayPalService.class);
        service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
        startService(service);

    }

    public void pay(View view) {

        price = getIntent().getIntExtra("price",0);
        count = getIntent().getIntExtra("count",0);
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(price),"USD","payment with paypal",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this,PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent,code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==code && resultCode==RESULT_OK)
        {
            PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (paymentConfirmation!=null)
            {
                String state = paymentConfirmation.getProofOfPayment().getState();

                if(state.equals("approved"))
                {
                   conFirmNontification(price);
                }else
                {
                    Toast.makeText(this, "error in the payment", Toast.LENGTH_SHORT).show();
                }

            }else
            {
                Toast.makeText(this, "paymentConfirmation is null", Toast.LENGTH_SHORT).show();
            }
        }
    }


    void conFirmNontification(int p )
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        builder.setContentTitle("Payment");
        builder.setContentText("Payment Done to "+p+" PE\nthe product will arrive to you since 4 days");
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(x++,builder.build());

    }
}
