package com.kopykitab.class9.cbse.oswaal.settings;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public final class Constants {

    // URLs
    public static final String BASE_URL = "https://www.kopykitab.com/";
    public static final String INDEX_URL = BASE_URL + "index.php";
    public static String IMAGE_URL = BASE_URL + "image/cache/";
    public static final String ICON_URL = BASE_URL + "image/cache/icons/";

    //API URLs
    public static final String LOGIN_API_URL = INDEX_URL + "?route=account/applogin/";
    public static final String FORGOT_PASSWORD_API_URL = LOGIN_API_URL + "forgotten";
    public static final String REGISTER_API_URL = LOGIN_API_URL + "register";
    public static final String NOTIFICATIONS_API_URL = LOGIN_API_URL + "getNotificationsByNotificationType";
    public static final String SYNCDATA_API_URL = LOGIN_API_URL + "syncAllDataByCategories";
    public static final String FEEDBACK_API_URL = LOGIN_API_URL + "feedback";
    public static final String CATEGORIES_URL = LOGIN_API_URL + "getCategoriesList";
    public static final String RECOMMENDATIONS_URL = LOGIN_API_URL + "recommendations";
    public static final String LOGOUT_URL = LOGIN_API_URL + "logout";
    public static final String STREAMS_URL = LOGIN_API_URL + "getStreams";
    public static final String ADD_UPDATE_STREAMS_URL = LOGIN_API_URL + "addStreams";
    public static final String GA_TRIGGER_OFFLINE_URL = LOGIN_API_URL + "offlineData";
    public static final String BANNERS_URL = LOGIN_API_URL + "banners";
    public static final String PROMOTIONAL_BANNERS_URL = LOGIN_API_URL + "promotionalBanners";

    //Site URLs
    public static final String LOGIN_WITH_FB_API_URL = INDEX_URL + "?route=account/register/registerWithFB";
    public static final String LOGIN_WITH_GOOGLE_API_URL = INDEX_URL + "?route=account/register/registerWithGoogle";
    public static final String PRODUCT_RECOMMENDATIONS_URL = INDEX_URL + "?route=product/product/recommendations";
    public static final String LIVE_SEARCH_URL = INDEX_URL + "?route=product/live_search";
    public static final String CART_URL = INDEX_URL + "?route=checkout/checkout";
    public static final String SCORECARD_URL = INDEX_URL + "?route=account/dashboard/study";
    public static final String WISHLIST_URL = INDEX_URL + "?route=account/wishlist";
    public static final String WHATSNEW_URL = INDEX_URL + "?route=information/whatsnew";
    public static final String REFER_N_EARN_URL = INDEX_URL + "?route=account/referral";
    public static final String ACCOUNT_SETTINGS_URL = INDEX_URL + "?route=account/settings";
    public static final String ACCOUNT_PROFILE_URL = INDEX_URL + "?route=account/account";
    public static final String SEARCH_IN_STORE_URL = INDEX_URL + "?route=product/search";
    public static final String FIND_MORE_URL = INDEX_URL  + "?route=product/product/related";

    // Paths
    public static final String SYNCDATA_JSON_FILENAME = "sync_all_data_by_categories.json";
    public static final String GA_TRIGGER_OFFLINE_DATA_JSON_FILENAME = "data.json";
    public static final String ANNOT_JSON_FILENAME = "annots_n.json";
    public static final String OLD_ANNOT_JSON_FILENAME = "annots.json";
    public static final String BOOKMARKED_PAGE_JSON_FILENAME = "annots_bookmarks.json";
    public static final String ANNOT_ADD_NOTES_JSON_FILENAME = "annots_notes.json";
    public static final String LOGIN_SOURCE = "android_app_CBSE_9_OSWAAL";
    public static final String FORMATTED_LOGIN_SOURCE = "CBSE Class 9";

    public static final int FETCH_SEARCH_TEXT = 20;

    //GA related details
    public static final String GA_TRACKING_ID = "UA-30674928-10";

    //GCM related details
    public static final String GCM_PROJECT_ID = "1034096850870";
    public static final String STORE_GCM_DETAILS_URL = LOGIN_API_URL + "addGCMRegistration";

    // common function for showing of toast
    static Toast toastActivity = null;

    public static void showToast(String text, Context mContext) {
        if (toastActivity != null) {
            View v = toastActivity.getView();
            if (v.isShown()) {
                toastActivity.setText(text);
                return;
            }
            toastActivity.cancel();
            toastActivity = null;
        }
        toastActivity = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
        toastActivity.show();
    }

    public static void cancelToast() {
        if (toastActivity != null) {
            toastActivity.cancel();
            toastActivity = null;
        }
    }

    public static void setImageBaseUrl(String imageBaseUrl) {
        IMAGE_URL = imageBaseUrl;
    }

    static String activateBottomNavigationWidgetButton = "";

    public static void setActivateBottomNavigationWidgetButton(String activateButton) {
        activateBottomNavigationWidgetButton = activateButton;
    }

    public static String getActivateBottomNavigationWidgetButton() {
        return activateBottomNavigationWidgetButton;
    }

}
