package com.bignerdranch.android.mockwalker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MockWalkerFragment extends Fragment {
    @InjectView(R.id.start_button) CompoundButton mStartButton;
    private CompositeSubscription mServiceSubscriptions = new CompositeSubscription();
    private Intent mServiceIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mockwalker, container, false);

        ButterKnife.inject(this, v);

        mServiceIntent = new Intent(getActivity(), MockWalkerService.class);

        mServiceSubscriptions.add(MockWalker.get(getActivity()).getChanges()
                .subscribe(new Action1<MockWalker>() {
                    @Override
                    public void call(MockWalker mockWalker) {
                        updateUI();
                    }
                }));

        updateUI();

        return v;
    }

    @OnClick(R.id.start_button)
    public void onStartButtonClick() {
        MockWalker mockWalker = MockWalker.get(getActivity());

        if (mockWalker.isStarted()) {
            getActivity().stopService(mServiceIntent);
        } else {
            getActivity().startService(mServiceIntent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mServiceSubscriptions.unsubscribe();
    }

    private void updateUI() {
        MockWalker mockWalker = MockWalker.get(getActivity());
        if (mockWalker.isStarted()) {
            mStartButton.setChecked(true);
        } else {
            mStartButton.setChecked(false);
        }
    }
}
