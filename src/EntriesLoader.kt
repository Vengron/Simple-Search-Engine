package search

import java.io.File
import java.util.Scanner
import kotlin.system.exitProcess

class EntriesLoader {
    val indexedEntries = mutableMapOf<String, MutableSet<Int>>()
    val entries = mutableListOf<String>()

    fun loadEntries(args: Array<String>) {
        if(!args.contains("-data")) {
            println("Incorrect args!")
            exitProcess(1)
        }
        val scanner = Scanner(File(args[1]))
        var index = 0
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            entries.add(line)
            for(s in line.toLowerCase().split(" ")) {
                if (indexedEntries.containsKey(s)) {
                    indexedEntries[s]?.add(index)
                } else {
                    indexedEntries[s] = mutableSetOf(index)
                }
            }
            ++index
        }
        scanner.close()
    }
}