


<b>Execution<br/>

The game comes in the form of an executable jar. It can be run by running one of the following commands.
* java -jar JakeFinnBlackjack-1.0.jar                                                     
* java -jar JakeFinnBlackjack-1.0.jar C:\path\to\file\myFile.txt                          

<p>
If no file is provided in the initial command, the application will request the user to input a valid file.<br/>
If the input is incorrect, or the input is not valid, a default deck will be used.</br>
</p>
<br>
<p>
Below is a table of the listed requirements, each requirement references the line number or function name where the functionality can be demonstrated.
</p>
<br/>

| Requirement                                                                                                                                                  | Class + Line reference or function                                                                                                                                                          | 
|--------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| Create a single deck of playing cards                                                                                                                        | GameUtilsTest.kt:82                                                                                                                                                 | 
| Two players (called Sam and the Dealer) who will play against each other                                                                                     | Game.kt:17                                                                                                                                                          | 
| Each player is given two cards from the top of a shuffled deck of cards.                                                                                     | com.finn.blackjack.Dealer.Companion.shuffleDeck </br> test.java.GameLogicTest.dealCards_unshuffledCardsTakenFromTopOfTheDeck_alternatingOrderWithSamFirst           | 
| Cards are given in the following order: [sam, dealer, sam, dealer]                                                                                           | test.java.GameLogicTest.dealCards_unshuffledCardsTakenFromTopOfTheDeck_alternatingOrderWithSamFirst                                                                 | 
| Determine score of a hand[1]                                                                                                                                 | com.finn.blackjack.Player                                                                                                                                           | 
| Check if either player has Blackjack with their initial hand and wins the game. Blackjack is an initial score of 21 with two cards: A + [10, J, Q, K]        | GameLogic.kt:33-49                                                                                                                                                  | 
| Sam wins when both players starts with Blackjack                                                                                                             | test.java.GameLogicTest.samAndDealerHaveBlackjackOnFirstRound_samWins                                                                                                                                                    | 
| Dealer wins when both players starts with 22 (A + A)                                                                                                         | samAndDealerHaveBlackjackOnFirstRound_samWins                                                                                                                                                     | 
| If neither player has Blackjack then sam can start drawing cards from the top of the deck                                                                    | test.java.GameLogicTest.neitherPlayerHasBlackjack_samStartsDrawing                                                                                                  | 
| Sam must stop drawing cards from the deck if their total reaches 17 or higher                                                                                | test.java.PlayerTest.samHitsLimit_samStops                                                                                                                                                   | 
| Sam has lost the game if their total is higher than 21                                                                                                       | test.java.PlayerTest.samHasBust_gameEndsWithNoMoreCardsDrawn                                                                                                        | 
| When sam has stopped drawing cards the dealer can start drawing cards from the top of the deck                                                               | test.java.GameLogicTest.dealCards_unshuffledCardsTakenFromTopOfTheDeck_alternatingOrderWithSamFirst | 
| The dealer must stop drawing cards when their total is higher than sam.                                                                                      | test.java.PlayerTest.dealerCardsHigherThanSam_dealerStops                                                                                                           | 
| The dealer has lost the game if their total is higher than 21                                                                                                | GameLogic.kt:41                                                                                                                                                     | 
| Determine which player wins the game (highest score wins) [1]                                                                                                | GameLogic.kt:40                                                                                                                                                     | 
| Numbered cards are their point value. Jack, Queen and King count as 10 and Ace counts as 11.                                                                 | test.java.GameUtilsTest.literalCardValueTest                                                                                                                        | 
| ##Input The game should be able to read a file containing a deck of cards, taking the reference to the file as a command line argument, as a starting point. | test.java.GameUtilsTest.csvFile_fileAccepted                                                                                                                        | 
| If no file is provided, a new shuffled deck of 52 unique cards should be initialized.                                                                        | Game.kt:15                                                                                                                                                          | 
| The list is in the following format: CA, D4, H7, SJ,..., S5, S9, D10 ###Suits: C: Clubs D: Diamonds H: Hearts S: Spades                                      | src/main/resources/normalDeck.txt                                                                                                                                   | 
| The output should look like:<br> </br> sam <br> </br> sam: CA, H9 <br> </br> dealer: D5, HQ, S8                                                              | GameLogic.kt:28-30                                                                                                                                                  | 
