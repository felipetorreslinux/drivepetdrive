package com.drivepetdriver.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.drivepetdriver.Fragments.Conta_Fragment;
import com.drivepetdriver.Fragments.Inicio_Fragment;
import com.drivepetdriver.R;
import com.drivepetdriver.Utils.Sounds;
import com.drivepetdriver.Utils.Vibrate;

public class View_Menu extends Activity implements View.OnClickListener {

    public static boolean ONLINE_USER = false;
    static int TAB_INDEX = 0;

    Switch switch_conexao;
    TextView textview_conexao;

    RelativeLayout action_bar_menu;

    LinearLayout item_tab_menu_inicio;
    LinearLayout item_tab_menu_ganhos;
    LinearLayout item_tab_menu_aval;
    LinearLayout item_tab_menu_conta;

    LinearLayout bottom_bar_menu;
    Window window;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.view_menu);

        window = getWindow();

        action_bar_menu = (RelativeLayout) findViewById(R.id.action_bar_menu);
        bottom_bar_menu = (LinearLayout) findViewById(R.id.bottom_bar_menu);
        textview_conexao = (TextView) findViewById(R.id.textview_conexao);

        textview_conexao.setText("Offline");
        action_bar_menu.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        bottom_bar_menu.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));

        switch_conexao = (Switch) findViewById(R.id.switch_conexao);
        switch_conexao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    ONLINE_USER = true;
                    textview_conexao.setText("Online");
                    item_tab_menu_inicio.setAlpha((float) 1.0);
                    item_tab_menu_ganhos.setAlpha((float) 0.5);
                    item_tab_menu_aval.setAlpha((float) 0.5);
                    item_tab_menu_conta.setAlpha((float) 0.5);
                    getFragmentManager().beginTransaction().replace(R.id.container_central,
                            new Inicio_Fragment()).commit();
                    TAB_INDEX = 0;
                }else{
                    ONLINE_USER = false;
                    textview_conexao.setText("Offline");
                    if(TAB_INDEX == 0){
                        getFragmentManager().beginTransaction().remove(getFragmentManager()
                                .findFragmentById(R.id.container_central)).commit();
                    }
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
        item_tab_menu_ganhos.setAlpha((float) 0.5);
        item_tab_menu_aval.setAlpha((float) 0.5);
        item_tab_menu_conta.setAlpha((float) 0.5);

    };

    public void novaSolicitacao(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_new_solicitacao, null);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.show();
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_tab_menu_inicio:
                item_tab_menu_inicio.setAlpha((float) 1.0);
                item_tab_menu_ganhos.setAlpha((float) 0.5);
                item_tab_menu_aval.setAlpha((float) 0.5);
                item_tab_menu_conta.setAlpha((float) 0.5);
                if(TAB_INDEX != 0){
                    if(ONLINE_USER == true){
                        getFragmentManager().beginTransaction().replace(R.id.container_central,
                                new Inicio_Fragment()).commit();
                        TAB_INDEX = 0;
                    }else{
                        getFragmentManager().beginTransaction().remove(getFragmentManager()
                                .findFragmentById(R.id.container_central)).commit();
                        TAB_INDEX = 0;
                    }
                }
                break;

            case R.id.item_tab_menu_ganhos:
                item_tab_menu_inicio.setAlpha((float) 0.5);
                item_tab_menu_ganhos.setAlpha((float) 1.0);
                item_tab_menu_aval.setAlpha((float) 0.5);
                item_tab_menu_conta.setAlpha((float) 0.5);
                TAB_INDEX = 1;
                break;

            case R.id.item_tab_menu_aval:
                item_tab_menu_inicio.setAlpha((float) 0.5);
                item_tab_menu_ganhos.setAlpha((float) 0.5);
                item_tab_menu_aval.setAlpha((float) 1.0);
                item_tab_menu_conta.setAlpha((float) 0.5);
                TAB_INDEX = 2;
                break;

            case R.id.item_tab_menu_conta:
                item_tab_menu_inicio.setAlpha((float) 0.5);
                item_tab_menu_ganhos.setAlpha((float) 0.5);
                item_tab_menu_aval.setAlpha((float) 0.5);
                item_tab_menu_conta.setAlpha((float) 1.0);
                if(TAB_INDEX != 3){
                    getFragmentManager().beginTransaction().replace(R.id.container_central,
                            new Conta_Fragment()).commit();
                    TAB_INDEX = 3;
                }
                break;
        }
    }

    @Override
    public void onBackPressed(){
        switch (TAB_INDEX){
            case 0:
                finish();
                break;
            default:
                item_tab_menu_inicio.setAlpha((float) 1.0);
                item_tab_menu_ganhos.setAlpha((float) 0.5);
                item_tab_menu_aval.setAlpha((float) 0.5);
                item_tab_menu_conta.setAlpha((float) 0.5);
                if(TAB_INDEX != 0){
                    getFragmentManager().beginTransaction().replace(R.id.container_central,
                            new Inicio_Fragment()).commit();
                    TAB_INDEX = 0;
                }
        }
    }
}
