package org.alcha.algalonj.models.wow.characters;

import org.alcha.algalonj.models.wow.WoWRewardItem;
import org.alcha.algalonj.models.wow.achievements.WoWAchievementCriteria;

/**
 * <p>Created by Alcha on 8/4/2017.</p>
 */

public class WoWCharacterFeedAchievement extends WoWCharacterFeedEntry {
    private int mId, mPoints;
    private String mTitle, mDescription, mIcon;
    private WoWRewardItem[] mRewardItems;
    private WoWAchievementCriteria[] mCriteria;

    private WoWCharacterFeedAchievement() {
        setEntryType(Type.ACHIEVEMENT);
    }

    public WoWCharacterFeedAchievement newInstance() {
        return new WoWCharacterFeedAchievement();
    }

    public WoWCharacterFeedAchievement newInstanceFromJSON(JSONObject jsonObject) {
        WoWCharacterFeedAchievement achievement = new WoWCharacterFeedAchievement();

        return achievement;
    }
}
