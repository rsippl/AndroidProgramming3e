package com.bignerdranch.android.mockwalker;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class MockWalk extends HandlerThread {
    private static final String TAG = "MockWalk";
    private boolean mEnabled = true;
    private Handler mHandler;
    private GoogleApiClient mClient;
    private List<Location> mLocations;
    private int mCurrentLocationIndex = 0;
    private static final long PERIOD_LENGTH_MS = 1000;

    private Runnable mLocationUpdate = new Runnable() {
        @Override
        public void run() {
            postNextLocation();

            scheduleNextUpdate();
        }

    };

    public MockWalk(GoogleApiClient client) {
        super("MockWalk");
        mClient = client;
        mLocations = getLocationData();
        updateClientMockState();
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
        if (isEnabled()) {
            scheduleNextUpdate();
        }

        updateClientMockState();
    }

    private void updateClientMockState() {
        LocationServices.FusedLocationApi
                .setMockMode(mClient, isEnabled());
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();

        mHandler = new Handler();
        scheduleNextUpdate();
    }

    @Override
    public boolean quit() {
        mHandler.removeCallbacks(mLocationUpdate);
        return super.quit();
    }

    private void scheduleNextUpdate() {
        if (isAlive() && !isInterrupted() && isEnabled()) {
            mHandler.postDelayed(this.mLocationUpdate, PERIOD_LENGTH_MS);
        }
    }

    private void postNextLocation() {
        int nextIndex = (mCurrentLocationIndex + 1) % mLocations.size();

        Location nextLocation = new Location(mLocations.get(nextIndex));
        nextLocation.setTime(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            nextLocation.setElapsedRealtimeNanos(System.nanoTime());
        }

        LocationServices.FusedLocationApi
                .setMockLocation(mClient, nextLocation);

        Log.i(TAG, "New location: " + nextLocation);
        mCurrentLocationIndex = nextIndex;
    }

    private Location newLocation(double lat, double lon, double alt) {
        Location l = new Location("MOCK");
        l.setLatitude(lat);
        l.setLongitude(lon);
        l.setAltitude(alt);
        l.setAccuracy(0.5f);

        return l;
    }

    private List<Location> getLocationData() {
        List<Location> locations = new ArrayList<Location>();

        locations.add(newLocation(33.751459, -84.3238770, 309));
        locations.add(newLocation(33.7514752, -84.3238663, 304));
        locations.add(newLocation(33.7514859, -84.3238610, 303));
        locations.add(newLocation(33.7514752, -84.32385, 303));
        locations.add(newLocation(33.7514859, -84.3237590, 287));
        locations.add(newLocation(33.7514859, -84.3237376, 286));
        locations.add(newLocation(33.7514805, -84.3237537, 286));
        locations.add(newLocation(33.7514430, -84.3237537, 283));
        locations.add(newLocation(33.7514108, -84.3237483, 286));
        locations.add(newLocation(33.7514162, -84.3237590, 287));
        locations.add(newLocation(33.7514269, -84.3237751, 287));
        locations.add(newLocation(33.7514376, -84.3237859, 287));
        locations.add(newLocation(33.7514698, -84.3238180, 283));
        locations.add(newLocation(33.7514805, -84.3238288, 282));
        locations.add(newLocation(33.7515181, -84.323887, 280));
        locations.add(newLocation(33.7515234, -84.3238985, 279));
        locations.add(newLocation(33.7515342, -84.3239146, 279));
        locations.add(newLocation(33.7515449, -84.3239307, 279));
        locations.add(newLocation(33.7515503, -84.3239468, 280));
        locations.add(newLocation(33.7515556, -84.323957, 280));
        locations.add(newLocation(33.751566, -84.3239682, 280));
        locations.add(newLocation(33.7515717, -84.3239790, 280));
        locations.add(newLocation(33.7515825, -84.3239897, 280));
        locations.add(newLocation(33.75159323, -84.3240058, 280));
        locations.add(newLocation(33.75160932, -84.3240165, 280));
        locations.add(newLocation(33.75161468, -84.3240272, 280));
        locations.add(newLocation(33.7510514, -84.3247300, 277));
        locations.add(newLocation(33.751062, -84.3247246, 276));
        locations.add(newLocation(33.75108897, -84.3247032, 276));
        locations.add(newLocation(33.7510782, -84.3246924, 280));
        locations.add(newLocation(33.7510836, -84.3247032, 282));
        locations.add(newLocation(33.75109970, -84.324697, 281));
        locations.add(newLocation(33.75112652, -84.324697, 283));
        locations.add(newLocation(33.75115334, -84.3246924, 281));
        locations.add(newLocation(33.7511640, -84.3246871, 284));
        locations.add(newLocation(33.7511748, -84.3246871, 284));
        locations.add(newLocation(33.7511855, -84.3246871, 284));
        locations.add(newLocation(33.7511962, -84.3246871, 284));
        locations.add(newLocation(33.751206, -84.3246871, 285));
        locations.add(newLocation(33.7512177, -84.3246763, 286));
        locations.add(newLocation(33.7512284, -84.3246763, 287));
        locations.add(newLocation(33.7512391, -84.3246763, 288));
        locations.add(newLocation(33.7512499, -84.3246817, 287));
        locations.add(newLocation(33.75126, -84.3246763, 287));
        locations.add(newLocation(33.7512713, -84.3246763, 287));
        locations.add(newLocation(33.7512820, -84.3246710, 287));
        locations.add(newLocation(33.7512981, -84.3246710, 287));
        locations.add(newLocation(33.751314, -84.3246763, 286));
        locations.add(newLocation(33.7513303, -84.3246710, 286));
        locations.add(newLocation(33.75135183, -84.324660, 287));
        locations.add(newLocation(33.75136792, -84.3246495, 287));
        locations.add(newLocation(33.75137865, -84.3246495, 287));
        locations.add(newLocation(33.75140011, -84.3246388, 286));
        locations.add(newLocation(33.7514108, -84.3246281, 286));
        locations.add(newLocation(33.7514269, -84.3246227, 286));
        locations.add(newLocation(33.7514376, -84.3246120, 285));
        locations.add(newLocation(33.7514537, -84.324606, 285));
        locations.add(newLocation(33.7514644, -84.3246012, 285));
        locations.add(newLocation(33.7514752, -84.3245959, 285));
        locations.add(newLocation(33.7514805, -84.3245851, 285));
        locations.add(newLocation(33.7515020, -84.3245798, 286));
        locations.add(newLocation(33.751512, -84.3245744, 286));
        locations.add(newLocation(33.7515234, -84.3245637, 286));
        locations.add(newLocation(33.7515288, -84.324553, 287));
        locations.add(newLocation(33.7515395, -84.3245476, 287));
        locations.add(newLocation(33.7515503, -84.3245422, 287));
        locations.add(newLocation(33.751566, -84.3245315, 288));
        locations.add(newLocation(33.7515717, -84.3245208, 288));
        locations.add(newLocation(33.7515825, -84.3245154, 288));
        locations.add(newLocation(33.75158786, -84.324499, 290));
        locations.add(newLocation(33.75160396, -84.3244940, 290));
        locations.add(newLocation(33.75161468, -84.3244832, 291));
        locations.add(newLocation(33.75162541, -84.3244725, 292));
        locations.add(newLocation(33.75164151, -84.324461, 292));
        locations.add(newLocation(33.75165224, -84.324445, 293));
        locations.add(newLocation(33.7516629, -84.3244349, 293));
        locations.add(newLocation(33.7516736, -84.3244242, 294));
        locations.add(newLocation(33.7516790, -84.3244135, 294));
        locations.add(newLocation(33.7516897, -84.3244028, 294));
        locations.add(newLocation(33.7516897, -84.3243813, 294));
        locations.add(newLocation(33.7516844, -84.3243598, 295));
        locations.add(newLocation(33.7516790, -84.3243438, 295));
        locations.add(newLocation(33.7516844, -84.3243277, 295));
        locations.add(newLocation(33.7516790, -84.3243062, 295));
        locations.add(newLocation(33.7516736, -84.3242955, 296));
        locations.add(newLocation(33.7516683, -84.3242794, 297));
        locations.add(newLocation(33.7516629, -84.3242686, 296));
        locations.add(newLocation(33.75165224, -84.3242526, 297));
        locations.add(newLocation(33.75164151, -84.3242365, 297));
        locations.add(newLocation(33.75162541, -84.3242204, 297));
        locations.add(newLocation(33.75162005, -84.3242043, 297));
        locations.add(newLocation(33.75161468, -84.3241882, 298));
        locations.add(newLocation(33.75160396, -84.3241775, 299));
        locations.add(newLocation(33.75159859, -84.3241667, 298));
        locations.add(newLocation(33.75159323, -84.324156, 299));
        locations.add(newLocation(33.7515771, -84.3241453, 299));
        locations.add(newLocation(33.751566, -84.3241345, 298));
        locations.add(newLocation(33.7515556, -84.3241184, 298));
        locations.add(newLocation(33.751566, -84.3241077, 297));
        locations.add(newLocation(33.7515556, -84.3240863, 296));
        locations.add(newLocation(33.7515503, -84.3240702, 296));
        locations.add(newLocation(33.7515449, -84.3240541, 296));
        locations.add(newLocation(33.7515288, -84.3240380, 297));
        locations.add(newLocation(33.7515234, -84.3240272, 297));
        locations.add(newLocation(33.7515181, -84.3240058, 297));
        locations.add(newLocation(33.7515074, -84.323995, 297));
        locations.add(newLocation(33.7515020, -84.3239790, 296));
        locations.add(newLocation(33.7514913, -84.3239629, 295));
        locations.add(newLocation(33.7514805, -84.3239521, 294));
        locations.add(newLocation(33.7514644, -84.323941, 294));
        locations.add(newLocation(33.7514537, -84.3239307, 293));
        locations.add(newLocation(33.7514483, -84.3239146, 294));
        locations.add(newLocation(33.7514483, -84.3238985, 293));
        locations.add(newLocation(33.7514483, -84.3238770, 293));
        locations.add(newLocation(33.7514483, -84.32385, 292));
        locations.add(newLocation(33.7514537, -84.3238395, 291));
        locations.add(newLocation(33.751459, -84.3238234, 291));
        locations.add(newLocation(33.7514698, -84.3238019, 291));
        locations.add(newLocation(33.7514805, -84.3237859, 291));
        locations.add(newLocation(33.7514859, -84.3237751, 291));
        locations.add(newLocation(33.7514913, -84.3237644, 291));
        locations.add(newLocation(33.7514913, -84.3237483, 290));
        locations.add(newLocation(33.7515020, -84.323742, 290));
        locations.add(newLocation(33.7515074, -84.3237322, 290));
        locations.add(newLocation(33.7515181, -84.3237268, 290));
        locations.add(newLocation(33.7515288, -84.3237161, 290));
        locations.add(newLocation(33.7515342, -84.323705, 290));
        locations.add(newLocation(33.7515181, -84.3237000, 290));
        locations.add(newLocation(33.7515074, -84.3237161, 290));
        locations.add(newLocation(33.7514966, -84.3237376, 290));
        locations.add(newLocation(33.7514913, -84.3237537, 290));
        locations.add(newLocation(33.7514859, -84.3237751, 290));
        locations.add(newLocation(33.7514966, -84.3237751, 290));
        locations.add(newLocation(33.7515020, -84.323796, 290));
        locations.add(newLocation(33.751512, -84.323796, 290));
        locations.add(newLocation(33.7515234, -84.3238127, 289));
        locations.add(newLocation(33.7514966, -84.3237912, 289));
        locations.add(newLocation(33.7514859, -84.3237805, 289));
        locations.add(newLocation(33.751459, -84.3237698, 289));
        locations.add(newLocation(33.7514537, -84.3237805, 289));
        locations.add(newLocation(33.7514430, -84.3237751, 290));

        return locations;
    }
}
