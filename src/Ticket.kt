data class Ticket(val film: Film, val session: Int, val space: Int) {

    override fun toString(): String {
        return "Ticket(film=${film.name}, session=$session, space=$space)"
    }
}