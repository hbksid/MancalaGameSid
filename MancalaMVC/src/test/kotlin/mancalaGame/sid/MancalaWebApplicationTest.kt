package mancalaGame.sid

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
class MancalaWebApplicationTest {

    @Autowired
    val restTemplate: TestRestTemplate? = null

    @Test
    @Throws(Exception::class)
    fun testJspWithEl() {
        val entity = this.restTemplate!!.getForEntity("/", String::class.java)
        MatcherAssert.assertThat(entity.statusCode, `is`(equalTo(HttpStatus.OK)))
    }
}