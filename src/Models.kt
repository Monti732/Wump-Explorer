data class User(
    val login: String,
    val hash: String,
    val salt: String
)

enum class Action {
    READ, WRITE, EXECUTE
}

// Модель Права доступа к конкретному ресурсу
data class Permission(
    val login: String,
    val resource: String,
    val action: Action
)

data class Resource(
    val path: String,
    val maxVolume: Int
)

object Database {
    // Список пользователей
    // Для alice: пароль "qwerty", соль "salt1". Хэш вычислен заранее
    // Для bob: пароль "12345", соль "salt2". Хэш вычислен заранее
    val users = listOf(
        User(login = "alice", hash = "4c6d9d4370211181f62c0709b407421f156d967e81255e2d1d4511d5f3d4cb80", salt = "salt1"),
        User(login = "bob", hash = "cd817887e2b6a9539d09c25d8a0c20a4d4b312b91953eb6368d40e32f4185f26", salt = "salt2")
    )

    // Список существующих ресурсов и их лимиты объема (целые числа)
    val resources = listOf(
        Resource(path = "A", maxVolume = 100),
        Resource(path = "A.B", maxVolume = 50),
        Resource(path = "A.B.C", maxVolume = 10),
        Resource(path = "X.Y", maxVolume = 1000)
    )

    val permissions = listOf(
        Permission(login = "alice", resource = "A", action = Action.READ),
        Permission(login = "bob", resource = "A.B", action = Action.WRITE)
    )
}
