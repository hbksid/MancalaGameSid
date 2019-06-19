package MancalaGame.MancalaModel

import org.springframework.util.StringUtils

class Mancala {

    var message: String? = null
    var firstPlayer: Players? = null
    var secondPlayer: Players? = null
    var gameBoard: GameBoard? = null

    constructor(){
        this.gameBoard = GameBoard()
        this.firstPlayer = Players()
        this.secondPlayer = Players()

        firstPlayer!!.playerNumber = 1
        secondPlayer!!.playerNumber = 2
        firstPlayer!!.isActive = true
        secondPlayer!!.isActive = true
    }

    var activePlayer: Players? = null
        get() = if (firstPlayer!!.isActive) firstPlayer else secondPlayer
}