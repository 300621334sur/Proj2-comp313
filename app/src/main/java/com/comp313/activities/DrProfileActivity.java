package com.comp313.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.comp313.helpers.VariablesGlobal;
import com.comp313.models.DrProfile;

import com.comp313.R;
import com.comp313.databinding.ActivityDrProfileBinding;

public class DrProfileActivity extends BaseActivity
{

    ActivityDrProfileBinding binding;

    //region >>> Vars
    int DrSelectedPos;
    String DrSelectedName, userIdStr;
    DrProfile dr;
    Intent i;
    Button btnDrProfile_SelectDrV, btnDrProfile_LoginV;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_profile);
        getSupportActionBar().setTitle("Review the Doctor");


        binding = DataBindingUtil.setContentView(this, R.layout.activity_dr_profile);
        //get ref AFTER binding's setContentView
        getReferences();
        hideBtnBasedOnLoginStatus();
        //
        TextView txtDrNameV = binding.txtDrName;
        txtDrNameV.setText("a a a a a a ");

        dr = new DrProfile();
        DrSelectedPos = getIntent().getIntExtra("DrSelectedIndex", 0);
        DrSelectedName = getIntent().getStringExtra("DrSelectedName");

        if(DrSelectedPos != 0)
        {
            for (DrProfile Dr : VariablesGlobal.DrProfiles)
            {
                if(Dr.name.equals(DrSelectedName))
                {
                    dr = Dr;
                }
            }
        }
        else
        {
            dr.name = "No Doctor Was Selected";
            dr.specialty = "No Doctor Was Selected";
            dr.email = "No Doctor Was Selected";
            dr.phone = "No Doctor Was Selected";
            dr.id_doc = 0;
            dr.Id_User = 0;
        }

        binding.setDr(dr);
    }

    private void getReferences()
    {
        btnDrProfile_SelectDrV = (Button)findViewById(R.id.btnDrProfile_SelectDr);
        btnDrProfile_LoginV = (Button)findViewById(R.id.btnDrProfile_Login);

        userIdStr = getSharedPreferences("prefs",0).getString("Id_User", "");
    }

    private void hideBtnBasedOnLoginStatus()
    {
        //hide both btn first
        btnDrProfile_SelectDrV.setVisibility(View.INVISIBLE);
        btnDrProfile_LoginV.setVisibility(View.INVISIBLE);
        //Then decide which btn to make visible
        if(userIdStr.equals(""))//Not logged in
        {
            btnDrProfile_LoginV.setVisibility(View.VISIBLE);
        }
        else //Logged in
        {
            btnDrProfile_SelectDrV.setVisibility(View.VISIBLE);
        }
    }

    public void clk_MakeAppoint_SelectTime(View view)
    {
        i = new Intent(this, SelectTime.class);
        startActivity(i);
    }

    public void clk_Login(View view)
    {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
