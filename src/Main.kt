package search

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val loader = EntriesLoader()
    loader.loadEntries(args)
    val engine = SearchEngine()
    engine.entries = loader.entries
    engine.indexedEntries = loader.indexedEntries
    do {
        println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit")
        val option = scanner.nextLine().toInt()
        when (option) {
            1 -> engine.doSearch()
            2 -> engine.printEntries()
            0 -> println("Bye!")
            else -> println("Incorrect option! Try again.")
        }
    } while (option != 0)
}
