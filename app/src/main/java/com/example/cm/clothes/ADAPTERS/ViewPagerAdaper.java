package com.example.cm.clothes.ADAPTERS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm.clothes.ACTIVITIES.Pay;
import com.example.cm.clothes.MODELS.ProductData;
import com.example.cm.clothes.R;
import com.steelkiwi.library.IncrementProductView;
import com.steelkiwi.library.listener.OnStateListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewPagerAdaper extends PagerAdapter {

    Context context;
    int res;
    ArrayList<ProductData> arrayList ;
    int c;
    int priceVal;

    public ViewPagerAdaper(Context context, int res, ArrayList<ProductData> arrayList) {
        this.context = context;
        this.res = res;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(((LinearLayout) object));
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = LayoutInflater.from(context).inflate(res,container,false);
        IncrementProductView incrementProductView = view.findViewById(R.id.product_item_ipv);
        final TextView counterAndprice = view.findViewById(R.id.product_item_counterANDprice);
        CircleImageView circleImageView = view.findViewById(R.id.product_item_img);
        TextView price = view.findViewById(R.id.product_item_price);
        Button button = view.findViewById(R.id.product_item_buy);

        final ProductData productData = arrayList.get(position);
        price.setText(productData.getPrice()+" PE");
        circleImageView.setImageResource(productData.getImg());

        incrementProductView.setOnStateListener(new OnStateListener() {
            @Override
            public void onCountChange(int count) {

                 priceVal = productData.getPrice()*count;
                if(priceVal==0)
                {
                    counterAndprice.setText("");
                }else
                {
                    counterAndprice.setText("price : "+priceVal);
                    c = count;
                }

            }

            @Override
            public void onConfirm(int count) {

            }

            @Override
            public void onClose() {

            }
        });

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"myfont.ttf");
        price.setTypeface(typeface);
        counterAndprice.setTypeface(typeface);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = counterAndprice.getText().toString().trim();
                if(s.length()==0)
                {
                    Toast.makeText(context, "You aren’t buy and thing", Toast.LENGTH_SHORT).show();
                }else {

                    Intent intent = new Intent(context, Pay.class);
                    intent.putExtra("count",c);
                    intent.putExtra("price",priceVal);
                    intent.putExtra("img",arrayList.get(position).getImg());
                    context.startActivity(intent);
                }
            }
        });

        container.addView(view);
        return view;
    }


}
