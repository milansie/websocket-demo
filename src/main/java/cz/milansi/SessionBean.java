package cz.milansi;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class SessionBean implements Serializable {

    @Inject
    private ApplicationBean applicationBean;

    private String user = "nobody";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void simplePush(String s) {
        applicationBean.simplePush(s);
    }
    public void simpleUserPush(String s) {
        applicationBean.simpleUserPush(s, user);
    }

    public void omniSimplePush(String s) {
        applicationBean.omniSimplePush(s);
    }

    public void omniSimpleUserPush(String s) {
        applicationBean.omniSimpleUserPush(s, user);
    }

    public void futurePush(String s) {
        applicationBean.futurePush(s);
    }

    public void omniFuturePush(String s) {
        applicationBean.omniFuturePush(s);
    }
}
