package com.codepath.apps.restclienttemplate.models;

import android.provider.ContactsContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import com.codepath.apps.restclienttemplate.TimeFormatter;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity= User.class, parentColumns="id", childColumns="userId"))

public class Tweet {
    @PrimaryKey
    @ColumnInfo
    public long id;
    @ColumnInfo
    public String body;
    @ColumnInfo
    public String created_at;

    @ColumnInfo
    public long userId;

    @Ignore
    public User user;

    // empty constructor needed by the Parceler library

    public Tweet(){}
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        tweet.body = jsonObject.getString("text");
        tweet.created_at = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        User user = User.fromJson((jsonObject.getJSONObject("user")));
        tweet.user = user;
        tweet.userId = user.id;
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length() ; i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    public String getFormattedTimeStamp(){
        return TimeFormatter.getTimeDifference(created_at);
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
