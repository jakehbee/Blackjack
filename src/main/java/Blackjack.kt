package com.finn.blackjack

import com.finn.blackjack.GameLogic.Companion.playBlackjack
import java.util.*

fun main(args: Array<String>) {
    val filePath:String = if (args.isEmpty()) {
        println("Please provide a file as a command-line argument:")
        Scanner(System.`in`).next()
    } else{
        args[0]
    }
    Game.setUp(filePath)
    playBlackjack()
}