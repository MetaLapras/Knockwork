package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pasistence.knockwork.ChatBox.ChatModel.FirebaseUidModel;
import com.pasistence.knockwork.ChatBox.CustomLayoutMessagesActivity;
import com.pasistence.knockwork.Client.Activities.ClientJobRequest;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.Model.ApiResponse.ApiClientProposalsResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.MilestonesModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.ViewHolder.ViewHolderLancerProposal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pasistence.knockwork.Common.PreferenceUtils.getDisplayName;
import static com.pasistence.knockwork.Common.PreferenceUtils.getPhotoUrl;
import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class ClientProposalAdapter extends RecyclerView.Adapter<ViewHolderLancerProposal> {

    private static final String TAG = "lanceradapter";
    public Context mContext;
    ArrayList<ApiClientProposalsResponse> lancerArraylist;
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    FragmentManager fragmentManager;
    MyApi mService;

    public ClientProposalAdapter(Context mContext, ArrayList<ApiClientProposalsResponse> mList) {
        this.mContext = mContext;
        this.lancerArraylist = mList;
        mService = Common.getApi();
    }

    @NonNull
    @Override
    public ViewHolderLancerProposal onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_template_clientproposal, parent, false);
        return new ViewHolderLancerProposal(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderLancerProposal holder, final int position) {

        final ApiClientProposalsResponse lancers = lancerArraylist.get(position);

        if (lancers.getLProfileImage().equals(null) || lancers.getLProfileImage().equals("")) {
            Picasso.with(mContext).load("http://fboverlays.com/pages/assets/frontend/img/previewImage.png")
                    .into(holder.CircularImageViewProfile);
        } else {
            Picasso.with(mContext).load(lancers.getLProfileImage())
                    .into(holder.CircularImageViewProfile);
        }


        holder.txtLancerName.setText(lancers.getLName());
        holder.txtLancerState.setText(lancers.getLGender());
        holder.txtLancerDescription.setText(lancers.getLSelfIntro());
        //   holder.txtLancerLike.setText(lancers.getLike());
        holder.txtLancerEarned.setText(lancers.getLMinhourrate());
        Log.e(TAG,lancers.getJobid());

        mService.getPostJobbyid(lancers.getJobid()).enqueue(new Callback<ApiPostJobResponse>() {
            @Override
            public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {
                ApiPostJobResponse result = response.body();
                Log.e(TAG,result.toString());
                if(!result.getError()){
                    for (ApiPostJobResponse.Result res: result.getResult()
                         ) {
                        holder.txtTitle.setText(res.getTitle());
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                t.printStackTrace();
                Common.commonDialog(mContext,"Server Not found!");
            }
        });

        mService.getCoverletter(lancers.getId()).enqueue(new Callback<List<MilestonesModel.Detail>>() {
            @Override
            public void onResponse(Call<List<MilestonesModel.Detail>> call, Response<List<MilestonesModel.Detail>> response) {
                List<MilestonesModel.Detail> result = response.body();
                Log.e(TAG,result.toString());
                holder.txtCoverletter.setText(result.get(0).getMilestone());
            }

            @Override
            public void onFailure(Call<List<MilestonesModel.Detail>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        //init Firebase

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("chatbox");
        final String chatId = getUid(mContext) + "_" + lancers.getUid();


        holder.btnLancerMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (!dataSnapshot.child(chatId).exists()) {
                            FirebaseUidModel uidModel = new FirebaseUidModel(
                                    getUid(mContext),
                                    lancers.getUid(),
                                    lancers.getLName(),
                                    lancers.getLProfileImage(),
                                    getDisplayName(mContext),
                                    getPhotoUrl(mContext),
                                    "");
                            mReference.child(chatId).setValue(uidModel);

                        }

                        mContext.startActivity(new Intent(mContext, CustomLayoutMessagesActivity.class)
                                .putExtra("lancerUid", lancers.getUid())
                                .putExtra("clientUid", getUid(mContext))
                                .putExtra("image", lancers.getLProfileImage())
                                .putExtra("name", lancers.getLName())
                        );

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, databaseError.getDetails());
                    }
                });
            }
        });

        holder.btnLancerHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ClientJobRequest.class);
                intent.putExtra(Common.PROPOSAL, Common.PROPOSAL);
                intent.putExtra("title", holder.txtTitle.getText().toString());
                intent.putExtra("lancer", lancerArraylist.get(position));
                //intent.putExtra("jobid",lancerArraylist.get(position).)
                mContext.startActivity(intent);
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(mContext, ClientJobRequest.class);
                intent.putExtra(Common.PROPOSAL, Common.PROPOSAL);
                intent.putExtra("title", holder.txtTitle.getText().toString());
                intent.putExtra("lancer", lancerArraylist.get(position));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lancerArraylist.size();
    }

}