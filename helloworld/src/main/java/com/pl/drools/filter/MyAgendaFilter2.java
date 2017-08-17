package com.pl.drools.filter;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

public class MyAgendaFilter2 implements AgendaFilter {
    private String ruleName;

    public MyAgendaFilter2(String ruleName) {
        this.ruleName = ruleName;
    }

    @Override
    public boolean accept(Match match) {
        return match.getRule().getName().startsWith(ruleName)?true:false;
    }
}
