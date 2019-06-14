package com.forevermore.nikcname.nevermore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StartActivity extends AppCompatActivity {


    private Document document;
    private Elements images;
    private String imgRegex = "(?i)<img[^>]+?src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
    private String cssPath = "html body#wrap div.main_fon div#content div.content_row div.manga_images a img";
    private Pattern p = Pattern.compile(imgRegex);
    private Matcher m;
    private String siteUrl = "https://manga-chan.me/manga/new";
    private List<String> urls = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private List<Bitmap> logo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        recyclerView = findViewById(R.id.recycler_manga_list);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MangaAdapter(logo);
        recyclerView.setAdapter(adapter);

        try {

            document = (Document) new PageDownloader().execute(siteUrl).get();
            images = document.select(cssPath);
            for (int i = 0; i<images.size(); i+=2){
                m = p.matcher(images.get(i).toString());
                while (m.find()){
                    urls.add(m.group(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new ImageDownloader().execute(urls).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    public class ImageDownloader extends AsyncTask<List<String>, Void, List<Bitmap>> {

        private List<Bitmap> images = new ArrayList<>();

        @Override
        protected List<Bitmap> doInBackground(List<String>... strs) {

            for (String s : strs[0]){
                try {

                    URL url = new URL(s);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    images.add(BitmapFactory.decodeStream(inputStream));

                } catch (Exception e) {
                    Log.e("tag", e.toString());
                }
            }
            return images;
        }

        @Override
        protected void onPostExecute(List<Bitmap> bitmaps) {
            super.onPostExecute(bitmaps);
            logo.addAll(bitmaps);
            StartActivity.this.adapter.notifyDataSetChanged();
        }
    }
}
