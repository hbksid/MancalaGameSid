package MancalaGame.GameController

import MancalaGame.MancalaModel.Players
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView


@Controller
class PlayerController {

    @RequestMapping(value = ["/player.do"], method = [RequestMethod.GET])
    fun showForm(): ModelAndView {
        return ModelAndView("player","players", Players())
    }
}