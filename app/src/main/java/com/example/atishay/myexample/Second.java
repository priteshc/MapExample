package com.example.atishay.myexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import ru.bullyboo.encoder.Encoder;
import ru.bullyboo.encoder.methods.AES;

/**
 * Created by Atishay on 06-02-2018.
 */

public class Second extends AppCompatActivity {


    private View button1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        button1 = findViewById(R.id.b1);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String encrypt = Encoder.BuilderAES()
                        .message(Constant.MESSAGE)
                        .method(AES.Method.AES_CBC_PKCS7PADDING)
                        .key("8080808080808080")
                        .keySize(AES.Key.SIZE_128)
                        .iVector("8040804080408080")
                        .encrypt();


              //  Log.d("Msg",encrypt);

                System.out.println("Msg:" + encrypt);


                AlertDialog alertDialog = new AlertDialog.Builder(Second.this).create();
                alertDialog.setTitle("Return from RD");
                alertDialog.setMessage(encrypt);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });
    }
}
