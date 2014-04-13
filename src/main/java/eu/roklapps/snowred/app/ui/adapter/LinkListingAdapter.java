package eu.roklapps.snowred.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import eu.roklapps.snowred.app.R;
import eu.roklapps.snowred.app.api.reddit.model.Link;

public class LinkListingAdapter extends ArrayAdapter<Link> {
    private Context mContext;
    private LayoutInflater mInflater;

    public LinkListingAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);

        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
    }

    public LinkListingAdapter(Context context, int resource, List<Link> objects) {
        super(context, resource, objects);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Link linkModel = getItem(position);
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.single_link_item, null);

            holder = new ViewHolder();

            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.score = (TextView) convertView.findViewById(R.id.scorePoints);
            holder.numberOfComments = (TextView) convertView.findViewById(R.id.comments);
            holder.nameOfSubreddit = (TextView) convertView.findViewById(R.id.subreddit);
            holder.creationDate = (TextView) convertView.findViewById(R.id.creationdate);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.author.setText(linkModel.getAuthor());
        holder.title.setText(linkModel.getTitle());
        holder.score.setText(String.valueOf(linkModel.getScore()));
        holder.numberOfComments.setText(String.valueOf(linkModel.getNum_comments()));
        holder.nameOfSubreddit.setText(linkModel.getSubreddit());
        holder.creationDate.setText(String.valueOf(linkModel.getCreated_utc()));

        return convertView;
    }

    private static class ViewHolder {
        TextView score;
        TextView title;
        TextView author;
        TextView numberOfComments;
        TextView nameOfSubreddit;
        TextView creationDate;
    }
}
