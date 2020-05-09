package com.hamzajbr.fannak_user.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.activities.AboutUsActivity;
import com.hamzajbr.fannak_user.activities.AccountActivity;
import com.hamzajbr.fannak_user.activities.ContactUsActivity;
import com.hamzajbr.fannak_user.activities.LoginActivity;
import com.hamzajbr.fannak_user.adapters.OptionsAdapter;
import com.hamzajbr.fannak_user.models.OptionItem;
import com.hamzajbr.fannak_user.utilities.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.more_options_rv)
    RecyclerView optionsRv;
    @BindView(R.id.logout_btn)
    MaterialButton logoutBtn;

    boolean signedIn;


    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        unbinder = ButterKnife.bind(this,view);
        signedIn = Utils.getValue(getContext(),"signed in",true);
        if (signedIn){
            logoutBtn.setVisibility(View.VISIBLE);
        }else {
            logoutBtn.setVisibility(View.GONE);
        }

        initOptionsRecycler();



        return view;
    }
    private void initOptionsRecycler(){
        OptionsAdapter adapter = new OptionsAdapter(getContext(), optionsArrayList(), new OptionsAdapter.IOption() {
            @Override
            public void onClick(OptionItem item) {
                switch (item.icon){
                    case R.drawable.ic_account:
                        if(signedIn)
                            Utils.goToActivity(getActivity(), AccountActivity.class,false);
                        else
                            Utils.goToActivity(getActivity(), LoginActivity.class,true);
                        break;
                    case R.drawable.ic_about_us:
                        Utils.goToActivity(getActivity(), AboutUsActivity.class,false);
                        break;
                    case R.drawable.ic_contact_us:
                        Utils.goToActivity(getActivity(), ContactUsActivity.class,false);
                        break;
                }


            }
        });
        optionsRv.setAdapter(adapter);
        optionsRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

    }
    private ArrayList<OptionItem> optionsArrayList(){
        ArrayList<OptionItem> optionItems = new ArrayList<>();
        OptionItem item;

        item = new OptionItem();
        item.label = getString(R.string.account);
        item.icon = R.drawable.ic_account;
        optionItems.add(item);


        item = new OptionItem();
        item.label = getString(R.string.about_us);
        item.icon = R.drawable.ic_about_us;
        optionItems.add(item);


        item = new OptionItem();
        item.label =getString(R.string.contact_us);
        item.icon = R.drawable.ic_contact_us;
        optionItems.add(item);

        return optionItems;
    }
    @OnClick(R.id.logout_btn)
    void Logout(){
        Utils.setValue(getContext(),"signed in",false);
        Utils.goToActivity(getActivity(),LoginActivity.class,true);
    }

}
