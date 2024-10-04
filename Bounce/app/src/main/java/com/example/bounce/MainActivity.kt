package com.example.bounce

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.AnimationVector
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bounce.ui.theme.BounceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)

        val button = findViewById<Button>(R.id.button)
        val star = findViewById<ImageView>(R.id.star)
        button.setOnClickListener(View.OnClickListener {
            star.clearAnimation()
            val transAnim = TranslateAnimation(
                0f,0f,0f,(displayHeight/1.5).toFloat()
            )
            transAnim.startOffset = 500
            transAnim.duration = 3500
            transAnim.fillAfter = true
            transAnim.interpolator = BounceInterpolator()
            transAnim.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    star.clearAnimation()
                    val left = star.left
                    val top = star.top
                    val right = star.right
                    val bottom = star.bottom
                    star.layout(left,top,right,bottom)
                }

                override fun onAnimationRepeat(animation: Animation?) {}

            })
            star.startAnimation(transAnim)

            fun onClick(view: View){
                button.startAnimation(animation)
            }
        })
    }
    private val displayHeight:Int
        get()= this.resources.displayMetrics.heightPixels
}

