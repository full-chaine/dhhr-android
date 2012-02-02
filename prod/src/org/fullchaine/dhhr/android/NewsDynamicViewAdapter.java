package org.fullchaine.dhhr.android;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import com.codelines.lib.dynamiclist.AdapterItem;
import com.codelines.lib.dynamiclist.DynamicViewAdapter;
import com.codelines.lib.dynamiclist.ViewHolder;
import org.fullchaine.dhhr.android.model.News;

public class NewsDynamicViewAdapter extends DynamicViewAdapter<News>
{
	private final NewsDynamicModel _model;

	public NewsDynamicViewAdapter(Context context, NewsDynamicModel model)
	{
		super(context, R.layout.progress_item, R.layout.unavailable_item,
				R.layout.empty_item);

		_model = model;
		_model.setLoadCompleted(new Handler()
		{
			@Override
			public void dispatchMessage(Message msg)
			{
				setDataCompleted();
			}
		});
		submitDataRequest();
	}

	@Override
	public View onDataView(int position, AdapterItem<News> item, View convertView,
			ViewGroup parent)
	{
		convertView = tryInflateView(convertView, NewsViewHolder.ResourceId,
				parent);
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		if (viewHolder == null)
		{
			viewHolder = new NewsViewHolder(convertView);
		}
		viewHolder.render(item);
		return convertView;
	}


	/*This method should return true if there is more data to load. 
	 * For instance it loads by 20 items, for list with 60 items it 
	 * should return true until it riches (60th) last item in the list;
	 * */
	@Override
	protected boolean isMoreToLoad()
	{
		return _model.isMoreToLoad();
	}
	
	/*This method is called when data item needs to be loaded from the model to the adapter; 
	 * Use addItem method to load every item from the model.*(non-Javadoc)
	 * @see com.examples.dynamiclist.core.DynamicViewAdapter#isMoreToLoad()
	 */
	@Override
	protected void onDataLoad()
	{
		for (int i = 0; i < _model.getCount(); i++)
		{
			addItem(_model.getItem(i), null);
		}
	}

	@Override
	protected void onSubmitDataRequest()
	{
		_model.loadMore();
	}

}
