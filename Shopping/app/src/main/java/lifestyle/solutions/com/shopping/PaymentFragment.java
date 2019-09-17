package lifestyle.solutions.com.shopping;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {


    private ArrayList<String> listOfPattern;

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_payment, container, false);
        return inflater.inflate(R.layout.bank_card_template, container, false);
    }

    private void cardPatterns(){
       listOfPattern=new ArrayList<String>();

        String ptVisa = "^4[0-9]{6,}$";
        listOfPattern.add(ptVisa);
        String ptMasterCard = "^5[1-5][0-9]{5,}$";
        listOfPattern.add(ptMasterCard);
        String ptAmeExp = "^3[47][0-9]{5,}$";
        listOfPattern.add(ptAmeExp);
        String ptDinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";
        listOfPattern.add(ptDinClb);
        String ptDiscover = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
        listOfPattern.add(ptDiscover);
        String ptJcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";
        listOfPattern.add(ptJcb);
    }


    /**
     * validate card type
     * @param s
     */
    /**@Override
    public void afterTextChanged(Editable s) {
        Log.d("DEBUG", "afterTextChanged : "+s);
        String ccNum = s.toString();
        for(String p:listOfPattern){
            if(ccNum.matches(p)){
                Log.d("DEBUG", "afterTextChanged : discover");
                break;
            }
        }
    }*/
}
