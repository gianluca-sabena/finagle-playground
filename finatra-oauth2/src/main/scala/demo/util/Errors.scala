package demo.util

//class JsonBadRequest extends Exception

case class IntError(httpCode: Int, internalCode: Int, message: String = "") extends Exception

object ErrorCodes {
  final val invalidData = 4001
  final val unauthorised = 401
  final val forbidden = 4030
  final val idNotFound = 4041
  final val pathNotFound = 4045
  final val storageException = 900
  final val genericException = 1000
}

object Errors {
  def idNotFound(s: String = "") = IntError(404, ErrorCodes.idNotFound, s"$s id not found")
  def pathNotFound(s: String = "") = IntError(404, ErrorCodes.pathNotFound, s"$s path or verb not found")
  def invalidData(s: String = "") = IntError(400, ErrorCodes.invalidData, s"$s data not valid")
  def aerospikeException(s: String = "") = IntError(500, ErrorCodes.storageException, s"storage exception - $s")
  def genericException(s: String = "") = IntError(500, ErrorCodes.genericException, s"generic exception - $s")
  def unauthorised(s: String = "") = IntError(401, ErrorCodes.unauthorised, s"$s unauthorised")
  def forbidden(s: String = "") = IntError(403, ErrorCodes.forbidden, s"$s forbidden")
}




