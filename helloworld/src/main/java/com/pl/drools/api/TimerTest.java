package com.pl.drools.api;

import com.pl.drools.fact.ResultEvent;
import com.pl.drools.fact.Server;
import com.pl.drools.filter.MyAgendaFilter;
import com.pl.drools.wrapper.CalendarWrapper;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.time.Calendar;
import org.quartz.impl.calendar.WeeklyCalendar;

public class TimerTest {

    @Test
    public void timerTest() throws InterruptedException {
        final KieSession kieSession = createKnowledgeSession();

        ResultEvent event = new ResultEvent();
        kieSession.setGlobal("event", event);
        final Server server = new Server(1);

        new Thread(()->{
            kieSession.fireUntilHalt();
        }).start();

        FactHandle serverHandler = kieSession.insert(server);

        for(int i=8;i<15;i++){
            Thread.sleep(2000);
            server.setTimes(++i);
            kieSession.update(serverHandler,server);
        }

        Thread.sleep(3000);
        kieSession.halt();
        System.out.println(event.getEvents());
    }

    @Test
    public void calenderTest() throws InterruptedException {
        final KieSession kieSession = createKnowledgeSession();
        WeeklyCalendar weekDayCal = new WeeklyCalendar();
        weekDayCal.setDayExcluded(java.util.Calendar.THURSDAY, true); // 设置为true则不包含此天，周四
        Calendar calendar = new CalendarWrapper(weekDayCal);

        kieSession.getCalendars().set("weekday", calendar);

        kieSession.insert(new String("Hello"));
        kieSession.fireAllRules(new MyAgendaFilter("calendar-rule"));

        kieSession.dispose();
        System.out.println("Bye");
    }

    private KieSession createKnowledgeSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rule");
        return kSession;
    }

}
