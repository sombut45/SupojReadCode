package supoj.th.ac.ms.supojreadcode.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import supoj.th.ac.ms.supojreadcode.MainActivity;
import supoj.th.ac.ms.supojreadcode.R;
import supoj.th.ac.ms.supojreadcode.utility.MyAlert;
import supoj.th.ac.ms.supojreadcode.utility.MyConstant;
import supoj.th.ac.ms.supojreadcode.utility.PostUserToServer;

/**
 * Created by sombut on 21/3/2561.
 */

public class RegisterFragment extends Fragment{
// Explict
    private String nameString, userString, passString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Create Toolbar
        createToolbar();

        // Register controller
        registerController();


    }  //main methon

    private void registerController() {
        Button button = getView().findViewById(R.id.btnregister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//       Get Value from Edittext
                EditText nameEditText = getView().findViewById(R.id.editname);
                EditText useEditText = getView().findViewById(R.id.edituser);
                EditText passEditText = getView().findViewById(R.id.editpassword);

//       Chang Edittex to String
                nameString = nameEditText.getText().toString().trim();
                userString = useEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();

//       Chack Space
                if (nameString.isEmpty() || userString.isEmpty() || passString.isEmpty()) {
//       Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Have Space", "Please Fill all Blank");

                } else {
//       No  Space
                    try {
                        MyConstant myConstant = new MyConstant();
                        PostUserToServer postUserToServer = new PostUserToServer(getActivity());
                        postUserToServer.execute(nameString, userString, passString,
                                myConstant.getUrlPostString());
                        String ressult = postUserToServer.get();
                        Log.d("22MarchV1", "Result =+>" + ressult);

                        if (Boolean.parseBoolean(ressult)) {
                            getActivity().getSupportFragmentManager().popBackStack();
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("POST USER OK", "Finish");
                        } else {
                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("Cannot Post User",
                                    "Please Try Angin");

                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }




            }
        });
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);


        //setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");

        // Show Navigator Icon
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}   //main class
