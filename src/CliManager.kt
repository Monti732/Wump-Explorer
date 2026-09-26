import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required

object CliManager {
  private val parser = ArgParser("Wump Explorer")
  
  private val login by parser.option(
    ArgType.String,
    shortName = "l",
    description = "User's login."
  ).required()
  
  private val password by parser.option(
    ArgType.String,
    shortName = "p",
    description = "User's password."
  ).required()
  
  private val action by parser.option(
    ArgType.String,
    shortName = "a",
    description = "User's action."
  ).required()
  
  private val resource by parser.option(
    ArgType.String,
    shortName = "r",
    description = "Requested resource name."
  ).required()
  
  private val volume by parser.option(
    ArgType.Int,
    shortName = "v",
    description = "Volume of requested resource."
  ).required()
  
  fun getValues(args: Array<String>) : CliOptions {
    parser.parse(args)
    return CliOptions(
      login = login,
      password = password,
      action = action,
      resource = resource,
      volume = volume
    )
  }
}

data class CliOptions(
  val login: String,
  val password: String,
  val action: String,
  val resource: String,
  val volume: Int,
)