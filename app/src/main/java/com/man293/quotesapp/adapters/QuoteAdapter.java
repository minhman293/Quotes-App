package com.man293.quotesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.man293.quotesapp.models.Quote;
import com.man293.quotesapp.R;

import java.util.List;

public class QuoteAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Quote> quoteList;
    public ViewHolder holder;

    public QuoteAdapter(Context context, int layout, List<Quote> quoteList) {
        this.context = context;
        this.layout = layout;
        this.quoteList = quoteList;
    }

    @Override
    public int getCount() {
        return quoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView content, auth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder = new ViewHolder();
            holder.content = (TextView) convertView.findViewById(R.id.tvQuoteContent);
            holder.auth = (TextView) convertView.findViewById(R.id.tvQuoteAuthor);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Quote quote = quoteList.get(position);
        holder.content.setText(quote.getContent());
        holder.auth.setText("_" + quote.getAuthor() + "_");

        return convertView;
    }
}
