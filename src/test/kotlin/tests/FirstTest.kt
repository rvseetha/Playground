package tests

import org.testng.ITestResult
import org.testng.annotations.*

class FirstTest {

    @BeforeTest
    fun setup() {
        println("Before Test Setup for FirstTest")
    }

    @BeforeClass
    fun beforeClass() {
        println("Before Class Setup for FirstTest")
    }

    @BeforeMethod
    fun beforeMethod(result: ITestResult) {
        println("Before Method Setup for FirstTest for :${result.method.methodName}")
    }

    @Test
    fun samplePassTest() {
        println("Executing Sample pass Test in FirstTest")
        assert(true)
    }

    @Test
    fun sampleFailTest() {
        println("Executing Sample fail Test in FirstTest")
        assert(false)
    }

    @AfterMethod
    fun afterMethod(result: ITestResult) {
        println("After Method Teardown for FirstTest for :${result.method.methodName}")
    }

    @AfterClass
    fun afterClass() {
        println("After Class Teardown for FirstTest")
    }

    @AfterTest
    fun teardown() {
        println("After Test Teardown for FirstTest")
    }

}