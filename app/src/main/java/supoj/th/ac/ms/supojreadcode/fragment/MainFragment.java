package supoj.th.ac.ms.supojreadcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import supoj.th.ac.ms.supojreadcode.R;
import supoj.th.ac.ms.supojreadcode.utility.GetAlUser;
import supoj.th.ac.ms.supojreadcode.utility.MyAlert;
import supoj.th.ac.ms.supojreadcode.utility.MyConstant;

/**
 * Created by SUPOJ on 20.03.2018.
 */

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Registre Controller
        registreController();

        // Login Controller
        loginController();

    }   ///main Method

    private void loginController() {

        Button button = getView().findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditTex = getView().findViewById(R.id.edituser);
                EditText passEditTex = getView().findViewById(R.id.btbpassword);

                String userString = userEditTex.getText().toString().trim();
                String passString = passEditTex.getText().toString().trim();

                if (userString.isEmpty() || passString.isEmpty()) {
//  Have Space
                    MyAlert myAlert = new MyAlert(getActivity());

                    myAlert.myDialog(getString(R.string.have_sppace),
                            getString(R.string.messes_have_sppace));


                } else {

                    // No space
                    try {
                        MyConstant myConstant = new MyConstant();
                        GetAlUser getAlUser = new GetAlUser(getActivity());
                        getAlUser.execute(myConstant.getUrlGetAllUserString());

                        String jsonString = getAlUser.get();
                        Log.d("22MarchV1", "JSON ==>" + jsonString);
                        String[] columnUserString = myConstant.getLoginstring();
                        String[] loginString = new String[columnUserString.length];
                        Boolean statusBool = true;

                        JSONArray jsonArray = new JSONArray(jsonString);
                        for (int i=0;i<jsonArray.length(); i+=1) {
                            JSONObject jsomObject = jsonArray.getJSONObject(i);

                            if (userString.equals(jsomObject.getString(columnUserString[2]))) {

                                statusBool = false;
                                for (int il=0; il<columnUserString.length; il+=1) {
                                    loginString[il] = jsomObject.getString(columnUserString[il]);
                                    Log.d("22MarchV1", "loginString[" + il + "] ==>" + loginString[il]);
                                }
                            }   //IF

                        }  //For
                        if (statusBool) {
 //                       User False
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("User False",
                                    "No This User in Mysql");

                        }  else if (passString.equals(loginString[3])){
 //                        Password True
                            Toast.makeText(getActivity(), "Welcom" + loginString[1], Toast.LENGTH_LONG).show();
                        }else {
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("Pass False","Please Try Again Password Flase");
                        }






                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }  // IF


            }   // OnClick
        });

    }

    private void registreController() {
        TextView textView = getView().findViewById(R.id.txtRegister); ///การประกาศตัวแปร
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Replac Freagment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();


            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);

        return view;
    }
}//Main Class
