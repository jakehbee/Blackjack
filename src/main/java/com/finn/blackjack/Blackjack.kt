package com.finn.blackjack

import com.finn.blackjack.Game.setUp
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
    if (File(filePath).isFile) {
        setUp(filePath)
    } else {
        println("Invalid file...using default file.")
        setUp()
    }
    playBlackjack()
}