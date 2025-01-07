fun main() {
    /*
    Написать программу по продаже билетов в кино.
    Пользователь покупает билет, в который заносятся данные названия фильма, места и времени начала сеанса.
    Проданные билеты заносятся в массив архива.
    По желанию можно вывести все проданные билеты на фильм.
    */
    startCinema()
}

fun startCinema(){
    val cinema = Cinema(
        intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    )
    val films = arrayOf(
        Film("Wizard of the Emerald City", intArrayOf(12, 14)),
        Film("Finist. The first hero", intArrayOf(16, 18)),
        Film("Knock on my Tver", intArrayOf(20)),
        Film("The illusion of control", intArrayOf(22))
    )
    while (true) {
        println("Купить билет - 1, Список проданных билетов на фильм - 2, выход - 0")
        val count = readln().toIntOrNull()
        when (count){
            1 -> {
                val ticket = buyTicket(films, cinema) ?: return
                cinema.archive.add(ticket)
                continue
            }
            2 -> {
                val film = movieSelection(films) ?: return
                listOfTicketsSoldForFilm(cinema, film)
                continue
            }
            else -> return
        }
    }
}

fun buyTicket(films: Array<Film>, cinema: Cinema): Ticket? {
    println("Покупаем билет.")
    val film = movieSelection(films) ?: return null
    val session = sessionSelection(film) ?: return null
    return spaceSelection(film, session, cinema)
}

fun movieSelection(films: Array<Film>): Film? {
    println("Выберите номер фильма. Выход - 0")
    for (a in films.indices) println("${a + 1} - ${films[a].name}")
    val numberFilm = readln().toIntOrNull() ?: return null
    return when (numberFilm){
        in 1..films.size -> films[numberFilm - 1]
        else -> null
    }
}

fun sessionSelection(film: Film): Int? {
    println("Выберите номер сеанса. Выход - 0")
    for (a in film.sessions) println("$a")
    val numberSession = readln().toIntOrNull() ?: return null
    return when (numberSession){
        in film.sessions -> numberSession
        else -> null
    }
}

fun spaceSelection(film: Film, session: Int, cinema: Cinema): Ticket? {
    println("Выберите номер места. Выход - 0")
    for (b in cinema.arraySpace){
        if (!cinema.archive.contains(Ticket(film, session, b))) print("$b ")
    }
    while (true) {
        val space = readln().toIntOrNull() ?: return null
        when (space) {
            in 1..cinema.arraySpace.size -> {
                val ticket = Ticket(film, session, space)
                if (cinema.archive.contains(ticket)){
                    println("Место занято. Выберите другое место.")
                    continue
                } else {
                    println("Билет куплен $ticket")
                    return ticket
                }
            }
            else -> return null
        }
    }
}

fun listOfTicketsSoldForFilm(cinema: Cinema, film: Film){
    println("Список проданных билетов на фильм: ${film.name}")
    val list = cinema.archive.filter { it.film == film }
        .sortedBy { it.session }
        .toList()
    for (c in list) println(c)
}