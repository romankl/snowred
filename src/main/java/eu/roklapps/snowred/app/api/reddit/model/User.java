package eu.roklapps.snowred.app.api.reddit.model;

import android.content.Context;

import eu.roklapps.snowred.app.api.reddit.access.Connection;
import eu.roklapps.snowred.app.api.reddit.callbacks.Result;

public class User {
    private long comment_karma;
    private long created;
    private long created_utc;
    private boolean has_mail;
    private boolean has_mod_mail;
    private boolean has_verified_email;
    private String id;
    private boolean is_friend;
    private boolean is_gold;
    private boolean is_mod;
    private long link_karma;
    private String name;
    private boolean over_18;

    public long getComment_karma() {
        return comment_karma;
    }

    public void setComment_karma(long comment_karma) {
        this.comment_karma = comment_karma;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getCreated_utc() {
        return created_utc;
    }

    public void setCreated_utc(long created_utc) {
        this.created_utc = created_utc;
    }

    public boolean isHas_mail() {
        return has_mail;
    }

    public void setHas_mail(boolean has_mail) {
        this.has_mail = has_mail;
    }

    public boolean isHas_mod_mail() {
        return has_mod_mail;
    }

    public void setHas_mod_mail(boolean has_mod_mail) {
        this.has_mod_mail = has_mod_mail;
    }

    public boolean isHas_verified_email() {
        return has_verified_email;
    }

    public void setHas_verified_email(boolean has_verified_email) {
        this.has_verified_email = has_verified_email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIs_friend() {
        return is_friend;
    }

    public void setIs_friend(boolean is_friend) {
        this.is_friend = is_friend;
    }

    public boolean isIs_gold() {
        return is_gold;
    }

    public void setIs_gold(boolean is_gold) {
        this.is_gold = is_gold;
    }

    public boolean isIs_mod() {
        return is_mod;
    }

    public void setIs_mod(boolean is_mod) {
        this.is_mod = is_mod;
    }

    public long getLink_karma() {
        return link_karma;
    }

    public void setLink_karma(long link_karma) {
        this.link_karma = link_karma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOver_18() {
        return over_18;
    }

    public void setOver_18(boolean over_18) {
        this.over_18 = over_18;
    }

    public void aboutUser(String username, Context context, Result result) {
        Connection connection = new Connection("http://www.reddit.com/user/" + username + "/about.json", context);
        if (CurrentUser.getInstance().getCredentials() != null) {
            connection.setParams(CurrentUser.getInstance().getCredentials().getCookieAndModhash());
        }
        connection.setCallback(result).performGetOperation();
    }
}
