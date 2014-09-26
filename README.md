# A rest service example with spring-boot, spring-jdbc and scala

I like spring-boot. I think it will be the future of java framework. Only a few files, you can setup a java micro service. You can even write all codes in groovy or scala.

This project is an example to use scala with spring-boot to provide a simple rest service.
Comparing to java version, following issues need to be solved.

1. Spring's default message resolver cannot handle scala class. Need to register a new one.
2. When invoking java code from scala, need to do some convertion from scala's AnyVal to their relevant java object type and scala's collection to java's when passing parameters to java code.
