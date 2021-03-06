/*
 * Copyright (C) 2015 Mehmet Akif Tütüncü
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mehmetakiftutuncu.mykentkart.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mehmetakiftutuncu.mykentkart.fragments.HelpPageFragment;
import com.mehmetakiftutuncu.mykentkart.models.HelpPage;

/**
 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that maps each
 * {@link com.mehmetakiftutuncu.mykentkart.models.HelpPage} to a
 * {@link com.mehmetakiftutuncu.mykentkart.fragments.HelpPageFragment}
 *
 * @author mehmetakiftutuncu
 */
public class HelpPageAdapter extends FragmentStatePagerAdapter {
    /**
     * Array of {@link com.mehmetakiftutuncu.mykentkart.fragments.HelpPageFragment}s that are
     * pages in {@link com.mehmetakiftutuncu.mykentkart.activities.HelpActivity}
     */
    private HelpPageFragment[] helpPageFragments;

    /**
     * Constructor initializing all values
     *
     * @param fragmentManager Instance of {@link android.support.v4.app.FragmentManager} needed
     *                        for {@link android.support.v4.app.FragmentStatePagerAdapter} constructor
     * @param helpPages       Array of {@link com.mehmetakiftutuncu.mykentkart.models.HelpPage} objects
     *                        containing data of pages in {@link com.mehmetakiftutuncu.mykentkart.activities.HelpActivity}
     */
    public HelpPageAdapter(FragmentManager fragmentManager, HelpPage[] helpPages) {
        super(fragmentManager);

        this.helpPageFragments = new HelpPageFragment[helpPages.length];
        for (int i = 0; i < helpPageFragments.length; i++) {
            HelpPage helpPage = helpPages[i];

            helpPageFragments[i] = HelpPageFragment.with(helpPage.titleResourceId, helpPage.imageResourceId, helpPage.messageResourceId);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return helpPageFragments != null ? helpPageFragments[position] : null;
    }

    @Override
    public int getCount() {
        return helpPageFragments != null ? helpPageFragments.length : -1;
    }
}
