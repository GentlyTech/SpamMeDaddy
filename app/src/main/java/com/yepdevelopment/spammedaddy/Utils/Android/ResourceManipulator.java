package com.yepdevelopment.spammedaddy.Utils.Android;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.yepdevelopment.spammedaddy.R;
import com.yepdevelopment.spammedaddy.Types.Contributor;

import java.util.LinkedList;
import java.util.List;

/**
 * Util methods related to Android resources.
 */
public class ResourceManipulator {
    /**
     * This class should not be instantiated.
     */
    private ResourceManipulator() {

    }

    /**
     * Maps a string resource containing an array of contributors into a list of Contributor objects.
     *
     * @param context an Android Context object so that string resources can be retrieved
     * @return a list containing the Contributors found in the string resource
     */
    public static List<Contributor> getContributors(Context context) {
        LinkedList<Contributor> contributorsList = new LinkedList<>();

        Resources resources = context.getResources();
        try (TypedArray arrayOfStringIds = resources.obtainTypedArray(R.array.contributors)) {
            for (int i = 0; i < arrayOfStringIds.length(); i++) {
                int id = arrayOfStringIds.getResourceId(i, 0);
                if (id < 1) continue; // Invalid resource
                String[] contributorStringArray = resources.getStringArray(id);
                Contributor contributor = new Contributor(contributorStringArray[0], contributorStringArray[1], contributorStringArray[2]); // [0] is the name, [1] is the role, [2] is the imageUri
                contributorsList.add(contributor);
            }
            arrayOfStringIds.recycle();
        }

        return contributorsList;
    }
}
