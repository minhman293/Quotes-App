package com.man293.quotesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.man293.quotesapp.R;
import com.man293.quotesapp.adapters.QuoteAdapter;
import com.man293.quotesapp.models.Quote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    ListView lvMain;
    ArrayList<Quote> quoteArr;
    QuoteAdapter quoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize
        lvMain = (ListView) findViewById(R.id.lvQuotesList);
        quoteArr = new ArrayList<>();

        // get json from url
        new GetQuoteAsynctask().execute("https://dummyjson.com/quotes");

        // initialize adapter
        quoteAdapter = new QuoteAdapter(this, R.layout.single_quote, quoteArr);

        // set adapter for listview
        lvMain.setAdapter(quoteAdapter);

    }

    // asynctask to get json
    private class GetQuoteAsynctask extends AsyncTask<String, Void, String> {

        // get all lines of file json and convert it to string
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        // get data from json
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray quoteJsonArr = object.getJSONArray("quotes");

                for (int i = 0; i < quoteJsonArr.length(); i++) {
                    JSONObject quote = quoteJsonArr.getJSONObject(i);
                    String cont = quote.getString("quote");
                    String auth = quote.getString("author");
                    quoteArr.add(new Quote(cont, auth));
                }

                quoteAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}