package org.alcha.algalona.models.wow.characters;

import android.util.Log;

import org.alcha.algalona.models.wow.WoWBattlegroup;
import org.alcha.algalona.models.wow.WoWCharacterClass;
import org.alcha.algalona.models.wow.WoWFaction;
import org.alcha.algalona.models.wow.WoWRace;
import org.alcha.algalona.models.wow.realms.WoWRealms;
import org.alcha.algalona.models.wow.guilds.WoWGuild;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.SortedMap;

/**
 * <p>Created by Alcha on 8/1/2017.</p>
 */

public class WoWCharacter {
    private static final String LOG_TAG = "WoWCharacter";
    private int mLastModified;
    private String mName;
    private WoWRealms mRealm;
    private WoWBattlegroup mBattlegroup;
    private WoWCharacterClass mCharacterClass;
    private WoWRace mRace;
    private int mGender;
    private int mLevel;
    private int mAchievementPoints;
    private String mThumbnail;
    private String mCalcClass;
    private WoWFaction mFaction;
    private int mTotalHonorableKills;
    private SortedMap<WoWCharacterField.Type, WoWCharacterField> mFieldMap;

    private WoWCharacter() {

    }

    public static WoWCharacter newInstanceFromJSON(JSONObject characterJson) {
        WoWCharacter character = new WoWCharacter();

        try {
            // Required parameters
            character.setLastModified(characterJson.getInt("lastModified"));
            character.setName(characterJson.getString("name"));
            character.setRealm(WoWRealms.fromString(characterJson.getString("realm")));
            character.setBattlegroup(WoWBattlegroup.valueOf(characterJson.getString("battlegroup")));
            character.setCharacterClass(WoWCharacterClass.fromId(characterJson.getInt("class")));
            character.setRace(WoWRace.fromId(characterJson.getInt("race")));
            character.setGender(characterJson.getInt("gender"));
            character.setLevel(characterJson.getInt("level"));
            character.setAchievementPoints(characterJson.getInt("achievementPoints"));
            character.setThumbnail(characterJson.getString("thumbnail"));
            character.setCalcClass(characterJson.getString("calcClass"));
            character.setFaction(WoWFaction.fromId(characterJson.getInt("faction")));
            character.setTotalHonorableKills(characterJson.getInt("totalHonorableKills"));

            // Optional parameters
            character = parseOptionalCharacterFields(character, characterJson);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return character;
    }

    /**
     * Parses the given {@link JSONObject} for any possible
     * {@link WoWCharacterField WoWCharacterFields} that may be present. If one is located, it is
     * passed to the <code>newInstanceFromJSON(jsonObject)</code> method in the objects
     * representative class (e.g. {@link WoWCharacterAppearance},
     * {@link WoWGuild WoWGuild}, {@link WoWCharacterAchievements})
     * and added to the provided {@link WoWCharacter}. After all possible options are tested for
     * their existence, the class is returned with any modifications if any are made.
     *
     * @param character     WoWCharacter object you wish to add any fields to if they are present
     * @param characterJson JSONObject that potentially contains one or more character fields
     *
     * @return the provided class with any modifications if any are made
     */
    private static WoWCharacter parseOptionalCharacterFields(WoWCharacter character, JSONObject characterJson) {
        /*
         * There is a try/catch as is required because I'm accessing the JSONObject. That being
         * said, there are two ways this would ever occur within this portion. Since I verify the
         * characterJson has the given field before attempting to get the JSONObject that the field
         * contains, no errors would be thrown.
         *
         * The first way an error could occur would be if Blizzard modifies the API so that the
         * field names are no longer as they are typed here. If that happens though, much much more
         * of the library will break as well.
         *
         * The second way is if I simply type one of the fields incorrectly below..
         */
        try {
            if (characterJson.has("achievements"))
                character.addField(WoWCharacterAchievements.newInstanceFromJSON(characterJson.getJSONObject("achievements")));

            if (characterJson.has("appearance"))
                character.addField(WoWCharacterAppearance.newInstanceFromJSON(characterJson.getJSONObject("appearance")));

            if (characterJson.has("feed"))
                character.addField(WoWCharacterFeed.newInstanceFromJSON(characterJson.getJSONObject("feed")));

            if (characterJson.has("guild"))
                character.addField(WoWCharacterGuild.newInstanceFromJSON(characterJson.getJSONObject("guild")));

            if (characterJson.has("hunterPets"))
                Log.d(LOG_TAG, "newInstanceFromJSON: hunterPets != null");

            if (characterJson.has("items"))
                Log.d(LOG_TAG, "newInstanceFromJSON: items != null");
            if (characterJson.has("mounts"))
                Log.d(LOG_TAG, "newInstanceFromJSON: mounts != null");
            if (characterJson.has("pets"))
                Log.d(LOG_TAG, "newInstanceFromJSON: pets != null");
            if (characterJson.has("petSlots"))
                Log.d(LOG_TAG, "newInstanceFromJSON: petSlots != null");
            if (characterJson.has("professions"))
                Log.d(LOG_TAG, "newInstanceFromJSON: professions != null");
            if (characterJson.has("progression"))
                Log.d(LOG_TAG, "newInstanceFromJSON: progression != null");
            if (characterJson.has("pvp"))
                Log.d(LOG_TAG, "newInstanceFromJSON: pvp != null");
            if (characterJson.has("quests"))
                Log.d(LOG_TAG, "newInstanceFromJSON: quests != null");
            if (characterJson.has("reputation"))
                Log.d(LOG_TAG, "newInstanceFromJSON: reputation != null");
            if (characterJson.has("statistics"))
                Log.d(LOG_TAG, "newInstanceFromJSON: statistics != null");
            if (characterJson.has("stats"))
                Log.d(LOG_TAG, "newInstanceFromJSON: stats != null");
            if (characterJson.has("talents"))
                Log.d(LOG_TAG, "newInstanceFromJSON: talents != null");
            if (characterJson.has("titles"))
                Log.d(LOG_TAG, "newInstanceFromJSON: titles != null");
            if (characterJson.has("audit"))
                Log.d(LOG_TAG, "newInstanceFromJSON: audit != null");

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return character;
    }

    public void addField(WoWCharacterField characterField) {
        mFieldMap.put(characterField.getFieldType(), characterField);
    }

    public WoWCharacterField getField(WoWCharacterField.Type characterField) {
        return mFieldMap.get(characterField.toString());
    }

    public int getTotalHonorableKills() {
        return mTotalHonorableKills;
    }

    public void setTotalHonorableKills(int totalHonorableKills) {
        mTotalHonorableKills = totalHonorableKills;
    }

    public String getCalcClass() {
        return mCalcClass;
    }

    public void setCalcClass(String calcClass) {
        mCalcClass = calcClass;
    }

    public WoWFaction getFaction() {
        return mFaction;
    }

    public void setFaction(WoWFaction faction) {
        mFaction = faction;
    }

    public int getLastModified() {
        return mLastModified;
    }

    public void setLastModified(int lastModified) {
        mLastModified = lastModified;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public WoWRealms getRealm() {
        return mRealm;
    }

    public void setRealm(WoWRealms realm) {
        mRealm = realm;
    }

    public WoWBattlegroup getBattlegroup() {
        return mBattlegroup;
    }

    public void setBattlegroup(WoWBattlegroup battlegroup) {
        mBattlegroup = battlegroup;
    }

    public WoWCharacterClass getCharacterClass() {
        return mCharacterClass;
    }

    public void setCharacterClass(WoWCharacterClass characterClass) {
        mCharacterClass = characterClass;
    }

    public WoWRace getRace() {
        return mRace;
    }

    public void setRace(WoWRace race) {
        mRace = race;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public int getAchievementPoints() {
        return mAchievementPoints;
    }

    public void setAchievementPoints(int achievementPoints) {
        mAchievementPoints = achievementPoints;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        mThumbnail = thumbnail;
    }
}

