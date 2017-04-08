package com.sample.srombs.weather;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sample.srombs.weather.api.ApiService;
import com.sample.srombs.weather.model.CurrentWeather;
import com.sample.srombs.weather.model.Weather;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewWeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewWeatherFragment extends Fragment implements ViewWeather {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Inject ViewWeatherPresenter presenter;
    @BindView(R.id.submit_zipcode_btn)
    Button submitBtn;

    @BindView(R.id.zip_input)
    EditText zipInput;

    @BindView(R.id.current_temp)
    TextView currentTemp;

    public ViewWeatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewWeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        ViewWeatherFragment fragment = new ViewWeatherFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        presenter.onAttach(this);

        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view_weather, container, false);

        ButterKnife.bind(this, rootView);

        submitBtn.setOnClickListener(v -> {
         presenter.loadCurrentWeatherByZip(zipInput.getText().toString());
        });


        return rootView;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void showCurrentLocationWeather() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showZipCodeWeather(CurrentWeather currentWeather) {
        currentTemp.setText(String.valueOf(currentWeather.main.temperature));
    }
}
