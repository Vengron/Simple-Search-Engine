package search

import java.util.Scanner

class SearchEngine {

    private val scanner = Scanner(System.`in`)
    lateinit var entries: MutableList<String>
    lateinit var indexedEntries: MutableMap<String, MutableSet<Int>>

    fun doSearch() {
        println("Select a matching strategy: ALL, ANY, NONE")
        val strategy = scanner.nextLine().toUpperCase()
        println("Enter a name or email to search all suitable people.")
        val searchQuery = scanner.nextLine().toLowerCase().split(" ")
        val results = when(strategy) {
            "ALL" -> searchAll(searchQuery)
            "NONE" -> searchNone(searchQuery)
            else -> searchAny(searchQuery)
        }
        if (results.isEmpty()) {
            println("No matching people found.")
        } else {
            println("${results.size} persons found: ")
            for (i in results) {
                println(entries[i])
            }
        }
        println()
    }

    private fun searchAll(query: List<String>): Set<Int> {
        val foundsIndices = mutableMapOf<Int, Int>()
        for (q in query) {
            if (indexedEntries.containsKey(q)) {
                for (i in indexedEntries.getValue(q)) {
                    if (foundsIndices.containsKey(i)) {
                        foundsIndices[i] = foundsIndices.getValue(i) + 1
                    } else {
                        foundsIndices[i] = 1
                    }
                }
            }
        }
        val results = mutableSetOf<Int>()
        for (f in foundsIndices) {
            if (f.value == query.size) {
                results.add(f.key)
            }
        }
        return results
    }

    private fun searchNone(query: List<String>): Set<Int> {
        val results = entries.indices.toMutableSet()
        for (q in query) {
            if (indexedEntries.containsKey(q)) {
                for (i in indexedEntries.getValue(q)) {
                    results.remove(i)
                }
            }
        }
        return results
    }

    private fun searchAny(query: List<String>): Set<Int> {
        val results = mutableSetOf<Int>()
        for (q in query) {
            if (indexedEntries.containsKey(q)) {
                for (i in indexedEntries.getValue(q)) {
                    results.add(i)
                }
            }
        }
        return results
    }

    fun printEntries() {
        println("=== List of people ===")
        for (entry in entries) {
            println(entry)
        }
        println()
    }
}