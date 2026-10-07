import kotlin.system.exitProcess

fun main(args: Array<String>) {
  val options = CliManager().parseArguments(args) ?: return

  val login = options.login
  val password = options.password
  val actionStr = options.action.uppercase()
  val resourcePath = options.resource
  val volume = options.volume

  val user = Database.users.find { it.login == login }
  if (user == null) {
    exitProcess(3) // Пользователь не найден
  }

  val calculatedHash = SecurityUtils.hashPassword(password, user.salt)
  if (calculatedHash != user.hash) {
    exitProcess(2) // Пароль неверен
  }

  val action = try {
    Action.valueOf(actionStr)
  } catch (e: IllegalArgumentException) {
    exitProcess(4) // Неизвестное действие
  }

  val parts = resourcePath.split(".")
  val nameRegex = Regex("^[a-zA-Z0-9_]{1,20}$")
  for (part in parts) {
    if (!nameRegex.matches(part)) {
      exitProcess(7) // Ошибка формата ресурса
    }
  }

  val currentResource = Database.resources.find { it.path == resourcePath }
  if (currentResource == null) {
    exitProcess(6) // Ресурс не существует
  }

  var hasAccess = false
  var currentPath = resourcePath

  while (currentPath.isNotEmpty()) {
    val permission = Database.permissions.find {
      it.login == login && it.resource == currentPath && it.action == action
    }

    if (permission != null) {
      hasAccess = true
      break
    }

    val lastDotIndex = currentPath.lastIndexOf('.')
    if (lastDotIndex == -1) {
      break
    }
    currentPath = currentPath.substring(0, lastDotIndex)
  }

  if (!hasAccess) {
    exitProcess(5) // Доступ запрещен
  }

  if (volume > currentResource.maxVolume) {
    exitProcess(8) // Превышение объема
  }

  exitProcess(0) // Успех
}
