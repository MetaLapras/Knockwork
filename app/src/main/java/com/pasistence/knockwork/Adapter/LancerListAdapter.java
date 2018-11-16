package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pasistence.knockwork.ChatBox.ChatModel.FirebaseUidModel;
import com.pasistence.knockwork.ChatBox.CustomLayoutMessagesActivity;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderFreeLancerList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.pasistence.knockwork.Common.PreferenceUtils.getDisplayName;
import static com.pasistence.knockwork.Common.PreferenceUtils.getPhotoUrl;
import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class LancerListAdapter extends RecyclerView.Adapter<ViewHolderFreeLancerList> {

    private static final String TAG = "lanceradapter";
    public Context mContext;
    ArrayList<ApiResponseRegisterLancer.Lancer> lancerArraylist ;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;

    public LancerListAdapter(Context mContext, ArrayList<ApiResponseRegisterLancer.Lancer> workerList) {
        this.mContext = mContext;
        this.lancerArraylist = workerList;
    }

    @NonNull
    @Override
    public ViewHolderFreeLancerList onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custome_member_templets,parent,false);
            return new ViewHolderFreeLancerList(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFreeLancerList holder, int position) {


            final ApiResponseRegisterLancer.Lancer lancers = lancerArraylist.get(position);

            if(lancers.getLancerImage().equals(null)||lancers.getLancerImage().equals("")){
                Picasso.with(mContext).load("http://fboverlays.com/pages/assets/frontend/img/previewImage.png")
                        .into(holder.CircularImageViewProfile);
            }else {
                Picasso.with(mContext).load(lancers.getLancerImage())
                        .into(holder.CircularImageViewProfile);
            }


            holder.txtLancerName.setText(lancers.getLancerName());
            holder.txtLancerState.setText(lancers.getLancerGender());
            holder.txtLancerDescription.setText(lancers.getLancerSelfIntro());
                //   holder.txtLancerLike.setText(lancers.getLike());
            holder.txtLancerEarned.setText(lancers.getLancerMinHourRate());


            //init Firebase

            mDatabase = FirebaseDatabase.getInstance();
            mReference = mDatabase.getReference("chatbox");
            final String chatId = getUid(mContext) +"_" + lancers.getUid();





            holder.btnLancerMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(!dataSnapshot.child(chatId).exists()){
                                FirebaseUidModel uidModel = new FirebaseUidModel(
                                        getUid(mContext),
                                        lancers.getUid(),
                                        lancers.getLancerName(),
                                        lancers.getLancerImage(),
                                        getDisplayName(mContext),
                                        getPhotoUrl(mContext),
                                        "");
                                mReference.child(chatId).setValue(uidModel);

                            }

                            mContext.startActivity(new Intent(mContext,CustomLayoutMessagesActivity.class)
                                    .putExtra("lancerUid",lancers.getUid())
                                    .putExtra("clientUid",getUid(mContext))
                                    .putExtra("image",lancers.getLancerImage())
                            );

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e(TAG, databaseError.getDetails() );
                        }
                    });



                }
            });

    }

    @Override
    public int getItemCount() {
        return lancerArraylist.size();
    }

}
