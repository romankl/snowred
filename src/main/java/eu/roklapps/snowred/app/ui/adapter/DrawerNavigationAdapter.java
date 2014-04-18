package eu.roklapps.snowred.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import eu.roklapps.snowred.app.R;
import eu.roklapps.snowred.app.api.reddit.model.Subreddit;

public class DrawerNavigationAdapter extends ArrayAdapter<Subreddit> {
    private Context mContext;
    private LayoutInflater mInflater;

    public DrawerNavigationAdapter(Context context, int resource) {
        super(context, resource);
    }

    public DrawerNavigationAdapter(Context context, int resource, List<Subreddit> objects) {
        super(context, resource, objects);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Subreddit subreddit = getItem(position);

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.single_drawer_list_item, null);

            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(subreddit.getDisplay_name());

        return convertView;
    }

    private static class ViewHolder {
        TextView name;
    }
}
