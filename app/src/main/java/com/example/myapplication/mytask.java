package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class mytask extends AsyncTask<String,Integer,Boolean> {

    public static String TAG = mytask.class.getSimpleName();

    ProgressBar mProgressBar;

    TextView mTextView;



    public mytask(ProgressBar progressBar, TextView textView) {

        mProgressBar = progressBar;

        mTextView = textView;

    }



    @Override

    protected void onPreExecute() {

        super.onPreExecute();

        mProgressBar.setVisibility(View.VISIBLE);

    }



    /**

     * this method will work in the background thread

     * and it is downloading the image

     * @param url

     * @return

     */

    @Override

    protected Boolean doInBackground(String... url) {

        Log.i(TAG,"doInBackground"+url);

        try {



            int totalSecs = 100;

            for (int i = 0; i<=totalSecs; i++){

                Thread.sleep(100);

                float percentage = ((float)i / (float)totalSecs) * 100;



                publishProgress( new Float( percentage).intValue()); //return the percentage of downloaded image

                //publishProgress will call onprogressupdate method

            }

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        return true;

    }



    @Override

    protected void onProgressUpdate(Integer... values) {

        super.onProgressUpdate(values);

        Log.i(TAG,"onProgressUpdate--"+values[0]);



        mProgressBar.setProgress(values[0]);

        mTextView.setText(""+values[0]);





    }



    @Override

    protected void onPostExecute(Boolean aBoolean) {

        super.onPostExecute(aBoolean);

        mProgressBar.setVisibility(View.GONE);



    }

}
