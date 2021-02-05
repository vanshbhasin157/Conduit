package io.realworld.api


import com.example.api.CondiutClient
import kotlinx.coroutines.runBlocking
import models.entities.UserCreds
import models.requests.SignUPRequest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*
import kotlin.random.Random

class ConduitClientTest {
    private val conduitClient = CondiutClient()
    @Test
    fun `GET articles`(){
        runBlocking {
            val articles =conduitClient.api.getArticles()
            assertNotNull(articles.body()?.articles)
        }


    }
    @Test
    fun `GET articles by author `(){
        runBlocking {
            val articles =conduitClient.api.getArticles(author = "444")
            assertNotNull(articles.body()?.articles)
        }


    }
    @Test
    fun `GET articles by tags `(){
        runBlocking {
            val articles =conduitClient.api.getArticles(tag = "dragons")
            assertNotNull(articles.body()?.articles)
        }


    }
    @Test
    fun `POST user --createUser`(){
        val userCreds = UserCreds(
            "testemail${Random.nextInt(99,9999)}@test.com",
            "pass${Random.nextInt(9999,9999999)}",
            "_rand_user_${Random.nextInt(99,999)}"
        )
        runBlocking {
            val resp = conduitClient.api.registerUser(SignUPRequest(userCreds))
            assertEquals(userCreds.username,resp.body()?.user?.username)
        }
    }

}