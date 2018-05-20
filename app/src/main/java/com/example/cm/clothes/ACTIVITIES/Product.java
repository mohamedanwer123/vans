package com.example.cm.clothes.ACTIVITIES;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cm.clothes.ADAPTERS.ViewPagerAdaper;
import com.example.cm.clothes.MODELS.ProductData;
import com.example.cm.clothes.R;

import java.util.ArrayList;

public class Product extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<ProductData> arrayList;
    ViewPagerAdaper viewPagerAdaper;
    Intent intent;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Init();
        selectedData();
    }

    void Init()
    {
        viewPager = findViewById(R.id.product_vp);
        arrayList = new ArrayList<>();
        intent = getIntent();
    }

    void PutShosesData()
    {
        arrayList.add(new ProductData(R.drawable.shose,320));
        arrayList.add(new ProductData(R.drawable.sh1,250));
        arrayList.add(new ProductData(R.drawable.sh2,350));
        arrayList.add(new ProductData(R.drawable.sh3,390));
        arrayList.add(new ProductData(R.drawable.sh4,360));
        arrayList.add(new ProductData(R.drawable.sh5,500));

        viewPagerAdaper = new ViewPagerAdaper(Product.this,R.layout.items,arrayList);
        viewPager.setAdapter(viewPagerAdaper);
    }

    void PutClosessData()
    {
        arrayList.add(new ProductData(R.drawable.clothes,320));
        arrayList.add(new ProductData(R.drawable.cl1,250));
        arrayList.add(new ProductData(R.drawable.cl2,150));
        arrayList.add(new ProductData(R.drawable.cl5,280));
        arrayList.add(new ProductData(R.drawable.cl4,200));
        arrayList.add(new ProductData(R.drawable.cl6,220));

        viewPagerAdaper = new ViewPagerAdaper(Product.this,R.layout.items2,arrayList);
        viewPager.setAdapter(viewPagerAdaper);
    }

    void PutBabyData()
    {

        arrayList.add(new ProductData(R.drawable.b1,250));
        arrayList.add(new ProductData(R.drawable.b2,150));
        arrayList.add(new ProductData(R.drawable.b3,280));
        arrayList.add(new ProductData(R.drawable.b4,200));
        arrayList.add(new ProductData(R.drawable.b5,220));

        viewPagerAdaper = new ViewPagerAdaper(Product.this,R.layout.items3,arrayList);
        viewPager.setAdapter(viewPagerAdaper);
    }

    void PutSockesData()
    {
        arrayList.add(new ProductData(R.drawable.s1,30));
        arrayList.add(new ProductData(R.drawable.s2,40));
        arrayList.add(new ProductData(R.drawable.s3,35));
        arrayList.add(new ProductData(R.drawable.s4,40));

        viewPagerAdaper = new ViewPagerAdaper(Product.this,R.layout.items4,arrayList);
        viewPager.setAdapter(viewPagerAdaper);
    }



    void selectedData()
    {
        flag = intent.getIntExtra("flag",0);

        if(flag==1)
        {
            PutShosesData();
        }else if(flag == 2)
        {
            PutBabyData();
        }else  if(flag==3)
        {
            PutSockesData();
        }else {
            PutClosessData();
        }
    }
}
