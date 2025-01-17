package org.alcha.algalonj.models.wow;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.TimeZone;

/**
 * <p>Created by Alcha on 8/1/2017.</p>
 */

public class WoWServerRealm {
    private static final String LOG_TAG = WoWServerRealm.class.getSimpleName();
    private String[] mConnectedRealms;
    private String mBattlegroup;

    private enum Population {
        High,
        Medium,
        Low,
        Full,
        Undefined
    }

    private Population mPopulation;
    private TimeZone mTimezone;
    private boolean mStatus;
    private boolean mQueue;
    private String mLocale;
    private String mServerName;
    private String mType;

    public WoWServerRealm() {
    }

    public WoWServerRealm(String serverName) {
        mServerName = serverName;
    }

    public WoWServerRealm(JsonObject realmData) {
        JsonArray connectedRealms = realmData.getAsJsonArray("connected_realms");

        for (int x = 0; x < connectedRealms.size(); x++) {
            System.out.println(connectedRealms);
        }
    }

    @Override
    public String toString() {
        if (mServerName.length() > 0 && mBattlegroup.length() > 0)
            return mServerName + " - " + mBattlegroup + " - " + mPopulation;
        else
            return "";
    }

    public static WoWServerRealm[] convertJSONArray(JsonArray array) {
        WoWServerRealm[] tempRealms = new WoWServerRealm[array.size()];

            for (int x = 0; x < array.size(); x++) {
                JsonObject realmObj = array.get(x).getAsJsonObject();
                WoWServerRealm realm = new WoWServerRealm(realmObj.get("name").getAsString());

                realm.setBattlegroup(realmObj.get("battlegroup").getAsString());
                realm.setType(realmObj.get("type").getAsString());
                realm.setQueue(Boolean.getBoolean(realmObj.get("queue").getAsString()));
                realm.setStatus(Boolean.getBoolean(realmObj.get("status").getAsString()));
                realm.setLocale(realmObj.get("locale").getAsString());
                realm.setConnectedRealms(realmObj.getAsJsonArray("connected_realms"));
                realm.setPopulation(realmObj.get("population").getAsString());
                realm.setTimezone(TimeZone.getTimeZone(realmObj.get("timezone").getAsString()));

                tempRealms[x] = realm;
            }


        return tempRealms;
    }

    public String formatConnectedRealms() {
        String tempString = "";

        for (int x = 0; x < mConnectedRealms.length; x++) {
            tempString += "- " + mConnectedRealms[x] + " ";
        }

        return tempString;
    }

    public String[] getConnectedRealms() {
        return mConnectedRealms;
    }

    void setConnectedRealms(JsonArray array) {
        mConnectedRealms = new String[array.size()];

        for (int x = 0; x < array.size(); x++) {
            String input = array.get(x).getAsString();
            String serverName = cleanServerName(input);

            mConnectedRealms[x] = serverName;
        }
    }

    void setConnectedRealms(String[] connectedRealms) {
        if (connectedRealms != null) {
            mConnectedRealms = connectedRealms;
        }
    }

    private String cleanServerName(String input) {
        String temp = input.substring(0, 1).toUpperCase() + input.substring(1);
        int hyphenLoc = temp.indexOf('-');

        while (hyphenLoc > -1) {
            temp = temp.substring(0, hyphenLoc + 1) + temp.substring(hyphenLoc + 1, hyphenLoc + 2).toUpperCase() + temp.substring(hyphenLoc + 2);
            hyphenLoc = temp.indexOf('-', hyphenLoc + 1);
        }

        if (temp.contains("-")) {
            temp = temp.replace("-", " ");
        }

        return temp;
    }

    public String getBattlegroup() {
        return mBattlegroup;
    }

    void setBattlegroup(String battlegroup) {
        mBattlegroup = battlegroup;
    }

    public Population getPopulation() {
        return mPopulation;
    }

    void setPopulation(String population) {
        switch (population.toLowerCase()) {
            case "low": {
                mPopulation = Population.Low;
                break;
            }
            case "medium": {
                mPopulation = Population.Medium;
                break;
            }
            case "high": {
                mPopulation = Population.High;
                break;
            }
            case "full": {
                mPopulation = Population.Full;
                break;
            }
            case "n/a": {
                mPopulation = Population.Undefined;
                break;
            }
            default: {
                System.out.println("Do wut? " + population);
                break;
            }
        }
    }

    public TimeZone getTimezone() {
        return mTimezone;
    }

    void setTimezone(TimeZone timezone) {
        mTimezone = timezone;
    }

    public boolean isStatus() {
        return mStatus;
    }

    void setStatus(boolean status) {
        mStatus = status;
    }

    public boolean ismQueue() {
        return mQueue;
    }

    void setQueue(boolean queue) {
        mQueue = queue;
    }

    public String getLocale() {
        return mLocale;
    }

    void setLocale(String locale) {
        mLocale = locale;
    }

    public String getServerName() {
        return mServerName;
    }

    void setServerName(String serverName) {
        mServerName = serverName;
    }

    public String getType() {
        return mType;
    }

    void setType(String type) {
        mType = type;
    }
}
