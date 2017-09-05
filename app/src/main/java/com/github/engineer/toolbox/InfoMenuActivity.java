package com.github.engineer.toolbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damian on 2017-08-02.
 */

public class InfoMenuActivity extends AppCompatActivity implements View.OnClickListener {
    //setting fields
    private static final String HTTPS_GITHUB = "https://github.com/ludi889/Enginner-Toolbox";
    private static final String HTTPS_LINKEDIN = "https://www.linkedin.com/in/damian-zi%C3%B3%C5%82kowski-41ab38141/";
    //Binding views
    @BindView(R.id.author_image_icon)
    ImageView authorImageIconView;
    @BindView(R.id.linkedin_account_link)
    TextView linkedinUrl;
    @BindView(R.id.github_account_link)
    TextView githubUrl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_menu);
        ButterKnife.bind(this);
        //setting listeners
        linkedinUrl.setOnClickListener(this);
        githubUrl.setOnClickListener(this);
        //setting image and catching runtime exception
        try {
            authorImageIconView.setImageResource(R.drawable.authorimageicon);
        } catch (RuntimeException e) {
            Log.v("Info menu", "Problem with setting author icon drawable" + e);
            authorImageIconView.setImageResource(android.R.color.transparent);
        }
    }

    @Override
    public void onClick(View v) {
        Uri uri = null;
        switch (v.getId()) {
            case R.id.linkedin_account_link:
                uri = Uri.parse(HTTPS_LINKEDIN);
                break;
            case R.id.github_account_link:
                uri = Uri.parse(HTTPS_GITHUB);
                break;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
