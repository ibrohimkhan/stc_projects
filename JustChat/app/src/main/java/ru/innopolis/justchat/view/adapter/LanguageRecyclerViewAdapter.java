package ru.innopolis.justchat.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ru.innopolis.justchat.R;
import ru.innopolis.justchat.model.Language;
import ru.innopolis.justchat.model.UserType;
import ru.innopolis.justchat.view.IOptions;

/**
 * Created by ibrahim on 7/8/2017.
 */

public class LanguageRecyclerViewAdapter extends RecyclerView.Adapter<LanguageRecyclerViewAdapter.ViewHolder> {
    private List<Language> languages;
    private IOptions iOptions;

    public LanguageRecyclerViewAdapter(List<Language> languages, IOptions iOptions) {
        this.languages = languages;
        this.iOptions = iOptions;
        Collections.sort(this.languages, Language.LanguageNameComparator);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Language language = languages.get(position);
        holder.bind(language);
    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView lang;
        private ImageView flag;

        private RadioButton toLearn;
        private RadioButton toTeach;

        private Language language;

        public ViewHolder(View itemView) {
            super(itemView);
            lang = (TextView) itemView.findViewById(R.id.language);
            flag = (ImageView) itemView.findViewById(R.id.flag);

            toLearn = (RadioButton) itemView.findViewById(R.id.toLearn);
            toTeach = (RadioButton) itemView.findViewById(R.id.toTeach);

            toLearn.setOnClickListener(this);
            toTeach.setOnClickListener(this);
        }

        public void bind(Language language) {
            this.language = language;

            lang.setText(language.getLanguage());
            flag.setImageResource(language.getFlag());
        }

        @Override
        public void onClick(View view) {
            boolean checked = ((RadioButton) view).isChecked();

            UserType type = null;
            switch (view.getId()) {

                case R.id.toLearn:
                    if (checked) type = UserType.LEARNER;
                    break;

                case R.id.toTeach:
                    if (checked) type = UserType.TEACHER;
                    break;
            }

            iOptions.selectedOptions(language, type);
        }
    }
}
