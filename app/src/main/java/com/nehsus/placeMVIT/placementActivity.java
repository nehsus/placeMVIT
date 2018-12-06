package com.nehsus.placeMVIT;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import com.nehsus.placeMVIT.R;

import java.util.List;
import java.util.Map;

public class placementActivity extends AppCompatActivity{

    private ArrayList<String> imageUrlList ;
    private ArrayList<String> linkUrlArray;
    private ArrayList<String> titleList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseweb);

        try {
            ProgressAsyncTask asyncTask = new ProgressAsyncTask();
            asyncTask.execute(10000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public void getData(List<Map<String, String>> map) {
        imageUrlList = new ArrayList<>();
        linkUrlArray = new ArrayList<>();
        titleList = new ArrayList<>();

        for (int i = 0; i < map.size(); i++) {
            imageUrlList.add(map.get(i).get("img"));
            linkUrlArray.add(map.get(i).get("url"));
            titleList.add(map.get(i).get("title"));
        }
        String url = "http://sirmvitplacements2019.wordpress.com/";

        Bundle bundle = new Bundle();

        bundle.putString("url", url);
        Intent intent = new Intent(placementActivity.this, BaseWebActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

        class ProgressAsyncTask extends AsyncTask<Integer, Integer, List<Map<String, String>>> {

            private List<Map<String, String>> map;

            public ProgressAsyncTask() {
                super();
                map = new ArrayList<>();
            }

            @Override
            protected List<Map<String, String>> doInBackground(Integer... params) {
                Document doc = null;

                try {
                    doc = Jsoup.connect("http://sirmvitplacements2019.wordpress.com/").get();
                    Elements elements1 = doc.select("[class=content-area][id=primary]");
                    Elements elements2 = elements1.select("main");
                    Elements elements3 = elements1.select("img");
                    Elements elements4 = elements1.select("a");
                    for (int i = 0; i < elements2.size(); i++) {
                        Map<String, String> m = new HashMap<>();
                        m.put("title", elements2.get(i).text());
                        m.put("img", "https://sirmvitplacements2019.wordpress.com/" + elements3.get(i).attr("src"));
                        m.put("url", elements4.get(i).attr("href"));
                        map.add(m);
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return map;
                //return test();
            }

            @Override
            protected void onPostExecute(List<Map<String, String>> result) {

                getData(result);

            }

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onProgressUpdate(Integer... values) {

            }
        }
    }
