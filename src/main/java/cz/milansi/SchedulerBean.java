package cz.milansi;


//import de.mirkosertic.cdicron.api.Cron;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;


//import javax.inject.Inject;
//import javax.inject.Named;
//import javax.inject.Singleton;
import java.io.Serializable;

@ApplicationScoped
@Named
public class SchedulerBean implements Serializable {

    private String schedulerChannelType = "SIMPLE";

    private String externalUser = "nobody";

    @Inject
    private transient ApplicationBean bean;

//    @Cron(cronExpression = "*/10 * * * * ?")
    public void atSchedule() {

        switch (schedulerChannelType) {
            case "SIMPLE":
                bean.simplePush("Sch");
                break;
            case "OMNISIMPLE":
                bean.omniSimplePush("Sch");
                break;
            case "OMNIUSER":
                bean.omniSimpleUserPush("Sch", externalUser);
                if (externalUser.contains(",")) {
                    String[] userArray = externalUser.split(",");
                    for (String s : userArray) {
                        bean.omniSimpleUserPush("Sch", s);
                    }
                } else {
                    bean.omniSimpleUserPush("Sch", externalUser);
                }
                break;
            case "FUTURE":
                bean.futurePush("Sch");
                break;
            case "OMNIFUTURE":
                bean.omniFuturePush("Sch");
                break;
            case "USER":
                if (externalUser.contains(",")) {
                    String[] userArray = externalUser.split(",");
                    bean.simpleUserPush("Sch", userArray);
                } else {
                    bean.simpleUserPush("Sch", externalUser);
                }
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

    public String getExternalUser() {
        return externalUser;
    }

    public void setExternalUser(String externalUser) {
        this.externalUser = externalUser;
    }
}