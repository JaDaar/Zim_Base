package com.consulting.jbh.zim4;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
	
	public class DownloadTask extends AsyncTask<String, String, String>{
		
		
		@Override
		protected String doInBackground(String... strings) {
			Log.i("TASK",strings[0]);
			return "COMPLETE";
		}
		
		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DownloadTask task=new DownloadTask();
		
		String rslt= String.valueOf(task.execute("http://www.jeffhamlin.com"));
	}
}
