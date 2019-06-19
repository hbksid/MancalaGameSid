package mancalaGame.sid

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import java.lang.Exception

@SpringBootApplication
open class MancalaWebApplication : SpringBootServletInitializer() {

    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(MancalaWebApplication::class.java, *args)
        }
    }

    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        return builder!!.sources(MancalaWebApplication::class.java)
    }
}