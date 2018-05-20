package com.example.cm.clothes.ACTIVITIES;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm.clothes.MODELS.PayData;
import com.example.cm.clothes.R;

public class Pay extends AppCompatActivity  {

    ImageView img;
    TextView price;
    EditText name , phone , address;
    Intent i ;
    int pricee,ccount,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Init();
        PutData();

    }

    private void Init() {

        img = findViewById(R.id.pay_img);
        price = findViewById(R.id.pay_price);
        name = findViewById(R.id.pay_name);
        phone = findViewById(R.id.pay_phone);
        address = findViewById(R.id.pay_aaddress);
        i = getIntent();
    }

    void PutData()
    {
        ccount = i.getIntExtra("count",0);
        pricee = i.getIntExtra("price",0);
        image = i.getIntExtra("img",R.drawable.add);

        price.setText("Count : "+ccount+"\nPrice : "+pricee);
        img.setImageResource(image);

    }

    public void pay(View view) {
        String n = name.getText().toString().trim();
        String ph = phone.getText().toString().trim();
        String l = address.getText().toString().trim();

        if(n.length()==0 || l.length()==0 || ph.length()==0 || ph.length()<11 || ph.charAt(0)!='0' || ph.charAt(1)!='1')
        {
                Toast.makeText(this, "complete your data / Phone number incorrect", Toast.LENGTH_SHORT).show();
        }else {
            PayData payaData = new PayData(n,ph,l,ccount,pricee,image);
            conFirmNontification(n);
            name.setText("");
            phone.setText("");
            address.setText("");

        }
    }

    static int x = 0;
    void conFirmNontification(String n)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        builder.setContentTitle("Payment");
        builder.setContentText("Dear , "+n + "\n Payment Done to "+pricee+" PE\nthe product will arrive to you since 4 days");
        builder.setSmallIcon(image);
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(x++,builder.build());

    }

    public void payPal(View view) {
        Intent intent = new Intent(Pay.this , Pay_Paypal.class);
        intent.putExtra("count",ccount);
        intent.putExtra("price",pricee);
        startActivity(intent);
    }
}
