package com.drivepetdriver.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.drivepetdriver.Fragments.Inicio_Fragment;
import com.drivepetdriver.R;

public class View_Menu extends Activity implements View.OnClickListener {

    Switch switch_conexao;
    TextView textview_conexao;

    LinearLayout item_tab_menu_inicio;
    LinearLayout item_tab_menu_ganhos;
    LinearLayout item_tab_menu_aval;
    LinearLayout item_tab_menu_conta;

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.view_menu);

        textview_conexao = (TextView) findViewById(R.id.textview_conexao);
        switch_conexao = (Switch) findViewById(R.id.switch_conexao);
        switch_conexao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    textview_conexao.setText("Online");
                }else{
                    textview_conexao.setText("Offline");
                }
            }
        });

        item_tab_menu_inicio = (LinearLayout) findViewById(R.id.item_tab_menu_inicio);
        item_tab_menu_inicio.setOnClickListener(this);
        item_tab_menu_ganhos = (LinearLayout) findViewById(R.id.item_tab_menu_ganhos);
        item_tab_menu_ganhos.setOnClickListener(this);
        item_tab_menu_aval = (LinearLayout) findViewById(R.id.item_tab_menu_aval);
        item_tab_menu_aval.setOnClickListener(this);
        item_tab_menu_conta = (LinearLayout) findViewById(R.id.item_tab_menu_conta);
        item_tab_menu_conta.setOnClickListener(this);

        item_tab_menu_inicio.setAlpha((float) 1.0);
        item_tab_menu_ganhos.setAlpha((float) 0.3);
        item_tab_menu_aval.setAlpha((float) 0.3);
        item_tab_menu_conta.setAlpha((float) 0.3);

        getFragmentManager().beginTransaction().replace(R.id.container_central,
                new Inicio_Fragment()).commit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_tab_menu_inicio:
                item_tab_menu_inicio.setAlpha((float) 1.0);
                item_tab_menu_ganhos.setAlpha((float) 0.3);
                item_tab_menu_aval.setAlpha((float) 0.3);
                item_tab_menu_conta.setAlpha((float) 0.3);
                getFragmentManager().beginTransaction().replace(R.id.container_central,
                        new Inicio_Fragment()).commit();
                break;

            case R.id.item_tab_menu_ganhos:
                item_tab_menu_inicio.setAlpha((float) 0.3);
                item_tab_menu_ganhos.setAlpha((float) 1.0);
                item_tab_menu_aval.setAlpha((float) 0.3);
                item_tab_menu_conta.setAlpha((float) 0.3);
                break;

            case R.id.item_tab_menu_aval:
                item_tab_menu_inicio.setAlpha((float) 0.3);
                item_tab_menu_ganhos.setAlpha((float) 0.3);
                item_tab_menu_aval.setAlpha((float) 1.0);
                item_tab_menu_conta.setAlpha((float) 0.3);
                break;

            case R.id.item_tab_menu_conta:
                item_tab_menu_inicio.setAlpha((float) 0.3);
                item_tab_menu_ganhos.setAlpha((float) 0.3);
                item_tab_menu_aval.setAlpha((float) 0.3);
                item_tab_menu_conta.setAlpha((float) 1.0);
                break;
        }
    }
}
