# aspectj-demo
Example App for my lighting talk about AspectJ

See example of DemoService (demo.service.impl.SomeDemoService) which needs to apply following crosscut concerns to every method:

* Ensure authorization
* start performance monitoring
* Start transaction
* Log the start of operation
* Perform the core operation
* Log the completion of operation
* Commit or rollback transaction
* report performance

However only relevant code we want to _see_ is __Perform the core operation__ .

I've written couple of aspects:

* LoggingAspect
* PerformanceAspect
* SecurityAspect
* TransactionAspect

And applied then to demo.service.impl.SomeDemoAopService

You can compare them and refer to the tests.
