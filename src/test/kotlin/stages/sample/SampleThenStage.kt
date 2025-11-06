package stages.sample

import com.tngtech.jgiven.Stage

open class SampleThenStage : Stage<SampleThenStage>() {
    open fun sample_then_func5() : SampleThenStage{
        println("Sample then func5")
        assert(true)
        return self()
    }

    open fun sample_then_func6() : SampleThenStage{
        println("Sample then func6")
        assert(false)
        return self()
    }

    open fun sample_then_func7() : SampleThenStage{
        println("Sample then func6")
        assert(true)
        return self()
    }
}