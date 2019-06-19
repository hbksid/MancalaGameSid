package MancalaGame.MancalaModel

import java.util.stream.IntStream
import java.util.stream.IntStream.range

class GameBoard {

    var pits: MutableList<Pit>? = null

    init {
        pits = mutableListOf()

        //total pits on the board = 14
        range(0,14).forEach { i ->

            var pit = Pit()
            pits?.add(pit)

            pits!![i].pitId = i

            //Total pits for a player = 7
            pits!![i].isMancala = (i+1)% 7 == 0

            //Max number of seeds in a pit = 6
            if(!pits!![i].isMancala)
                pits!![i].totalSeeds = 6
            else
                pits!![i].totalSeeds = 0
        }
    }

    fun getPitNumber(pitNumber: Int): Pit {
        return this.pits!![pitNumber]
    }
}