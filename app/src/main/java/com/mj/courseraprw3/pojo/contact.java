package com.mj.courseraprw3.pojo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mj.courseraprw3.R;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class contact extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView message;
    Button   mysendbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        name         = (TextView) findViewById(R.id.acetName);
        email        = (TextView) findViewById(R.id.acetEmail);
        message      = (TextView) findViewById(R.id.acetMessage);
        mysendbutton = (Button) findViewById(R.id.acbSend);

        mysendbutton.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Properties props = new Properties();
                    props.put("mail.smtp.host","smtp.gmail.com");
                    Session session = Session.getInstance(props, null);

                    MimeMessage msg = new MimeMessage(session);
                    msg.setFrom("no-reply@petagram.com");
                    msg.addRecipients(Message.RecipientType.TO, email.getText().toString());
                    msg.setSubject("Mensaje de patagram.");
                    msg.setSentDate(new Date());
                    msg.setText("Mensaje de:" + name.getText().toString() + "\n Mensaje: "  + message.getText().toString());
                    Transport.send(msg,getResources().getString(R.string.email_account),getResources().getString(R.string.email_psw));
                }
                catch (Exception e) {
                    System.out.println("send failed, exception: ");
                    e.printStackTrace();
                } finally {
                    Toast.makeText(v.getContext(),"Error: Envio de mensaje falló", Toast.LENGTH_LONG);
                    finish();
                }
            }
        });

        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBarContact);
        setSupportActionBar(myActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
