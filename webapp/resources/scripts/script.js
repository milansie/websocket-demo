function onMessage (message, channel) {
    document.getElementById("simpleForm:simpleChannelOutput").textContent=channel + ": " + message;
    console.log(getDateTime() + ": onMessage : " +  channel + " -> " + message);
}


function getDateTime() {
    let today = new Date();
    let date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds() + "." + today.getMilliseconds();
    return date + " " + time;
}