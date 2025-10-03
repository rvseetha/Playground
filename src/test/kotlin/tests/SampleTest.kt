package tests

import com.tngtech.jgiven.testng.ScenarioTest
import org.testng.annotations.Test
import stages.sample.SampleGivenStage
import stages.sample.SampleThenStage
import stages.sample.SampleWhenStage

open class SampleTest : ScenarioTest<SampleGivenStage, SampleWhenStage, SampleThenStage>() {

    @Test
    fun sample_test() {
        given()
            .sample_given_func1()
            .sample_given_func2()
        `when`()
            .sample_when_func3()
            .sample_when_func4()
        then()
            .sample_then_func5()
            .sample_given_func6()
    }

}