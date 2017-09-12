package br.com.casproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.casproject.NovaSolicitacaoFragment;
import br.com.casproject.SolicitacaoAbertaFragment;
import br.com.casproject.SolicitacaoFechadaFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_TABS = 3;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SolicitacaoAbertaFragment.newInstance();
            case 1:
                return SolicitacaoFechadaFragment.newInstance();
            default:
                return NovaSolicitacaoFragment.newInstance(/*this.activity*/);
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Solicitações Abertas";
        }

        if (position == 1) {
            return "Solicitações Fechadas";
        }

        return "Nova Solicitação";
    }
}