package chapter7.volatile.incorrect


class Something {

    @Volatile
    private var type = 0
    private var title = ""

    fun setTitle(newTitle: String) {
        when(type) {
            0 -> title = newTitle
            else -> throw Exception("Invalid State")
        }
    }
}