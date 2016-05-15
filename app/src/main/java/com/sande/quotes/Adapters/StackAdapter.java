package com.sande.quotes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sande.quotes.Pojo.QuotesClass;
import com.sande.quotes.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 15-May-16.
 */
public class StackAdapter extends BaseAdapter {

    ArrayList<QuotesClass> mQuotes=new ArrayList<>();
    LayoutInflater mLayInflater;
    public StackAdapter(ArrayList<QuotesClass> quotes, Context context) {
        mQuotes=quotes;
        mLayInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(mQuotes.size()==0)
            return 0;
        else
            return mQuotes.size();
    }

    @Override
    public QuotesClass getItem(int position) {
        return mQuotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=mLayInflater.inflate(R.layout.quote_card_item,parent,false);
        String quot=mQuotes.get(position).getContent();
        quot=quot.replace("<p>","");
        quot=quot.replace("</p>","");
        quot=quot.replace("&#8217;","'");
        TextView quote=(TextView)convertView.findViewById(R.id.quote_tv_qci);
        quote.setText(quot);
        TextView author=(TextView)convertView.findViewById(R.id.author_tv_qci);
        author.setText(mQuotes.get(position).getTitle());
        return convertView;
    }
}
