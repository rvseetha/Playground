package stages.sample

import com.tngtech.jgiven.Stage

open class SampleGivenStage : Stage<SampleGivenStage>() {
    open fun sample_given_func1() : SampleGivenStage{
        println("Sample Given func1")
        return self()
    }

    open fun sample_given_func2() : SampleGivenStage{
        println("Sample Given func2")
        return self()
    }
}