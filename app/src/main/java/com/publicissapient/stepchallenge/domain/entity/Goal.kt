package com.publicissapient.stepchallenge.domain.entity

import com.publicissapient.stepchallenge.domain.util.Utilities
import java.time.Month

data class Goal(var id: String = Utilities.generateId("Goal"),
                var stepCount: Int,
                val goalMonth: Month,
                val isPersonal: Boolean = false,
                var status: GoalStatus = GoalStatus.TODO) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Goal) return false
        return stepCount == other.stepCount && isPersonal == other.isPersonal && goalMonth == other.goalMonth
    }

    override fun hashCode(): Int {
        var result = stepCount
        result = 31 * result + isPersonal.hashCode() + goalMonth.hashCode()
        return result
    }
}

enum class GoalStatus(val value: String) {
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    ABANDONED("Abandoned")
}
