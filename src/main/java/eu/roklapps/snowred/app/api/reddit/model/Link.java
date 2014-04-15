package eu.roklapps.snowred.app.api.reddit.model;

import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import eu.roklapps.snowred.app.api.reddit.access.Connection;

public class Link {
    String author;
    private String clicked;
    private String domain;
    private boolean hidden;
    private boolean is_self;
    private boolean likes;
    private boolean stickied;
    private boolean saved;
    private boolean over_18;
    private String thumbnail;
    private String subreddit;
    private String url;
    private String selftext_html;
    private int score;
    private int num_comments;
    private String title;
    private long created_utc;

    public static void getLinksForSubreddit(Context context, String subreddit, FutureCallback<JsonObject> result) {
        Connection connection = new Connection("http://reddit.com/" + subreddit + ".json", context);

        if (CurrentUser.getInstance().isLoggedIn()) {
            connection.setParams(CurrentUser.getCredentials().getCookieAndModHash());
        }
        connection.setCallback(result)
                .performOperation();
    }

    public boolean isIs_self() {
        return is_self;
    }

    public void setIs_self(boolean is_self) {
        this.is_self = is_self;
    }

    public long getCreated_utc() {
        return created_utc;
    }

    public void setCreated_utc(long created_utc) {
        this.created_utc = created_utc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getClicked() {
        return clicked;
    }

    public void setClicked(String clicked) {
        this.clicked = clicked;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isLikes() {
        return likes;
    }

    public void setLikes(boolean likes) {
        this.likes = likes;
    }

    public boolean isStickied() {
        return stickied;
    }

    public void setStickied(boolean stickied) {
        this.stickied = stickied;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isOver_18() {
        return over_18;
    }

    public void setOver_18(boolean over_18) {
        this.over_18 = over_18;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSelftext_html() {
        return selftext_html;
    }

    public void setSelftext_html(String selftext_html) {
        this.selftext_html = selftext_html;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }
}
