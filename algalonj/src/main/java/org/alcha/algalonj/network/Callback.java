package org.alcha.algalonj.network;

import org.alcha.algalonj.interfaces.OnTaskCompleted;
import com.google.gson.JsonObject;

/**
 * <p>Created by Alcha on 8/6/2017.</p>
 */

public abstract class Callback implements OnTaskCompleted {
    @Override
    public void onTaskCompleted(JSONObject response) {

    }
}
