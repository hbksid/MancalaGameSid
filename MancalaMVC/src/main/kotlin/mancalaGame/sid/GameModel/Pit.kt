package MancalaGame.MancalaModel

class Pit {

    var totalSeeds: Int
    var pitId: Int = 0
    var pitEmpty: Boolean = false
    var isMancala: Boolean = false

    init {
        this.totalSeeds = 6 //total seeds allowed in a pit
    }
}