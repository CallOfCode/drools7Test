package com.pl.drools.api;

import com.pl.drools.filter.MyAgendaFilter2;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RepeatTest {

    @Test
    public void test(){
        final KieSession kieSession = createKnowledgeSession();

        for(int i=0;i<5;i++){
            Object obj = new Object();
            kieSession.insert(obj);
        }
        System.out.println("begin fire");
        int count = kieSession.fireAllRules(new MyAgendaFilter2("repeat-"));
        System.out.println("Fire " + count + " rules!");
    }

    private KieSession createKnowledgeSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rule");
        return kSession;
    }

}
