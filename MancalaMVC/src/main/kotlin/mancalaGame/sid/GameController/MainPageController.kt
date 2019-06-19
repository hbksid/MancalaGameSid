package MancalaGame.GameController

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class MainPageController {


    @GetMapping("/")
    fun index(): String {
        return "index"
    }
}