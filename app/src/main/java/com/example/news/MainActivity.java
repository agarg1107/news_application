package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static Context activity;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DividerItemDecoration dividerItemDecoration;
    ArrayList list;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

        setContentView(R.layout.activity_main);
        list = new ArrayList<newsmodel>();
        recyclerView = findViewById(R.id.newsrecycleview);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());


        getdata();
    }

    public void getdata()
    {
        String url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=ef563e94a4d24e1d85dbf98374526a14";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonlist = response.getJSONArray("articles");
                            for(int i = 0 ; i< jsonlist.length();i++)
                            {
                                try
                                {
                                    JSONObject jsonObject = jsonlist.getJSONObject(i);

                                    String title = jsonObject.optString("title");
                                    String image = jsonObject.getString("urlToImage");
                                    String url = jsonObject.getString("url");
                                    list.add(new newsmodel(image,title,url));

                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                    Log.d("main",e+"");
                                }
                            }
                            adapter = new newsadapter(list,getApplicationContext());
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.addItemDecoration(dividerItemDecoration);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", error.toString());

                    }
                })
        {
            @Override
            public Map<String, String> getHeaders(){
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-agent", "Mozilla/5.0");
                return headers;


            }
        };
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}