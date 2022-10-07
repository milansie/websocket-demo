# websocket-demo
simple demo to compare differences between myfaces (2.3-next) and omnifaces websocket implementation

*note: use myfaces-2.3-next-M8 or later to work correctly*

compile and run on apache tomcat

**user field** - set up user for session scope (use same or different in more browser to see behaviour)
**buttons** - send websocket message immediately / different channels 

- **simple** - simple push message (application scope, all users get the message)
- **simple user** - session scope message - only user with defined username get the message 
- **future** - application scope in future thread (get the message about started thread and get the real 
message after 2 second - `CompletableFuture` is used

*note: similar behaviour for jsf and omnifaces implementation*

**Scheduler**

Is set to 10 seconds to send new message. For "user" channel is using value of field next to combobox. 
User comma separated users to send message to more user at once ie: nobody,nobody1
