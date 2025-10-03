package stages.sample

import com.tngtech.jgiven.Stage

open class SampleThenStage : Stage<SampleThenStage>() {
    open fun sample_then_func5() : SampleThenStage{
        println("Sample then func5")
        return self()
    }

    open fun sample_given_func6() : SampleThenStage{
        println("Sample then func6")
        return self()
    }
}