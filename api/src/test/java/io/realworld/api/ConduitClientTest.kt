package io.realworld.api


import com.example.api.CondiutClient
import org.junit.Assert.assertNotNull
import org.junit.Test

class ConduitClientTest {
    private val conduitClient = CondiutClient()
    @Test
    fun `GET articles`(){
        val articles =conduitClient.api.getArticles().execute()
        assertNotNull(articles.body()?.articles)


    }

}