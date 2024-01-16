
package com.corp.ankit.deca;




import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;


public class SettingsActivity extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.app_preferences);
        Preference preference = (Preference) findPreference("clear");
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                QuoteBank mQuotebank = new QuoteBank(getActivity());
                mQuotebank.clear();
                Toast.makeText(getActivity(),"Wrong Answers Cleared",Toast.LENGTH_LONG).show();
                return true;
            }
        });
        Preference signout = (Preference) findPreference("signout");
        signout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                DatabaseUtils databaseUtils = new DatabaseUtils();
                databaseUtils.signOutUser();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                return false;
            }
        });

        Preference changePassword = (Preference) findPreference("change_password");
        changePassword.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                DatabaseUtils databaseUtils = new DatabaseUtils();
                databaseUtils.resetPassword(getActivity(), databaseUtils.getEmailAddress());

                return false;
            }
        });
    }
}
