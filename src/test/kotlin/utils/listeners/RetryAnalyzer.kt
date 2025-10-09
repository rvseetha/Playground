package utils.listeners

import org.testng.IRetryAnalyzer
import org.testng.ITestResult

class RetryAnalyzer : IRetryAnalyzer {
    private var retryCount = 0
    private val maxRetryCount = 3

    override fun retry(result: ITestResult?): Boolean {
        if (retryCount < maxRetryCount) {
            retryCount++
            println("Retrying test ${result?.name}, attempt $retryCount")
            return true
        }
        return false
    }

    fun getRetryCount(): Int = retryCount
    fun getMaxRetryCount(): Int = maxRetryCount
}