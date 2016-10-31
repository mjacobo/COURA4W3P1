package com.mj.courseraprw3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mj.courseraprw3.fragment.PerfilFragment;

/**
 * Created by leyenda1 on 15/10/2016.
 */

public class Account extends AppCompatActivity {
    public TextView myAccountTV;
    public Button   myAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBarAccount);
        setSupportActionBar(myActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAccountTV = (TextView) findViewById(R.id.myUserAccount);
        myAccountButton = (Button) findViewById(R.id.acbSendAccount);

        myAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myProfileViewIntent = new Intent(getBaseContext(), MainActivity.class);
                myProfileViewIntent.putExtra("accountName", myAccountTV.getText().toString());
                startActivity(myProfileViewIntent);
            }
        });

    }

}
