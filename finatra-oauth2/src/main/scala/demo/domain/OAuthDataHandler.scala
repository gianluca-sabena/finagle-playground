package demo.domain

import java.util.Date

import com.twitter.finagle.oauth2.{AccessToken, AuthInfo, DataHandler}
import com.twitter.util.Future

class OAuthDataHandler extends DataHandler[User] {
    val userAaccessToken = AccessToken("token1", Some("refreshToken1"), Some("all"), Some(3600), new java.util.Date())

    def validateClient(clientId: String, clientSecret: String, grantType: String) =
      Future.value(true)

    def findUser(username: String, password: String): Future[Option[User]] ={
      println("====="+username)
      Future.value(Some(User(10000, "username")))
    }

    def createAccessToken(authInfo: AuthInfo[User]) =
      Future.value(userAaccessToken)

    def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] =
      Future.value(None)

    def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] =

      Future.value(None)

    def findClientUser(clientId: String, clientSecret: String, scope: Option[String]): Future[Option[User]] =
      Future.value(None)

    def findAccessToken(token: String): Future[Option[AccessToken]] = {
      println(" ===== FIND TOKEN")
      if(token=="token1") Future.value(Some(userAaccessToken))
      else Future.value(None)
    }

    def findAuthInfoByAccessToken(accessToken: AccessToken): Future[Option[AuthInfo[User]]] = {
      println(" ===== FIND TOKEN")
      if(accessToken.token=="token1") {
        val u = User(10000, "username")
        Future.value(Some(AuthInfo(u,"client_id",None,None)))
      } else
        Future.value(None)
    }


    def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AccessToken]] =
      Future.value(None)

    def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AccessToken] =
      Future.value(AccessToken("", Some(""), Some(""), Some(0L), new Date()))

}
