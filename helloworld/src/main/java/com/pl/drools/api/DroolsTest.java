package com.pl.drools.api;

import com.pl.drools.fact.Product;
import com.pl.drools.filter.MyAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class DroolsTest {

    @Test
    public void testRules(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rule");

        Product product = new Product();
        product.setType(Product.DIAMOND);
        product.setDiscount(1);

        kSession.insert(product);
        int count = kSession.fireAllRules();

        System.out.println("命中"+count+"条规则");
        System.out.println("商品" +product.getType() + "的商品折扣为" + product.getDiscount() + "%。");
    }

    @Test
    public void testAgenda(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rule");

       //kSession.getAgenda().getAgendaGroup("abc").setFocus();
       kSession.fireAllRules();
       kSession.dispose();
    }

    @Test
    public void testAgenda2(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rule3");

        //kSession.getAgenda().getAgendaGroup("abc").setFocus();
        FactHandle handle = kSession.insert(new Object());
        kSession.fireAllRules();
        kSession.update(handle,new Object());
        kSession.fireAllRules();
        kSession.dispose();
    }


    @Test
    public void testAgendaFilter(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rule");
        kSession.fireAllRules(new MyAgendaFilter("test agenda-group"));
        kSession.dispose();
    }

    @Test
    public void testActivation(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rule2");

        FactHandle handle = kSession.insert(new Object());
        kSession.insert(new Object());
        int count = kSession.fireAllRules();
        System.out.println("命中"+count+"条规则");
        kSession.update(handle,new Object());
        int count2 = kSession.fireAllRules();


    }

}
