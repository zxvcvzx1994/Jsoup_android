package com.cvcompany.vo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cvcompany.vo.myapplication.Module.TiengAnh;
import com.cvcompany.vo.myapplication.Module.Volley.MyVolley;
import com.cvcompany.vo.myapplication.View.MyAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<TiengAnh> list ;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private String url="http://600tuvungtoeic.com/";
    private String ten="";
    private String hinhanh="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list = new ArrayList<TiengAnh>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),manager.getOrientation() ));
        recyclerView.setHasFixedSize(true);
        processdata();
    }

    private void processdata() {
        MyVolley.getSingleton(this).startVolley();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: "+response.toString());
                Document doc =  Jsoup.parse(response);
                Elements elements = doc.select("div.gallery-item");
                if(elements!=null){
                    for(Element element: elements){
                        Element elementTen  = element.getElementsByTag("h3").first();
                        Element elementHinhAnh  = element.getElementsByTag("img").first();
                        Log.i(TAG, "onResponse: "+ elementTen.text());
                        if (elementTen!=null)
                        ten = elementTen.text();
                        if(elementHinhAnh!=null)
                        hinhanh = elementHinhAnh.attr("src");
                        list.add(new TiengAnh(ten, hinhanh));
                    }
                }


                MyAdapter adapter = new MyAdapter(MainActivity.this, list);
                recyclerView.setAdapter(adapter);
                MyVolley.getSingleton(MainActivity.this).stopVolley();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyVolley.getSingleton(MainActivity.this).stopVolley();
            }
        });

        MyVolley.getSingleton(MainActivity.this).addVolley(stringRequest);
    }

}
