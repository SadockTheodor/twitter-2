package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import com.codepath.apps.restclienttemplate.TimeFormatter;

@Parcel
public class Tweet {
    public String body;
    public String created_at;
    public long id;
    public User user;

    // empty constructor needed by the Parceler library

    public Tweet(){}
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.created_at = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
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
