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
        User(
            login = "alice",
            hash = "077317626a29b193cf722eb4ac84e22ccd50a69e2d88072c1f3b33b154cfc9b8",
            salt = "salt1"
        ),
        User(
            login = "bob",
            hash = "e1f114c42c6a926c821b0f8ffe49b38f6b28f29dd2574869cd72455cb6253ab5",
            salt = "salt2"
        ),
        User(
            login = "aboba",
            hash = "37ef143ac81c06d76b9ebf52d70bda466402af15cad6af6739df7b65aab3ca88",
            salt = "salt3"
        )
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
        Permission(login = "bob", resource = "A.B", action = Action.WRITE),
        Permission(login = "aboba", resource = "A", action = Action.READ)
    )
}
