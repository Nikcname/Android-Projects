package com.forevermore.nikcname.nevermore;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; 

import java.io.IOException;

public class PageDownloader extends AsyncTask {

    @Override
    protected Document doInBackground(Object[] objects) {
        try {
            return Jsoup.connect(objects[0].toString()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
