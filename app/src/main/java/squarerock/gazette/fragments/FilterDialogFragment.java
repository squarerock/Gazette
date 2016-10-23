package squarerock.gazette.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import squarerock.gazette.R;
import squarerock.gazette.utils.Constants;
import squarerock.gazette.utils.Preferences;

/**
 * Created by pranavkonduru on 10/21/16.
 */

public class FilterDialogFragment extends DialogFragment {

    @BindView(R.id.tv_begin_date) EditText tvStartDate;
    @BindView(R.id.tv_end_date) EditText tvEndDate;
    @BindView(R.id.spinner_sort_order) Spinner spinnerSortOrder;
    @BindView(R.id.chkbx_arts) CheckBox chkbxArts;
    @BindView(R.id.chkbx_fashion) CheckBox chkbxFashion;
    @BindView(R.id.chkbx_sports) CheckBox chkbxSports;
    @BindView(R.id.btn_cancel) Button btnCancel;
    @BindView(R.id.btn_reset) Button btnReset;
    @BindView(R.id.btn_save) Button btnSave;

    private FilterDialogListener listener;
    private DatePickerDialog beginDatePickerDialog, endDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    public interface FilterDialogListener{
        void onPreferencesSaved();
    }

    /**
     * Default constructor
     */
    public FilterDialogFragment() {
    }

    /**
     * Returns new instance of FilterDialogFragment to be shown
     * @param title Title, if any needs to be shown
     * @return
     */
    public static FilterDialogFragment newInstance(String title){
        FilterDialogFragment frag = new FilterDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    /**
     * Capture the listener here
     * @param context Context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FilterDialogListener){
            listener = (FilterDialogListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_dialog, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    /**
     * Initialize views
     */
    private void initViews() {
        dateFormatter = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US);

        Calendar cal = Calendar.getInstance();
        beginDatePickerDialog = new DatePickerDialog(this.getActivity(), (view12, year, month, dayOfMonth) -> {
            Calendar beginDate = Calendar.getInstance();
            beginDate.set(year, month, dayOfMonth);
            tvStartDate.setText(dateFormatter.format(beginDate.getTime()));
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        endDatePickerDialog = new DatePickerDialog(this.getActivity(), (view1, year, month, dayOfMonth) -> {
            Calendar beginDate = Calendar.getInstance();
            beginDate.set(year, month, dayOfMonth);
            tvEndDate.setText(dateFormatter.format(beginDate.getTime()));
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        populateFields();
    }

    @OnClick({ R.id.tv_begin_date, R.id.tv_end_date, R.id.btn_cancel, R.id.btn_save, R.id.btn_reset })
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_begin_date:
                beginDatePickerDialog.show();
                break;
            case R.id.tv_end_date:
                endDatePickerDialog.show();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_save:
                saveFields();
                if(listener != null){
                    listener.onPreferencesSaved();
                }
                dismiss();
                break;
            case R.id.btn_reset:
                resetFields();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    /**
     * Upon clicking Save, save the fields into SharedPreferences
     */
    private void saveFields(){
        Preferences.putString(Preferences.PREF_KEY_START_DATE, tvStartDate.getText().toString());
        Preferences.putString(Preferences.PREF_KEY_END_DATE, tvEndDate.getText().toString());
        Preferences.putString(Preferences.PREF_KEY_SORT_ORDER, (String) spinnerSortOrder.getSelectedItem());
        Preferences.putBoolean(Preferences.PREF_KEY_NEWSDESK_ARTS, chkbxArts.isChecked());
        Preferences.putBoolean(Preferences.PREF_KEY_NEWSDESK_FASHION, chkbxFashion.isChecked());
        Preferences.putBoolean(Preferences.PREF_KEY_NEWSDESK_SPORTS, chkbxSports.isChecked());
    }

    /**
     * Upon clicking Reset, clear all the values in SharedPreferences
     */
    private void resetFields(){
        Preferences.clearAll(getContext());
        populateFields();
    }

    /**
     * Populate fields from Preferences onto the views
     */
    private void populateFields(){
        tvStartDate.setText(Preferences.getString(Preferences.PREF_KEY_START_DATE));
        tvEndDate.setText(Preferences.getString(Preferences.PREF_KEY_END_DATE));
        chkbxArts.setChecked(Preferences.getBoolean(Preferences.PREF_KEY_NEWSDESK_ARTS));
        chkbxSports.setChecked(Preferences.getBoolean(Preferences.PREF_KEY_NEWSDESK_SPORTS));
        chkbxFashion.setChecked(Preferences.getBoolean(Preferences.PREF_KEY_NEWSDESK_FASHION));
        spinnerSortOrder.setSelection("oldest".equals(Preferences.getString(Preferences.PREF_KEY_SORT_ORDER)) ? 0 : 1);
    }
}
