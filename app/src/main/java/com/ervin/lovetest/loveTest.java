package com.ervin.lovetest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by ervin on 09/08/16.
 */
public class loveTest extends Fragment{

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentManager fm = getFragmentManager();
        View vw = inflater.inflate(R.layout.love_test, container, false);
        final EditText name = (EditText) vw.findViewById(R.id.name1);
        final EditText cname = (EditText) vw.findViewById(R.id.name2);
        final ImageView button = (ImageView) vw.findViewById(R.id.test_up);
        Picasso.with(getActivity()).load(R.drawable.testup).into(button);





        button.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Editable n = name.getText();
                Editable cn = cname.getText();
                String concat = String.valueOf(n).concat(String.valueOf(cn)).toUpperCase();
                if ((n.toString().trim().length() == 0) || (cn.toString().trim().length() == 0)) {
                    Toast.makeText(getActivity(), "Please fill both the fields ", Toast.LENGTH_LONG).show();
                } else {
                    int sum = 0;
                    for (int i = 0; i < concat.length(); i++) {
                        char character = concat.charAt(i);
                        int ascii = (int) character;
                        sum += ascii;
                    }
                    int res = sum % 100;
                    Intent intent=new Intent(getActivity(), result.class);
                    intent.putExtra("hasil",res);
                    startActivity(intent);
                }
            }
        });

        return vw;
            }
}



