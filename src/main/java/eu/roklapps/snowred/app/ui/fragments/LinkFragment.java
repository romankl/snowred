package eu.roklapps.snowred.app.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

import eu.roklapps.snowred.app.R;
import eu.roklapps.snowred.app.api.reddit.model.Link;
import eu.roklapps.snowred.app.ui.activity.LogInActivity;
import eu.roklapps.snowred.app.ui.adapter.LinkListingAdapter;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;


public class LinkFragment extends ListFragment implements OnRefreshListener {
    private static final String SUBREDDIT_RELATIVE_NAME = "subreddit_relative_name";
    private static final String TAG = "LinkFragment";
    private FutureCallback<JsonObject> mResult = new FutureCallback<JsonObject>() {
        @Override
        public void onCompleted(Exception e, JsonObject result) {
            Log.d(TAG, result.toString());
            new LinkPreparation().execute(result);
        }
    };
    private String mSubredditName;
    private OnLinkClickListener mListener;
    private List<Link> mLinks;
    private LinkListingAdapter mAdapter;
    private PullToRefreshLayout mPullToRefreshLayout;

    public LinkFragment() {
    }

    public static Fragment newInstance(String param1) {
        LinkFragment fragment = new LinkFragment();
        Bundle args = new Bundle();
        args.putString(SUBREDDIT_RELATIVE_NAME, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        if (getArguments() != null) {
            mSubredditName = getArguments().getString(SUBREDDIT_RELATIVE_NAME);
        }

        Link.getLinksForSubreddit(getActivity(), mSubredditName, mResult);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_link_list, container, false);

        mPullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.refresh_layout);
        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(this)
                .setup(mPullToRefreshLayout);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "Position: " + position + " id: " + id);

        mListener.linkClicked((Link) getListAdapter().getItem(position));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLinkClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnLinkClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LogInActivity.LOGIN_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {

            }
        }
    }

    @Override
    public void onRefreshStarted(View view) {
        Link.getLinksForSubreddit(getActivity(), mSubredditName, mResult);
    }

    public interface OnLinkClickListener {
        public void linkClicked(Link link);
    }

    private class LinkPreparation extends AsyncTask<JsonObject, Void, Void> {

        @Override
        protected Void doInBackground(JsonObject... jsonObjects) {
            JsonArray array = jsonObjects[0].getAsJsonObject("data").getAsJsonArray("children");
            mLinks = new ArrayList<Link>();
            Link link;
            JsonElement element;

            for (int i = 0; i < array.size(); i++) {
                Log.d(TAG, array.get(i).toString());
                element = array.get(i).getAsJsonObject().get("data").getAsJsonObject();
                link = new Gson().fromJson(element, Link.class);

                mLinks.add(link);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter = new LinkListingAdapter(getActivity(), R.layout.single_link_item, mLinks);
            setListAdapter(mAdapter);
            mPullToRefreshLayout.setRefreshComplete();
        }
    }
}
