package nyc.c4q.androidtest_unit4final;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by justiceo on 1/9/18.
 */

public class InfoFragment extends Fragment {
    private Button moreInfo;
    private TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.info_fragment, container, false);
        moreInfo = v.findViewById(R.id.more_info);
        textView = v.findViewById(R.id.more_textView);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getVisibility() == View.VISIBLE) {
                    textView.setVisibility(View.GONE);
                    moreInfo.setVisibility(View.GONE);
                } else {
                    textView.setVisibility(View.VISIBLE);
                    moreInfo.setVisibility(View.GONE);
                }
            }
        });
        return v;
    }
}
