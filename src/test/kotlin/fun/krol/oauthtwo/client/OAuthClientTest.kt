package `fun`.krol.oauthtwo.client;

import `fun`.krol.oauthtwo.AccessToken
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import `fun`.krol.oauthtwo.OAuthBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.equalTo
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.junit.jupiter.api.AfterAll
import java.util.*
import kotlin.test.assertEquals


class OAuthClientTest {

    companion object {

        val port = 8088
        @JvmStatic val wireMockServer = WireMockServer(port)


        @BeforeAll
        @JvmStatic
        internal fun setup() {
            wireMockServer.start()
        }

        @AfterAll
        @JvmStatic
        internal fun end() {
            wireMockServer.stop()
        }

    }

    @BeforeEach
    internal fun reset() {
        wireMockServer.resetAll()
    }

    @Test
    fun testAuthorizationRequest() {

        val clientId = UUID.randomUUID().toString()
        val client = OAuthBuilder()
            .setAutorisationbaseHost("http://localhost:$port/oauth")
            .setRedirectUrl("http://fun.krol/oauth")
            .setTokenAccessEndpont("/token")
            .setClientId(clientId)
            .buildClient()


        val oauthUrl = client.identifyСlient(setOf("READ", "UPDATE")).toString()
        assertEquals(
            "http://localhost:$port/oauthresponse_type=code&client_id=$clientId&redirect_uri=http://fun.krol/oauth&scops=READ UPDATE",
            oauthUrl
        )
    }


    @Test
    fun testTakeAccessTokens() {
        val clientId = UUID.randomUUID().toString()
        val accessToken = UUID.randomUUID().toString()
        val client = OAuthBuilder()
            .setAutorisationbaseHost("http://localhost:$port/oauth")
            .setTokenAccessEndpont("/token")
            .setRedirectUrl("http://fun.krol/oauth")
            .setClientId(clientId)
            .buildClient()

        val expectedToken = AccessToken(
            "v1vQXiU5gN6DJzWdnRK6YYnoDe3b5pjd",
            "bearer",
            7200,
            "7T2g8xeogsgpG3whezFQ2ZKckabiwCgS",
            setOf()
        );

        wireMockServer.stubFor(
            WireMock.post("/oauth/token")
                .willReturn(
                    WireMock.ok()
                        .withHeader("Content-Type", "text/plain")
                        .withBody(
                            ObjectMapper().writeValueAsString(
                                AccessToken(
                                    "v1vQXiU5gN6DJzWdnRK6YYnoDe3b5pjd",
                                    "bearer",
                                    7200,
                                    "7T2g8xeogsgpG3whezFQ2ZKckabiwCgS",
                                    null
                                )
                            )
                        )
                )
        );


        val token = client.takeAccessTokens(accessToken)


        wireMockServer.verify(
            WireMock.postRequestedFor(WireMock.urlEqualTo("/oauth/token"))
                .withRequestBody(equalTo("grant_type=authorization_code&code=$accessToken&client_id=$clientId&redirect_uri=http://fun.krol/oauth"))
        )

    }

}