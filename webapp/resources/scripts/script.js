function onMessage (message, channel) {
    document.getElementById("simpleForm:simpleChannelOutput").textContent=channel + ": " + message;
    console.log("onMessage : " +  channel + " -> " + message);
}