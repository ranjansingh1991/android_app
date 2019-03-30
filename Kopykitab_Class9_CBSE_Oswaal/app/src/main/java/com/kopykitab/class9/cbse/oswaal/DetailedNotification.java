package com.kopykitab.class9.cbse.oswaal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.kopykitab.class9.cbse.oswaal.components.CircularProgressView;
import com.kopykitab.class9.cbse.oswaal.settings.AppSettings;
import com.kopykitab.class9.cbse.oswaal.settings.Constants;
import com.kopykitab.class9.cbse.oswaal.settings.Utils;
import com.kopykitab.class9.cbse.oswaal.R;

import android.annotation.SuppressLint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

class NotificationDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable divider;

    public NotificationDividerItemDecoration(Drawable divider) {
        this.divider = divider;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        // TODO Auto-generated method stub
        super.onDraw(c, parent);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }
}

class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View v;

        public ViewHolder(View v) {
            super(v);
            this.v = v;
        }
    }

    private Context mContext;
    private List<HashMap<String, String>> notificationsList;
    private View notificationView, lastExpandedView = null;
    private TextView notification_title, notification_date, notification_description;
    private LinearLayout notification_details;
    private int lastExpandedPosition = -1;

    public NotificationAdapter(Context c, List<HashMap<String, String>> allNotifications) {
        mContext = c;
        notificationsList = allNotifications;
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return notificationsList.size();
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO Auto-generated method stub
        View v = LayoutInflater.from(mContext).inflate(R.layout.notification_item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // TODO Auto-generated method stub
        final HashMap<String, String> notificationListItem = notificationsList.get(position);

        notificationView = holder.v;
        notification_title = (TextView) notificationView.findViewById(R.id.notification_title);
        notification_date = (TextView) notificationView.findViewById(R.id.notification_date);
        notification_details = (LinearLayout) notificationView.findViewById(R.id.notification_details);
        notification_description = (TextView) notificationView.findViewById(R.id.notification_description);

        notification_title.setText(notificationListItem.get("notification_title"));
        notification_date.setText(notificationListItem.get("notification_date"));

        notification_description.setText(notificationListItem.get("notification_description"));
        Button notificationButton = (Button) notificationView.findViewById(R.id.notification_button);
        if (notificationListItem.containsKey("notification_url")) {
            notificationButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (Utils.isNetworkConnected(mContext)) {
                        if (notificationListItem.get("notification_url").startsWith(Constants.BASE_URL + "app/")) {
                            Intent webViewIntent = new Intent(mContext, WebViewActivity.class);
                            webViewIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            webViewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            webViewIntent.putExtra("web_url", notificationListItem.get("notification_url"));
                            mContext.startActivity(webViewIntent);
                            ((Activity) mContext).getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(notificationListItem.get("notification_url")));
                            mContext.startActivity(intent);
                        }
                    } else {
                        Utils.networkNotAvailableAlertBox(mContext);
                    }
                }
            });
        } else {
            notificationButton.setVisibility(View.GONE);
        }

        if (lastExpandedPosition > -1 && lastExpandedPosition == position) {
            notification_title.setTextColor(mContext.getResources().getColor(R.color.action_bar_background));
            if (notification_details.getVisibility() == View.GONE) {
                notification_details.setVisibility(View.VISIBLE);
            } else {
                notification_details.setVisibility(View.GONE);
            }
        } else {
            notification_title.setTextColor(mContext.getResources().getColor(android.R.color.black));
            notification_details.setVisibility(View.GONE);
        }

        notificationView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                View tempView = v.findViewById(R.id.notification_details);
                if (lastExpandedPosition == position) {
                    lastExpandedPosition = -1;
                    collapseNotification(lastExpandedView);
                    lastExpandedView = null;
                } else {
                    lastExpandedPosition = position;
                    if (lastExpandedView != null) {
                        collapseNotification(lastExpandedView);
                    }
                    lastExpandedView = tempView;
                    expandNotification(tempView);
                }
            }
        });
    }

    private void expandNotification(final View v) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                // TODO Auto-generated method stub
                v.getLayoutParams().height = interpolatedTime < 1.0f ? (int) (targetHeight * interpolatedTime) : LayoutParams.WRAP_CONTENT;
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        LinearLayout notificationLayout = (LinearLayout) v.getParent();
        ((TextView) notificationLayout.findViewById(R.id.notification_title)).setTextColor(mContext.getResources().getColor(R.color.action_bar_background));
        v.startAnimation(a);
    }

    private void collapseNotification(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime < 1.0f) {
                    v.getLayoutParams().height = (int) (initialHeight * (1 - interpolatedTime));
                    v.requestLayout();
                } else {
                    v.setVisibility(View.GONE);
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        LinearLayout notificationLayout = (LinearLayout) v.getParent();
        ((TextView) notificationLayout.findViewById(R.id.notification_title)).setTextColor(mContext.getResources().getColor(android.R.color.black));
        v.startAnimation(a);
    }
}

@SuppressLint("ValidFragment")
public class DetailedNotification extends Fragment {

    private Context mContext;
    private int notificationType;
    private ViewFlipper notificationFlip;
    private RecyclerView notificationResultsListView;
    private View rootView;
    NotificationAdapter notificationAadapter;

    public DetailedNotification() { }

    public DetailedNotification(Context mContext, int notificationType) {
        this.notificationType = notificationType;
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.notification_list, container, false);
        notificationFlip = (ViewFlipper) rootView.findViewById(R.id.notification_flip);

        notificationResultsListView = (RecyclerView) rootView.findViewById(R.id.notification_results);
        notificationResultsListView.setHasFixedSize(true);

        notificationResultsListView.setLayoutManager(new LinearLayoutManager(mContext));
        notificationResultsListView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(mContext, R.drawable.item_divider)));

        if (Utils.isNetworkConnected(mContext)) {
            notificationFlip.setDisplayedChild(0);

            new GetNotificationByNotificationType().execute();
        } else {
            notificationFlip.setDisplayedChild(2);
        }

        return rootView;
    }

    private class GetNotificationByNotificationType extends AsyncTask<Void, Void, List<HashMap<String, String>>> {

        @Override
        protected List<HashMap<String, String>> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            List<HashMap<String, String>> allNotifications = new ArrayList<HashMap<String, String>>();

            List<NameValuePair> postParams = new ArrayList<NameValuePair>();
            postParams.add(new BasicNameValuePair("notification_type", Integer.toString(notificationType)));
            postParams.add(new BasicNameValuePair("customer_id", AppSettings.getInstance(mContext).get("CUSTOMER_ID")));
            postParams.add(new BasicNameValuePair("source", Constants.LOGIN_SOURCE));

            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpEntity httpEntity = null;
                HttpResponse httpResponse = null;

                HttpPost httpPost = new HttpPost(Constants.NOTIFICATIONS_API_URL);
                httpPost.setEntity(new UrlEncodedFormEntity(postParams));

                httpResponse = httpClient.execute(httpPost);

                httpEntity = httpResponse.getEntity();
                String response = EntityUtils.toString(httpEntity);
                Log.i("Notification Type " + notificationType + " Details", response);

                JSONObject notificationsJson = new JSONObject(response);
                JSONArray notificationsJsonArray = notificationsJson.getJSONArray("all_notifications");
                for (int i = 0; i < notificationsJsonArray.length(); i++) {
                    JSONObject tempNotification = notificationsJsonArray.getJSONObject(i);
                    HashMap<String, String> notification = new HashMap<String, String>();
                    notification.put("notification_title", tempNotification.getString("notification_title"));
                    notification.put("notification_date", tempNotification.getString("notification_date"));
                    notification.put("notification_description", tempNotification.getString("notification_description"));
                    if (tempNotification.has("notification_url")) {
                        notification.put("notification_url", tempNotification.getString("notification_url"));
                    }

                    allNotifications.add(notification);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return allNotifications;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> allNotifications) {
            // TODO Auto-generated method stub
            if (allNotifications.size() > 0) {
                notificationAadapter = new NotificationAdapter(mContext, allNotifications);
                notificationResultsListView.setLayoutManager(new LinearLayoutManager(mContext));
                notificationResultsListView.setAdapter(notificationAadapter);
            } else {
                notificationFlip.setDisplayedChild(1);
            }
            CircularProgressView notificationProgress = (CircularProgressView) rootView.findViewById(R.id.notification_progress);
            notificationProgress.setVisibility(View.GONE);
        }
    }
}