package com.example.beginning.ViewModel;

import android.view.View;

import com.example.beginning.Model.User;

public interface ItemClickListener {
    void onItemClicked(View view, User user);

    void onItemLongClicked(View view, User user, int index);
}
