package utils.listeners

import org.testng.ITestResult
import org.testng.TestListenerAdapter

class JGivenRetryAwareListener : TestListenerAdapter() {
    override fun onTestStart(result: ITestResult) {
        val retryAnalyzer = result.method.getRetryAnalyzer(result) as? RetryAnalyzer
        if (retryAnalyzer != null && retryAnalyzer.getRetryCount() < retryAnalyzer.getMaxRetryCount()) {
            // Disable JGiven reporting for this attempt
            result.testContext.setAttribute("jgiven.ignore", true)
        } else {
            result.testContext.removeAttribute("jgiven.ignore")
        }
    }
}
