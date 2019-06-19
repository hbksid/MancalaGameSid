package MancalaGame.MancalaService

import MancalaGame.MancalaModel.GameBoard
import MancalaGame.MancalaModel.Mancala
import MancalaGame.MancalaModel.Pit
import MancalaGame.MancalaModel.Players
import org.springframework.stereotype.Service



@Service("mancalaService")
class MancalaService {

    var gameMessage = ""

    fun beginGame(pitNumberClicked: Int, mancala: Mancala): Mancala {

        var newGame = mancala.gameBoard
        var index = pitNumberClicked + 1
        var pitClicked = mancala.gameBoard!!.getPitNumber(pitNumberClicked)
        var numberOfSeedsInPit = pitClicked.totalSeeds
        var pits = mancala.gameBoard!!.pits
        pits!![pitNumberClicked].totalSeeds = 0
        var activePlayer = mancala.activePlayer

        moveSeeds(mancala,index,numberOfSeedsInPit, pits, activePlayer)
        newGame!!.pits = pits

        checkForWinner(mancala, newGame, pits)

        mancala.message = gameMessage
        mancala.gameBoard = newGame

        return mancala
    }

    private fun checkForWinner(mancala: Mancala, newGame: GameBoard, pits: MutableList<Pit>) {
        var seedsForFirstPlayer = numberOfSeedsForAPlayer(newGame, mancala.firstPlayer)
        var seedsForSecondPlayer = numberOfSeedsForAPlayer(newGame, mancala.secondPlayer)
        if(seedsForFirstPlayer == 0 || seedsForSecondPlayer == 0)
            endGame(mancala, pits, seedsForFirstPlayer, seedsForSecondPlayer)
    }

    private fun numberOfSeedsForAPlayer(newGame: GameBoard, player: Players?): Int{
        var totalPits = newGame.pits
        var pits = ArrayList<Pit>()

        if(player!!.playerNumber == 1)
            (0 until 7 - 2).mapTo(pits) { totalPits!![it]}
        else
            (7 until 14 - 1).mapTo(pits) { totalPits!![it]}

        return pits.sumBy { it.totalSeeds }
    }

    private fun endGame(mancala: Mancala, pits: MutableList<Pit>, seedsForFirstPlayer: Int, seedsForSecondPlayer: Int) {
        var firstPlayer = pits[6]
        var secondPlayer = pits[13]
        var totalSeedsForFirstPlayer = firstPlayer.totalSeeds + seedsForFirstPlayer
        var totalSeedsForSecondPlayer = secondPlayer.totalSeeds + seedsForSecondPlayer
        if(totalSeedsForFirstPlayer > totalSeedsForSecondPlayer)
            gameMessage = "First Player is the winner!!!"
        else if (totalSeedsForSecondPlayer > totalSeedsForFirstPlayer)
            gameMessage = "Second Player is the winner!!!"
        else
            gameMessage = "IT'S A DRAW!!!"
        mancala.firstPlayer!!.isActive = false
        mancala.secondPlayer!!.isActive = false
    }

    private fun moveSeeds(mancala: Mancala, index: Int, numberOfSeedsInPit: Int, pits: MutableList<Pit>, activePlayer: Players?) {

        var numberOfSeedsInPitCopy = numberOfSeedsInPit  //Changing it to var coz later on it needs to be changed
        var indexCopy = index

        while (indexCopy < 14 && numberOfSeedsInPitCopy > 0){
            if(numberOfSeedsInPitCopy == 1){
                var isPlayer : Boolean = pits[indexCopy].pitId != activePlayer!!.playerNumber*7 - 1
                var isActivePlayersPit: Boolean = activePlayer.playerNumber == 1 && pits[indexCopy].pitId < 7 ||
                        activePlayer.playerNumber == 2 && pits[indexCopy].pitId >  7
                var isLastSeedInPit: Boolean = !isPlayer && pits[indexCopy].pitId == 0 && isActivePlayersPit
                if(isLastSeedInPit){
                    var oppPit = mancala.gameBoard!!.getPitNumber(14 - 2 - pits[indexCopy].pitId)
                    var seedsToAdd = oppPit.totalSeeds + 1
                    if(activePlayer!!.playerNumber == 1)
                        pits[6].totalSeeds = pits[6].totalSeeds + seedsToAdd
                    else
                        pits[6].totalSeeds = pits[13].totalSeeds + seedsToAdd
                    pits[oppPit.pitId].totalSeeds = 0
                    pits[pits[indexCopy].pitId].totalSeeds = 0
                    changePlayer(mancala, activePlayer)
                    break
                } else if ( pits[indexCopy].pitId != activePlayer!!.playerNumber*7 - 1 ){
                    changePlayer(mancala, activePlayer!!)
                }
            }

            var isPlayerActive: Boolean = pits[indexCopy].pitId == activePlayer!!.playerNumber*7 - 1
            if(!pits[indexCopy].isMancala || isPlayerActive){
                pits[indexCopy].totalSeeds = pits[indexCopy].totalSeeds + 1
                numberOfSeedsInPitCopy -= 1
            }

            if(indexCopy == 13 && numberOfSeedsInPitCopy > 0)
                indexCopy = 0
            else
                indexCopy += 1

        }
    }

    private fun changePlayer(mancala: Mancala, activePlayer: Players) {

        if(activePlayer.playerNumber == 1){
            mancala.firstPlayer!!.isActive = false
            mancala.secondPlayer!!.isActive = true
            gameMessage = "It's Player 2's turn!!"
        } else {
            mancala.secondPlayer!!.isActive = false
            mancala.firstPlayer!!.isActive = true
            gameMessage = "It's Player 1's turn!!"
        }
        mancala.firstPlayer!!.numberOfTurns = 1
        mancala.secondPlayer!!.numberOfTurns = 1
        mancala.message = gameMessage
    }


}


//TODO create enums for the board constants