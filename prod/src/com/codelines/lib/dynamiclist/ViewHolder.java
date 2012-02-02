package com.codelines.lib.dynamiclist;

import android.view.View;

public class ViewHolder<T>
{
    public final int resourceId;

    public final View view;
    public Object tag = null;
    
    public ViewHolder(View convertView, int resourceId)
    {
        this.view = convertView;
        this.resourceId = resourceId;
        
        convertView.setTag(this);
    }
    
    public void render(AdapterItem<T> item)
    {
    }
}
