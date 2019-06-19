package MancalaGame.GameController

import MancalaGame.MancalaModel.Mancala
import MancalaGame.MancalaModel.Players
import MancalaGame.MancalaService.MancalaService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

@Controller
class MancalaController {

    var mancalaService: MancalaService? = null
    lateinit var mancala: Mancala
    var ERROR = "/error"
    var MANCALA = "mancalaGame"
    var NUMBER_OF_PITS = "numberOfPits"
    var TOTAL_ROWS = "totalRows"
    var MESSAGE = "meesage"


    fun submitGame(@Valid @ModelAttribute("players") players: Players, modelMap: ModelMap, result: BindingResult): String {
        if(result.hasErrors())
            return ERROR

        mancala = Mancala()

        modelMap.addAttribute(NUMBER_OF_PITS, 14)
        modelMap.addAttribute(TOTAL_ROWS, 7)
        modelMap.addAttribute(MANCALA, mancala)

        return MANCALA
    }

    fun move(@RequestParam pitNumber: String, model: Model): String {
        var pitNumberClicked = Integer.valueOf(pitNumber).toInt()
        mancala = mancalaService!!.beginGame(pitNumberClicked, mancala)
        model.addAttribute(MANCALA, mancala)
        model.addAttribute(TOTAL_ROWS, 7)
        model.addAttribute(NUMBER_OF_PITS, 14)
        model.addAttribute(MESSAGE, mancala.message!!)

        return MANCALA
    }

}