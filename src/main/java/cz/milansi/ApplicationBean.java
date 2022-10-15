package cz.milansi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.commons.lang3.RandomStringUtils;
import org.omnifaces.config.OmniFaces;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Named
@ApplicationScoped
public class ApplicationBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(ApplicationBean.class.getName());


    @Inject
    @Push(channel = "simpleChannel")
    private transient PushContext simpleChannelPush;

    @Inject
    @Push(channel = "simpleUserChannel")
    private transient PushContext simpleUserChannelPush;

    @Inject
    @Push(channel = "futureChannel")
    private transient PushContext futureChannelPush;

    @Inject
    @org.omnifaces.cdi.Push(channel = "omniSimpleChannel")
    private transient org.omnifaces.cdi.PushContext omniSimpleChannelPush;

    @Inject
    @org.omnifaces.cdi.Push(channel = "omniSimpleUserChannel")
    private transient org.omnifaces.cdi.PushContext omniSimpleUserChannelPush;

    @Inject
    @org.omnifaces.cdi.Push(channel = "omniFutureChannel")
    private transient org.omnifaces.cdi.PushContext omniFutureChannelPush;


    public void simplePush(String s) {
        String randomString = RandomStringUtils.random(4, true, false);
        simpleChannelPush.send("["+ s+ "] " + randomString);
    }

    public void simpleUserPush(String s, String... users) {
        String randomString = RandomStringUtils.random(4, true, false);
        for (String user : users) {
            simpleUserChannelPush.send("[" + s + " / " + user + "] + " + randomString, user);
        }
    }

    public void omniSimplePush(String s) {
        String randomString = RandomStringUtils.random(4, true, false);
        omniSimpleChannelPush.send("["+ s+ "] " +randomString);
    }
    public void omniSimpleUserPush(String s, String... users) {
        String randomString = RandomStringUtils.random(4, true, false);
        for (String user : users) {
            omniSimpleUserChannelPush.send("[" + s + " / " + user + "] + " + randomString, user );
        }
    }

    public void futurePush(String s) {
        futureChannelPush.send("START");

        CompletableFuture.supplyAsync(() -> {
                    String randomString = RandomStringUtils.random(4, true, false);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException irex) {
                        // Restore interrupted state...
                        Thread.currentThread().interrupt();
                    }

                    return randomString;
                }

        ).thenAccept(message -> {
                try {
                    futureChannelPush.send("["+ s+ "] " +message);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        });
    }

    public void omniFuturePush(String s) {
        omniFutureChannelPush.send("START");

        CompletableFuture.supplyAsync(() -> {
                    String randomString = RandomStringUtils.random(4, true, false);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException irex) {
                        // Restore interrupted state...
                        Thread.currentThread().interrupt();
                    }

                    return randomString;
                }

        ).thenAccept(message -> {
            try {
                omniFutureChannelPush.send("["+ s+ "] " + message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public String getJsfVersion() {
        Package jsf = FacesContext.class.getPackage();
        return jsf.getImplementationVendor() + " / " + jsf.getImplementationTitle() + " / "
                + jsf.getImplementationVersion();
    }

    public String getOmniVersion() {
        Package omniFaces = OmniFaces.class.getPackage();
        return omniFaces.getImplementationTitle() + " / " + omniFaces.getImplementationVersion();
    }

}
