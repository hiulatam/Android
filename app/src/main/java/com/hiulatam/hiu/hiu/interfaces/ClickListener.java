package com.hiulatam.hiu.hiu.interfaces;

import android.view.View;

/**
 * Created By:  Shiny Solutions
 * Created On:  09/13/2017
 * Description: Android Image Gallery
 */

public interface ClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
