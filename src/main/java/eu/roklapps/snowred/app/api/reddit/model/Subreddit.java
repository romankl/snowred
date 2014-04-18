package eu.roklapps.snowred.app.api.reddit.model;

public class Subreddit extends Thing {
    private int accounts_active;
    private int comment_score_hide_mins;
    private String description;
    private String description_html;
    private String display_name;
    private String header_img;
    private String header_size;
    private String header_title;
    private boolean over18;
    private String public_description;
    private boolean public_traffic;
    private long subscribers;
    private String submission_type;
    private String submit_link_label;
    private String submit_text_label;
    private String subreddit_type;
    private String title;
    private String url;
    private boolean user_is_banned;
    private boolean user_is_contributor;
    private boolean user_is_moderator;
    private boolean user_is_subscriber;

    public int getAccounts_active() {
        return accounts_active;
    }

    public void setAccounts_active(int accounts_active) {
        this.accounts_active = accounts_active;
    }

    public int getComment_score_hide_mins() {
        return comment_score_hide_mins;
    }

    public void setComment_score_hide_mins(int comment_score_hide_mins) {
        this.comment_score_hide_mins = comment_score_hide_mins;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_html() {
        return description_html;
    }

    public void setDescription_html(String description_html) {
        this.description_html = description_html;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getHeader_img() {
        return header_img;
    }

    public void setHeader_img(String header_img) {
        this.header_img = header_img;
    }

    public String getHeader_size() {
        return header_size;
    }

    public void setHeader_size(String header_size) {
        this.header_size = header_size;
    }

    public String getHeader_title() {
        return header_title;
    }

    public void setHeader_title(String header_title) {
        this.header_title = header_title;
    }

    public boolean isOver18() {
        return over18;
    }

    public void setOver18(boolean over18) {
        this.over18 = over18;
    }

    public String getPublic_description() {
        return public_description;
    }

    public void setPublic_description(String public_description) {
        this.public_description = public_description;
    }

    public boolean isPublic_traffic() {
        return public_traffic;
    }

    public void setPublic_traffic(boolean public_traffic) {
        this.public_traffic = public_traffic;
    }

    public long getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(long subscribers) {
        this.subscribers = subscribers;
    }

    public String getSubmission_type() {
        return submission_type;
    }

    public void setSubmission_type(String submission_type) {
        this.submission_type = submission_type;
    }

    public String getSubmit_link_label() {
        return submit_link_label;
    }

    public void setSubmit_link_label(String submit_link_label) {
        this.submit_link_label = submit_link_label;
    }

    public String getSubmit_text_label() {
        return submit_text_label;
    }

    public void setSubmit_text_label(String submit_text_label) {
        this.submit_text_label = submit_text_label;
    }

    public String getSubreddit_type() {
        return subreddit_type;
    }

    public void setSubreddit_type(String subreddit_type) {
        this.subreddit_type = subreddit_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUser_is_banned() {
        return user_is_banned;
    }

    public void setUser_is_banned(boolean user_is_banned) {
        this.user_is_banned = user_is_banned;
    }

    public boolean isUser_is_contributor() {
        return user_is_contributor;
    }

    public void setUser_is_contributor(boolean user_is_contributor) {
        this.user_is_contributor = user_is_contributor;
    }

    public boolean isUser_is_moderator() {
        return user_is_moderator;
    }

    public void setUser_is_moderator(boolean user_is_moderator) {
        this.user_is_moderator = user_is_moderator;
    }

    public boolean isUser_is_subscriber() {
        return user_is_subscriber;
    }

    public void setUser_is_subscriber(boolean user_is_subscriber) {
        this.user_is_subscriber = user_is_subscriber;
    }
}
