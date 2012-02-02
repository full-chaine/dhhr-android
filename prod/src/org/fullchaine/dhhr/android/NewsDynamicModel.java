package org.fullchaine.dhhr.android;

import android.os.AsyncTask;
import android.os.Handler;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.fullchaine.dhhr.android.model.News;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class NewsDynamicModel
{
	private final int pageSize = 20;
    private ArrayList<News> newsList = new ArrayList<News>();
    private boolean moreToLoad = true;


	private Handler _loadCompleted;

	public void loadMore()
	{
        if (moreToLoad)
		    (new BackgroundTask()).execute();
	}

	public int getCount()
	{
		return newsList.size();
	}

	public News getItem(int position)
	{
        return newsList.get(position);
	}

	public void setLoadCompleted(Handler loadCompleted)
	{
		_loadCompleted = loadCompleted;
	}

    public boolean isMoreToLoad() {
        return moreToLoad;
    }

    private class BackgroundTask extends AsyncTask<String, Void, Integer>
	{
        private volatile News[] allNews;
        final int lastPageNumber = (newsList.size() / pageSize);

		@Override
		protected Integer doInBackground(String... params)
		{
            //final HttpGet requestPages = new HttpGet("http://www.dhhr.ca/api/news/page/1?since");
            final HttpGet requestPages = new HttpGet("http://www.dhhr.ca/api/news/page/"+(lastPageNumber+1)+"?rpp="+pageSize);
            final AbstractHttpClient client = new DefaultHttpClient();
            //client.setRedirectHandler(new DefaultRedirectHandler());
            //client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler());
            try {
                allNews = client.execute(requestPages, new ResponseHandler<News[]>() {
                    public News[] handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                        final HttpEntity entity = httpResponse.getEntity();
                        if (entity == null)
                            return null;

                        final InputStreamReader reader = new InputStreamReader(entity.getContent());
                        final News[] response = new Gson().fromJson(reader, News[].class);
                        return response;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
		}

		@Override
		protected void onPostExecute(Integer result)
		{
            if (allNews == null)
                return;

            if (allNews.length == 0)
                moreToLoad = false;

            newsList.addAll(Arrays.asList(allNews));
			if (_loadCompleted != null)
			{
				_loadCompleted.sendEmptyMessage(0);
			}
		}
	}

}
