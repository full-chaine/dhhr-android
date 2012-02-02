package org.fullchaine.dhhr.android;

import android.view.View;
import android.widget.TextView;

import com.codelines.lib.dynamiclist.AdapterItem;
import com.codelines.lib.dynamiclist.ViewHolder;
import org.fullchaine.dhhr.android.R;
import org.fullchaine.dhhr.android.model.News;

import java.text.DateFormat;
import java.util.Date;

public class NewsViewHolder extends ViewHolder<News>
{
    public final static int ResourceId = R.layout.test_item;

    private TextView titleView;
    private TextView authorView;
    private TextView dateView;

    public NewsViewHolder(View convertView)
    {
        super(convertView, ResourceId);        
        
        titleView = (TextView)view.findViewById(R.id.title);
        authorView = (TextView)view.findViewById(R.id.author);
        dateView = (TextView)view.findViewById(R.id.date);
    }
    
    public void render(AdapterItem<News> item)
    {
        titleView.setText(item.value.getTitle());
        authorView.setText(item.value.getAuthor());
        dateView.setText(DateFormat.getInstance().format(new Date(item.value.getDate())));
    }
}
