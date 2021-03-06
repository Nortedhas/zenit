package com.ageone.zenit.Application.Coordinator.Router

import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.Stack.*
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.External.Extensions.FlowCoordinator.clearFlowStack
import timber.log.Timber

fun FlowCoordinator.createStackFlows(startFlow: Int) {
    clearFlowStack()

    // MARK: in order like in navigation


    Timber.i("Bottom create stack flows")

    runFlowNews()
    runFlowMessage()
    runFlowEvent()
    runFlowStatus()
    runFlowProfile()

    Stack.flows[startFlow].start()
}