import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlin.system.exitProcess

object CliManager {
  fun parseArguments(args: Array<String>): CliOptions? {
    if (args.contains("-h") || args.contains("--help")) {
      val parser = ArgParser("app.jar")
      registerAllOptions(parser)
      parser.parse(args)
      exitProcess(1) // Справка запрошена
    }

    val parser = ArgParser("app.jar")
    val loginOpt = parser.option(ArgType.String, "login", "l", "User's login.")
    val passwordOpt = parser.option(ArgType.String, "password", "p", "User's password.")
    val actionOpt = parser.option(ArgType.String, "action", "a", "User's action.")
    val resourceOpt = parser.option(ArgType.String, "resource", "r", "Requested resource name.")
    val volumeOpt = parser.option(ArgType.String, "volume", "v", "Volume of requested resource.")

    try {
      parser.parse(args)
    } catch (e: Exception) {
      showHelpAndExit()
    }

    val login = loginOpt.value ?: showHelpAndExit()
    val password = passwordOpt.value ?: showHelpAndExit()
    val action = actionOpt.value ?: showHelpAndExit()
    val resource = resourceOpt.value ?: showHelpAndExit()
    val volumeStr = volumeOpt.value ?: showHelpAndExit()

    val volume = volumeStr.toIntOrNull()
    if (volume == null) {
      exitProcess(7) // Ошибка формата объема
    }

    return CliOptions(login, password, action, resource, volume)
  }

  private fun registerAllOptions(parser: ArgParser) {
    parser.option(ArgType.String, "login", "l", "User's login.")
    parser.option(ArgType.String, "password", "p", "User's password.")
    parser.option(ArgType.String, "action", "a", "User's action.")
    parser.option(ArgType.String, "resource", "r", "Requested resource name.")
    parser.option(ArgType.String, "volume", "v", "Volume of requested resource.")
  }

  private fun showHelpAndExit(): Nothing {
    val parser = ArgParser("app.jar")
    registerAllOptions(parser)
    parser.parse(arrayOf("-h"))
    exitProcess(1)
  }
}

data class CliOptions(
  val login: String,
  val password: String,
  val action: String,
  val resource: String,
  val volume: Int
)
