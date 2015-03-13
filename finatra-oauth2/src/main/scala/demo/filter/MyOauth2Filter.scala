package demo.filter

import com.twitter.finagle.{Service, OAuth2, OAuth2Request, Filter}
import com.twitter.finagle.http.{Status, Version, Response, Request}
import com.twitter.finagle.oauth2.{OAuthError, OAuthErrorHandler, DataHandler}
import com.twitter.util.Future
import play.api.libs.json.Json

class MyOAuth2Filter[U](dataHandler: DataHandler[U])
  extends Filter[Request, Response, OAuth2Request[U], Response] with OAuth2 with OAuthErrorHandler {

  override def handleError(e: OAuthError) = e.toHttpResponse

  def apply(req: Request, service: Service[OAuth2Request[U], Response]) = {
    if (req.path == "/v1/auth") {
      issueAccessToken(req, dataHandler) flatMap { token =>
        val rep = Response(Version.Http11, Status.Ok)
        //rep.setContentString(s"access_token=${token.accessToken}&expires_in=${token.expiresIn}&refresh_token=${token.refreshToken}")
        val respJson = Json.obj(
          "access_token" -> token.accessToken,
          "expires_in" -> token.expiresIn,
          "refresh_token" -> token.refreshToken)
        rep.setContentType("application/json")
        rep.setContentString(respJson.toString())
        Future.value(rep)
      } handle {
        case e: OAuthError => e.toHttpResponse
      }
    } else {
      authorize(req, dataHandler) flatMap { authInfo =>
        service(OAuth2Request(authInfo, req))
      } handle {
        case e: OAuthError => handleError(e)
      }
    }
  }
}
