package com.zenk.timerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.zenk.timerdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var countDownTimer : CountDownTimer? = null

    //timer duration in milliseconds
    private var timerDuration : Long = 60000


    //offset for pause. timerDuration - time left
    private var pauseOffset : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        //Set text for timer on UI.
        binding?.timerTv?.text = "${(timerDuration / 1000).toString()}"

        //Set on click listeners for buttons.
        binding?.startBtn?.setOnClickListener{
            startClicked(pauseOffset)
        }

        binding?.resetBtn?.setOnClickListener{
            resetClicked()
        }

        binding?.pauseBtn?.setOnClickListener {
            pauseClicked()
        }
    }

    private fun startClicked(pauseOffsetL : Long)
    {
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL, 1000) {
            override fun onTick(millisUntilFinished : Long)
            {
                pauseOffset = timerDuration - millisUntilFinished
                binding?.timerTv?.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish()
            {
                Toast.makeText(this@MainActivity,
                "Timer is finished",
                Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun resetClicked()
    {
        //Check if timer exists and has been started.
        if(countDownTimer != null)
        {
            countDownTimer!!.cancel()
            binding?.timerTv?.text = "${(timerDuration / 1000).toString()}"

            //Kill existing timer
            countDownTimer = null

            //Set pause offset back to zero.
            pauseOffset = 0
        }
    }
    private fun pauseClicked()
    {

        //Has the timer been created and started?
        if(countDownTimer != null)
        {
            countDownTimer!!.cancel()

        }


    }
}