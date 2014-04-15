package eu.roklapps.snowred.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eu.roklapps.snowred.app.api.reddit.model.Link;
import eu.roklapps.snowred.app.ui.activity.LogInActivity;
import eu.roklapps.snowred.app.ui.fragments.BrowserFragment;
import eu.roklapps.snowred.app.ui.fragments.LinkFragment;
import eu.roklapps.snowred.app.ui.fragments.NavigationDrawerFragment;
import eu.roklapps.snowred.app.util.Util;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, LinkFragment.OnLinkClickListener {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.enableStrictMode();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        FragmentManager manager = getFragmentManager();

        Fragment listFragment = manager.findFragmentByTag("listing");
        if (listFragment == null) {
            manager.beginTransaction()
                    .add(R.id.container, LinkFragment.newInstance(""), "listing")
                    .commit();
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        /*FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();*/
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    } //f90a0152a8457cd949f7a3872b3f757ea4445f29

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_login:
                Intent i = new Intent(this, LogInActivity.class);
                startActivityForResult(i, LogInActivity.LOGIN_REQUEST);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void linkClicked(Link link) {
        if (!link.isIs_self()) {
            BrowserFragment fragment = BrowserFragment.newInstance(link.getUrl());

            getFragmentManager().beginTransaction()
                    .addToBackStack("listing")
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
