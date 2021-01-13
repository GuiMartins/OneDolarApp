package martins.gui.onedollar

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var frontAnim:AnimatorSet
    lateinit var backAnim:AnimatorSet
    var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardFront: TextView = findViewById<TextView>(R.id.card_front)
        val cardBack: TextView = findViewById<TextView>(R.id.card_back)
        val flipBtn: Button = findViewById<Button>(R.id.flip_btn)

        val scale = applicationContext.resources.displayMetrics.density
        cardFront.cameraDistance = 8000 * scale
        cardBack.cameraDistance = 8000 * scale

        frontAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        backAnim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet

        flipBtn.setOnClickListener {
            if (isFront) {
                frontAnim.setTarget(cardFront)
                backAnim.setTarget(cardBack)
                frontAnim.start()
                backAnim.start()
                isFront = false
            } else {
                frontAnim.setTarget(cardBack)
                backAnim.setTarget(cardFront)
                backAnim.start()
                frontAnim.start()
                isFront = true
            }
        }
    }
}