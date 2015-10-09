package com.example.user.gengtu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TakeFragment extends Fragment {
    private EditText mEdtTitle, mEdtDescription;
    private Button mBtnSubmit;


    public void onViewCreated (View view, Bundle savedInstanceState) {
        mEdtTitle = (EditText) view.findViewById(R.id.edtTitle);
        mEdtDescription = (EditText) view.findViewById(R.id.edtDescription);
        mBtnSubmit = (Button) view.findViewById(R.id.submit_post);

        mBtnSubmit.setOnClickListener(btnSubmitOnClick);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("=====>", "TakeFragment onAttach");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("=====>", "TakeFragment onCreateView");
        return inflater.inflate(R.layout.frg_take, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("=====>", "TakeFragment onActivityCreated");
    }

    private View.OnClickListener btnSubmitOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
/*            String url = "https://gentu-server.herokuapp.com/api/insertPost?userID=" + "jamesxxx" + "&" + "userName=" + "王智永" + "&" + "title=" + mEdtTitle.getText().toString() + "&" + "description=" + mEdtDescription.getText().toString() + "&" + "categorie=" + "funny";
            ServiceHandler sh = new ServiceHandler();
            sh.makeServiceCall(url, ServiceHandler.GET);*/
            send(v);
        }
        public void send(View v)
        {

            //check whether the msg empty or not
            if(mEdtTitle.getText().toString().length()>0) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("https://gentu-server.herokuapp.com/api/insertPost");

                try {
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    nameValuePairs.add(new BasicNameValuePair("userID", "jamesxxx"));
                    nameValuePairs.add(new BasicNameValuePair("userName", "王智永"));
                    nameValuePairs.add(new BasicNameValuePair("title", mEdtTitle.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("description", mEdtDescription.getText().toString()));
                    nameValuePairs.add(new BasicNameValuePair("categorie", "funny"));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    httpclient.execute(httppost);
                    //Toast.makeText(getBaseContext(), "Sent", Toast.LENGTH_SHORT).show();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //display message if text field is empty
                //Toast.makeText(getBaseContext(),"All fields are required",Toast.LENGTH_SHORT).show();
            }
        }
    };



}