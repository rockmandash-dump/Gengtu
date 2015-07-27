package com.example.user.gengtu;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class HotFragment extends ListFragment {

    private ProgressDialog pDialog;
    // URL to get contacts JSON
    private static String url = "https://gentu-server.herokuapp.com/api/queryData";

    // JSON Node names
    private static final String TAG_USER_ID = "userID";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_CATEGORIES = "categories";
    private static final String TAG_TIME = "time";

    // post JSONArray
    JSONArray posts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> postList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void onViewCreated (View view, Bundle savedInstanceState) {
        postList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Calling async task to get json
        new GetPosts().execute();
    }


    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetPosts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray posts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < posts.length(); i++) {
                        JSONObject c = posts.getJSONObject(i);

                        String id = c.getString(TAG_USER_ID);
                        String title = c.getString(TAG_TITLE);
                        String description = c.getString(TAG_DESCRIPTION);

                        // tmp hashmap for single contact
                        HashMap<String, String> post = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        post.put(TAG_USER_ID, id);
                        post.put(TAG_TITLE, title);
                        post.put(TAG_DESCRIPTION, description);

                        // adding contact to contact list
                        postList.add(post);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), postList,
                    R.layout.list_item, new String[] { TAG_USER_ID, TAG_TITLE,
                    TAG_DESCRIPTION }, new int[] { R.id.userID,
                    R.id.title, R.id.description });

            setListAdapter(adapter);
        }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("=====>", "HotFragment onAttach");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("=====>", "HotFragment onCreateView");
        return inflater.inflate(R.layout.frg_hot, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("=====>", "HotFragment onActivityCreated");

    }

}
