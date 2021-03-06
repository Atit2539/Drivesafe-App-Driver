package com.senior.g40.drivesafe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.senior.g40.drivesafe.engines.UserEngines;
import com.senior.g40.drivesafe.utils.SettingVerify;
import com.senior.g40.drivesafe.weeworh.WWProp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtxt_loginUsername)
    EditText edtxtLoginUsername;
    @BindView(R.id.edtxt_loginPassword)
    EditText edtxtLoginPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    @BindView(R.id.txt_headMessage)
    TextView txtHeadMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        if (!SettingVerify.isNetworkConnected(this)) {return;}
            switch (view.getId()) {
                case R.id.btn_login:
                    if (UserEngines.getInstance(this)
                            .login(edtxtLoginUsername.getText().toString(), edtxtLoginPassword.getText().toString())) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        txtHeadMessage.setVisibility(View.VISIBLE);
                        txtHeadMessage.setText(getResources().getString(R.string.login_nouser));
                    }
                    break;
                case R.id.btn_register:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WWProp.WEEWORH_HOST)));
                    break;
            }

    }
}
