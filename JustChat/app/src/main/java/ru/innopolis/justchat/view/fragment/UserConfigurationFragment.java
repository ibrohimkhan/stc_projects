package ru.innopolis.justchat.view.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ru.innopolis.justchat.R;
import ru.innopolis.justchat.manager.NetworkManager;
import ru.innopolis.justchat.manager.SharedDataManager;
import ru.innopolis.justchat.model.Language;
import ru.innopolis.justchat.model.State;
import ru.innopolis.justchat.model.User;
import ru.innopolis.justchat.model.UserType;
import ru.innopolis.justchat.view.IOptions;
import ru.innopolis.justchat.view.adapter.LanguageRecyclerViewAdapter;

/**
 * Created by ibrahim on 7/7/2017.
 */
public class UserConfigurationFragment extends Fragment implements IOptions {
    public static final String USER = "user";
    private SharedDataManager sharedDataManager;
    private LanguageRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_configuration_fragment, container, false);

        user = (User) getArguments().get(USER);

        sharedDataManager = new SharedDataManager(getActivity());
        final List<Language> languages = sharedDataManager.getAllLanguages();

        recyclerView = (RecyclerView) view.findViewById(R.id.languages);
        adapter = new LanguageRecyclerViewAdapter(languages, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        searchEditText = (EditText) view.findViewById(R.id.searchLanguage);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<Language> filtered = new ArrayList<Language>();

                for (Language language : languages) {
                    if (language.getLanguage().toLowerCase().contains(charSequence.toString().toLowerCase()))
                        filtered.add(language);
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new LanguageRecyclerViewAdapter(filtered, UserConfigurationFragment.this);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return view;
    }

    @Override
    public void selectedOptions(Language language, UserType type) {
        user.setType(type);
        user.setLanguage(language);
        user.setState(State.WAITING);

        connectMe.execute(user);
    }

    AsyncTask<User, Void, Void> connectMe = new AsyncTask<User, Void, Void>() {
        @Override
        protected Void doInBackground(User... users) {
            NetworkManager.connection(users[0]);
            return null;
        }
    };
}
