package com.consulting.jbh.zim4;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
	
	public class DownloadTask extends AsyncTask<String, String, String>{
		
		
		@Override
		protected String doInBackground(String... urls) {
			String result="";
			URL url;
			HttpURLConnection urlConnection=null;
			
			try{
				if(urls.length>0) {
					url = new URL(urls[0]);
					urlConnection=(HttpURLConnection)url.openConnection();
					InputStream inputStream=urlConnection.getInputStream();
					InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
					int data=inputStreamReader.read();
					while(data!=-1){
						char current=(char) data;
						result+=current;
						
						data=inputStreamReader.read();
					}
				}
				return result;
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
			return result;
		}
		
		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
		}
	}
	
	public void startDownload(View view){
		EditText urlTextView=findViewById(R.id.urlTextView);
		String url=urlTextView.getText().toString().trim();
		
		
		if(url.length()>0){
			DownloadTask task=new DownloadTask();
			
			try{
				String rslt;
				rslt = task.execute(url).get();
				TextView outputTextView=findViewById(R.id.siteTextView);
				outputTextView.setText(rslt);
			}
			catch (InterruptedException ex){
				ex.printStackTrace();
			}
			catch (ExecutionException ex){
				ex.printStackTrace();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}
}
