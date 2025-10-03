package stages.sample

import com.tngtech.jgiven.Stage

open class SampleWhenStage : Stage<SampleWhenStage>() {
    open fun sample_when_func3() : SampleWhenStage{
        println("Sample when func3")
        return self()
    }

    open fun sample_when_func4() : SampleWhenStage{
        println("Sample when func4")
        return self()
    }
}