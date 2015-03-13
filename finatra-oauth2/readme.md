# Finagle playground

Http restful api developed with Finatra http://finatra.info

## Finatra-oauth2

Experiments with, https://github.com/finagle/finagle-oauth2

How to run:

* Checkout finagle-oauth2 version 0.1.3(*) https://github.com/finagle/finagle-oauth2/tree/0.1.3
* Compile and publish local
* compile and run finatra-oauth2

Version 0.1.4 does not work because depends on finagle-httpx 6.24.0 and in combination with finatra 1.6.0
triggers a demo.ApiHttpServer$.failfastOnFlagsNotParsed(). This is probably related to this comment
https://github.com/twitter/util/blob/master/util-app/src/main/scala/com/twitter/app/App.scala#L40


## Notes

* http://oauthbible.com/





