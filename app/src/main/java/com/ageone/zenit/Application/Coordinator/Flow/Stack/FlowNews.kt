package com.ageone.zenit.Application.Coordinator.Flow.Stack

import android.graphics.Color
import androidx.core.view.children
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.zenit.Application.Coordinator.Flow.FlowCoordinator.MainUIObject.flowStorage
import com.ageone.zenit.Application.Coordinator.Flow.Regular.runFlowFinalQuiz
import com.ageone.zenit.Application.Coordinator.Flow.Regular.runFlowQuiz
import com.ageone.zenit.Application.Coordinator.Router.DataFlow
import com.ageone.zenit.Application.Coordinator.Router.TabBar.Stack
import com.ageone.zenit.Application.coordinator
import com.ageone.zenit.Application.currentActivity
import com.ageone.zenit.External.Base.Flow.BaseFlow
import com.ageone.zenit.External.Base.Module.ModuleInterface
import com.ageone.zenit.External.Extensions.Activity.setLightStatusBar
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Answer.AnswerModel
import com.ageone.zenit.Modules.Answer.AnswerView
import com.ageone.zenit.Modules.Answer.AnswerViewModel
import com.ageone.zenit.Modules.Item.ItemModel
import com.ageone.zenit.Modules.Item.ItemView
import com.ageone.zenit.Modules.Item.ItemViewModel
import com.ageone.zenit.Modules.News.NewsView
import com.ageone.zenit.Modules.News.NewsModel
import com.ageone.zenit.Modules.News.NewsViewModel
import com.ageone.zenit.Modules.Quiz.QuizModel
import com.ageone.zenit.Modules.Quiz.QuizView
import com.ageone.zenit.Modules.Quiz.QuizViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowNews() {

    var flow: FlowNews? = FlowNews()

    flow?.let { flow ->
        flowStorage.addFlow(flow.viewFlipperModule)
        flowStorage.displayFlow(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(flowStorage.size - 1)

        currentActivity?.setLightStatusBar(Color.TRANSPARENT, Color.GRAY)

        flow.isLightStatusBar = true
        flow.colorStatusBar = Color.TRANSPARENT
        flow.colorStatusBarDark = Color.GRAY

        flow.colorNavigationBar = Color.BLACK

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        flow?.viewFlipperModule?.children?.forEachIndexed { index, view ->
            if (view is ModuleInterface) {
                Timber.i("Delete module in flow finish")
                view.onDeInit?.invoke()
            }
        }

        flowStorage.deleteFlow(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null

    }

//    flow?.start()

}

class FlowNews(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowNewsModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleNews()
    }

    inner class FlowNewsModels {
        val modelNews = NewsModel()
        val modelItem = ItemModel()
    }

    fun runModuleNews() {
        val module = NewsView()

        module.viewModel.initialize(models.modelNews) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (NewsViewModel.EventType.valueOf(event)) {
                NewsViewModel.EventType.OnContinuePressed -> {
                    runModuleItem()
                }
                NewsViewModel.EventType.OnQuizPressed -> {
                    coordinator.runFlowQuiz(this)
                }
                NewsViewModel.EventType.OnFinalQuizPressed -> {
                    coordinator.runFlowFinalQuiz(this)
                }
            }
        }
        push(module)
    }

    fun runModuleItem() {
        val module = ItemView(
            InitModuleUI(
                isBackPressed = true
        ))

        module.viewModel.initialize(models.modelItem) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ItemViewModel.EventType.valueOf(event)) {

            }
        }

        push(module)
    }

}