package com.yepdevelopment.spammedaddy.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yepdevelopment.spammedaddy.Types.Contributor;
import com.yepdevelopment.spammedaddy.ViewHolders.GenericViewHolder;
import com.yepdevelopment.spammedaddy.databinding.ComponentContributorBinding;

import java.util.ArrayList;
import java.util.List;

public class ContributorAdapter extends GenericAdapter<ComponentContributorBinding> {
    private final List<Contributor> contributors;

    public ContributorAdapter(@NonNull Context context, List<Contributor> contributors) {
        super(context, ComponentContributorBinding.class);
        if (contributors == null) {
            this.contributors = new ArrayList<>(0);
        } else {
            this.contributors = contributors;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<ComponentContributorBinding> holder, int position) {
        Contributor contributor = contributors.get(position);
        if (contributor == null) return;

        ComponentContributorBinding binding = holder.getBinding();
        binding.textContributorName.setText(contributor.getName());
        binding.textContributorRole.setText(contributor.getRole());

        if (!contributor.getImageUri().isEmpty()) {
            binding.imageContributorPhoto.setImageURI(Uri.parse(contributor.getImageUri()));
        }
    }

    @Override
    public int getItemCount() {
        return contributors.size();
    }
}
