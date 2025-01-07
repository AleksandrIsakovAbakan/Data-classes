data class Film(val name: String, val sessions: IntArray) {

    fun getInfoFilm(){
        println("Фильм $name")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Film

        if (name != other.name) return false
        if (!sessions.contentEquals(other.sessions)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + sessions.contentHashCode()
        return result
    }
}