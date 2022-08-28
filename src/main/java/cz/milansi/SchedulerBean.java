package cz.milansi;


import de.mirkosertic.cdicron.api.Cron;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.Serializable;

@Singleton
@Named
public class SchedulerBean implements Serializable {


    private String schedulerChannelType = "SIMPLE";

    @Inject
    private transient TestBean bean;

    @Cron(cronExpression = "*/10 * * * * ?")
    public void atSchedule() throws InterruptedException {


        switch (schedulerChannelType) {
            case "SIMPLE":
                bean.simplePush("Sch");
                break;
            case "OMNISIMPLE":
                bean.omniSimplePush("Sch");
                break;
            case "FUTURE":
                bean.futurePush("Sch");
                break;
            case "OMNIFUTURE":
                bean.omniFuturePush("Sch");
                break;
            default:
                bean.simplePush("Sch");
        }

    }

    public String getSchedulerChannelType() {
        return schedulerChannelType;
    }

    public void setSchedulerChannelType(String schedulerChannelType) {
        this.schedulerChannelType = schedulerChannelType;
    }
}