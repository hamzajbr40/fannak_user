package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.adapters.AboutUsAdapter;
import com.hamzajbr.fannak_user.models.DeveloperItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.developers_rv)
    RecyclerView developersRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initDeveloperRecycler();

    }
    @OnClick(R.id.back_btn)
    void back(View view){
        onBackPressed();
        finish();
    }

    private void initDeveloperRecycler(){
        AboutUsAdapter adapter = new AboutUsAdapter(this,aboutUsList());
        developersRv.setAdapter(adapter);
        developersRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private ArrayList<DeveloperItem> aboutUsList(){
        ArrayList<DeveloperItem> list = new ArrayList<>();
        DeveloperItem item;

        item = new DeveloperItem();
        item.name = "Hamza Jbr";
        item.description = "Hello, I am Hamza and I’m a motivated Android Developer who is always has an ambitious to learn more about mobile development and the new tools. I love challenges in the line of work , and fannak is my new challenge, specially in UI/UX design. I love working with Colors and Art, so that what made me feel that I need to be a part of this project.";
        item.image = R.drawable.hamza_jbr;
        list.add(item);

        item = new DeveloperItem();
        item.name = "Ali Abu Diab";
        item.description = "Hello, I'm Ali and I'm to be more specific a React Front-end Web Developer who is always excited to learn the new things in the web development world!, What made us think of creating Fannak is that there's no actual platform for artists to show their work to the world and we wanted to do something to change that so we did!.";
        item.image = R.drawable.ali_abudiab;
        list.add(item);

        item = new DeveloperItem();
        item.name = "Marwan AlRamahi";
        item.description = "Hi,I'm Marwan and I work in front end development using AngularJS to be more specific. What made us think of creating Fannak is that there's no actual platform for artists to show their work to the world and we wanted to do something to change that so we did!";
        item.image = R.drawable.marwan_alramahi;
        list.add(item);

        item = new DeveloperItem();
        item.name = "Mohammad Abu Khalaf";
        item.description = "Hello, I'm Mohammad , I like problem solving and mathematical challenges !, So i make my way to coding and programming with Java programming language . I accept Fannak challenge because there’s limited sources to presenting your art and to gain a money with it So let’s do it ..!";
        item.image = R.drawable.moe_abukhalaf;
        list.add(item);

        return list;

    }

}
