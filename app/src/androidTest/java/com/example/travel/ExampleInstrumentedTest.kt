package com.example.travel

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.travel.view.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TravelAppNavTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testLoginToRegister_Navigation() {

        composeRule.waitForIdle()
        composeRule.onNodeWithText("Register", ignoreCase = true).performClick()
        composeRule.onNodeWithText("Create Account", ignoreCase = true).assertIsDisplayed()
    }

    @Test
    fun testLoginToForgotPassword_Navigation() {
        composeRule.waitForIdle()

        // "Forgot Password"
        composeRule.onNodeWithText("Forgot Password", ignoreCase = true).performClick()

        // Verify Forgot Password screen
        composeRule.onNodeWithText("Reset Password", ignoreCase = true).assertIsDisplayed()
    }

    @Test
    fun testLoginInput_And_Click() {
        composeRule.waitForIdle()


        composeRule.onNodeWithText("Email").performTextInput("test@travel.com")
        composeRule.onNodeWithText("Password").performTextInput("password123")

        // Login button
        composeRule.onNodeWithText("Login", ignoreCase = true).performClick()

        composeRule.waitForIdle()
    }
}