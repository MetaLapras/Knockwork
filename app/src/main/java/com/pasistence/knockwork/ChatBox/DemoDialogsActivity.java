package com.pasistence.knockwork.ChatBox;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.pasistence.knockwork.ChatBox.ChatModel.Dialog;
import com.pasistence.knockwork.Common.AppUtils;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

public class DemoDialogsActivity extends AppCompatActivity
        implements DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog>{

    protected ImageLoader imageLoader;
    protected DialogsListAdapter<Dialog> dialogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, @Nullable String url, @Nullable Object payload) {
                Picasso.with(DemoDialogsActivity.this).load(url).into(imageView);

            }
        };
    }

    @Override
    public void onDialogClick(Dialog dialog) {

    }

    @Override
    public void onDialogLongClick(Dialog dialog) {
        AppUtils.showToast(this,
                dialog.getDialogName(),
                false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){

        }
        return super.onKeyDown(keyCode, event);
    }

}
