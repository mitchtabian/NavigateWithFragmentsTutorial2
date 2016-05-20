package applications.navigatewithfragmentstutorial2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mitch on 2016-05-19.
 */
public class Front_Page_Fragment extends Fragment {
    View myView;
    EditText etAdd;
    DatabaseHelper myDB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.front_page,container, false);

        etAdd = (EditText) myView.findViewById(R.id.etAdd);
        Button btnAddToList = (Button) myView.findViewById(R.id.btnAddToList);
        myDB = new DatabaseHelper(this.getActivity());

        btnAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the input from the user
                String text = etAdd.getText().toString();

                //check to see if there was text in the box before they hit the button
                if(text.length() != 0) {
                    AddData(text);
                    etAdd.setText("");
                }else{
                    Toast.makeText(getActivity(), "You need to enter something in the text field first!.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return myView;
    }

    public void AddData(String mItem) {

        boolean insertData = myDB.addData(mItem);

        if (insertData == true) {
            Toast.makeText(this.getActivity(), "Data Successfully Inserted!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this.getActivity(), "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }
}
