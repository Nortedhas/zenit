package com.ageone.zenit.Modules.Auth

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.Module.BaseModule
import com.ageone.zenit.External.Base.RecyclerView.BaseAdapter
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.InitModuleUI
import com.ageone.zenit.Modules.Auth.rows.TextInputViewHolder
import com.ageone.zenit.Modules.Auth.rows.initialize
import com.ageone.zenit.R
import yummypets.com.stevia.*

class AuthView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = AuthViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val imageViewLogo by lazy {
        val imageView = BaseImageView()
//        imageView.cornerRadius = .dp
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
    // 	imageView.elevation = 5F.dp
        imageView
    }

    val imageViewBackground by lazy {
        val imageView = BaseImageView()
//        imageView.cornerRadius = .dp
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
    // 	imageView.elevation = 5F.dp
        imageView
    }

    init {
//        viewModel.loadRealmData()

        backgroundFullscreen.setBackgroundColor(Color.WHITE)

        toolbar.title = ""

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


        imageViewBackground.setBackgroundResource(R.drawable.back_lion)
        imageViewLogo.setBackgroundResource(R.drawable.zenit_logo)
        renderUIO()
        bindUI()

        onDeInit = {
            unBind()
        }

    }

    fun bindUI() {
        /*compositeDisposable.addAll(
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val TextInputType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> TextInputType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                TextInputType -> {
                    TextInputViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is TextInputViewHolder -> {
                    holder.initialize()
                }

            }

        }

    }

}

fun AuthView.renderUIO() {

    backgroundFullscreen.subviews(
        imageViewBackground,
        imageViewLogo
    )

    imageViewBackground
        .width(utils.tools.getActualSizeFromDes(505))
        .height(utils.tools.getActualSizeFromDes(400))
        .constrainTopToTopOf(backgroundFullscreen)
        .constrainLeftToLeftOf(backgroundFullscreen, utils.tools.getActualSizeFromDes(40).toInt())

    imageViewLogo
        .width(utils.tools.getActualSizeFromDes(208))
        .height(utils.tools.getActualSizeFromDes(78))
        .constrainTopToTopOf(backgroundFullscreen, 76)
        .constrainLeftToLeftOf(backgroundFullscreen)
        .constrainRightToRightOf(backgroundFullscreen)

    renderBodyTable()
}

