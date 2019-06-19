# bound_service

Bound Service In Flutter.

## Getting Started

This is a simple implementation of a bound service in Flutter. This is based on [Android documentation (Bound Services)](https://developer.android.com/guide/components/bound-services.html#java).

Bound Services let you run background code inside your flutter app, even the application is left or open as it uses binding and unbinding of a service.

The simplest way to explain it:

1. You run flutter application with ```Flutter Platform Channel``` For Android
2. This implementation let your code in Java run in background. Ex. Music, downloads, recording, background location.
3. When the user leaves your application, the background code will remain opened until it is explicitly closed (by the application or OS).
4. Since it's a bound service, the service should be bound to an application (obligatory).
5. If the application is killed, the service is killed also by the system.

Folder to check the codes:
 - bound_service.dart -- in charge of communicating with Java
 - main.dart -- Top level communication with Java
 - BoundServicePlugin.java -- It is the plugin where all your background code which runs
 - LocalService.java -- contains all your Local Services in form of public methods, of whatever tricks you would like !

