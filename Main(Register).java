package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.BlockingDeque;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText edtName,edtPass,edtRepass,edtEmail;
    private Button btnImg,btnReg;
    private CheckBox ckbAgr;
    private TextView txtName ,txtEmail,txtPass,txtRepass,txtAgr,txtCoun,txtGen;
    private Spinner spnCoun;
    private RadioGroup rgGen;
    private ConstraintLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        txtAgr.setText("By clicking I Agree to our policy you agree to the terms and conditions set for you");
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"pick image",Toast.LENGTH_SHORT).show();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: started");
        if(validateData()){
        if(ckbAgr.isChecked()){
            showSnackbar();
        }
        else Toast.makeText(this, "Agree to policy to continue", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSnackbar() {
        Log.d(TAG, "showSnackbar: started");
        txtName.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);
        txtPass.setVisibility(View.GONE);
        txtRepass.setVisibility(View.GONE);

        String name=edtName.getText().toString();
        String email=edtEmail.getText().toString();
        String country=spnCoun.getSelectedItem().toString();
        String gender="";

        switch (rgGen.getCheckedRadioButtonId()){
            case R.id.rbmale:
                gender="Male";
                break;

            case R.id.rbfemale:
                gender="Female";
                break;

            case R.id.rbother:
                gender="Other";
                break;

            default:
                gender="Unknown";
                break;
        }

        String snackText="Name: "+name+"\nE-mail: "+email+"\nCountry: "+country+"\nGender: "+gender;
        Snackbar.make(parent,snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtName.setText("");
                        edtEmail.setText("");
                        edtPass.setText("");
                        edtRepass.setText("");
                    }
                }).show();
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: started");
        if(edtName.getText().toString().isEmpty()) {txtName.setVisibility(View.VISIBLE);
            txtName.setText("Enter the name");
        return false;
        }
        if(edtEmail.getText().toString().isEmpty()) {txtEmail.setVisibility(View.VISIBLE);
            txtEmail.setText("Enter the email");
        return false;
        }
        if(edtPass.getText().toString().isEmpty()) {txtPass.setVisibility(View.VISIBLE);
            txtPass.setText("Enter the password");
        return false;
        }
        if(edtRepass.getText().toString().isEmpty()) {txtRepass.setVisibility(View.VISIBLE);
            txtRepass.setText("Renter the password");
        return false;
        }

        if(!edtPass.getText().toString().equals(edtRepass.getText().toString())) {
            Toast.makeText(MainActivity.this, "Re-entered password should be same", Toast.LENGTH_SHORT).show();
            txtPass.setVisibility(View.VISIBLE);
            txtRepass.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    private void initViews() {
        Log.d(TAG,"initViews Started");;
        edtName=findViewById(R.id.name);
        edtEmail=findViewById(R.id.email);
        edtPass=findViewById(R.id.pass);
        edtRepass=findViewById(R.id.repass);

        btnImg=findViewById(R.id.btnimg);
        btnReg=findViewById(R.id.register);

        txtName=findViewById(R.id.warnname);
        txtEmail=findViewById(R.id.warnemail);
        txtPass=findViewById(R.id.warnpass);
        txtRepass=findViewById(R.id.warnrepass);
        txtAgr=findViewById(R.id.policy);
        txtCoun=findViewById(R.id.txtcountry);
        txtGen=findViewById(R.id.txtgender);

        ckbAgr=findViewById(R.id.agree);
        spnCoun=findViewById(R.id.country);
        rgGen=findViewById(R.id.rbgen);
        parent=findViewById(R.id.parent);

    }
}
