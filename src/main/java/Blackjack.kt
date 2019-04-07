package com.finn.blackjack

import com.finn.blackjack.GameLogic.Companion.playBlackjack
import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val filePath: String = if (args.isEmpty()) {
        println("Please provide a file as a command-line argument:")
        Scanner(System.`in`).next()
    } else {
        args[0]
    }
    println(filePath)
    if (File(filePath).isFile) {
        Game.setUp(filePath)
    } else {
        println("Invalid file")
        Game.setUp()
    }

    playBlackjack()
}