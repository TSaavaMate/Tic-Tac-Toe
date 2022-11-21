package com.example.tiktaktoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
class Secactivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var num1: Button
    private lateinit var num2: Button
    private lateinit var num3: Button
    private lateinit var num4: Button
    private lateinit var num5: Button
    private lateinit var num6: Button
    private lateinit var num7: Button
    private lateinit var num8: Button
    private lateinit var num9: Button
    private lateinit var res: Button
    private lateinit var tryagain: Button
    private lateinit var p1name: TextView
    private lateinit var p2name: TextView
    private lateinit var p1point: TextView
    private lateinit var p2point: TextView
    private var activeplayer =1
    private  var score: MutableList<Int> = mutableListOf(0,0)
    private var firstPlayer = ArrayList<Int>()
    private var secondPlayer = ArrayList<Int>()
    private var wincombination: List<List<Int>> = listOf(
        listOf(1, 5, 9),
        listOf(3, 5, 7),
        listOf(1, 4, 7),
        listOf(1, 2, 3),
        listOf(2, 5, 8),
        listOf(3, 6, 9),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secactivity)
        p1name=findViewById(R.id.player1name)
        p2name=findViewById(R.id.player2name)
        val p1Name=intent.extras?.getString("NAME1")
        val p2Name=intent.extras?.getString("NAME2")
        p1name.text=p1Name.toString()
        p2name.text=p2Name.toString()
        init()
    }
    private fun init(){
        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        num3 = findViewById(R.id.num3)
        num4= findViewById(R.id.num4)
        num5= findViewById(R.id.num5)
        num6= findViewById(R.id.num6)
        num7= findViewById(R.id.num7)
        num8= findViewById(R.id.num8)
        num9 = findViewById(R.id.num9)
        p1point=findViewById(R.id.player1point)
        p2point=findViewById(R.id.player2point)
        res=findViewById(R.id.reset)
        tryagain=findViewById(R.id.tryagain)

        num1.setOnClickListener(this)
        num2.setOnClickListener(this)
        num3.setOnClickListener(this)
        num4.setOnClickListener(this)
        num5.setOnClickListener(this)
        num6.setOnClickListener(this)
        num7.setOnClickListener(this)
        num8.setOnClickListener(this)
        num9.setOnClickListener(this)
        res.setOnClickListener(this)
        tryagain.setOnClickListener(this)


    }
    override fun onClick(ClickedView: View?) {
        if (ClickedView is Button){
            var buttonNumber=0
            when(ClickedView.id){
                R.id.num1 ->buttonNumber=1
                R.id.num2 ->buttonNumber=2
                R.id.num3 ->buttonNumber=3
                R.id.num4 ->buttonNumber=4
                R.id.num5 ->buttonNumber=5
                R.id.num6 ->buttonNumber=6
                R.id.num7 ->buttonNumber=7
                R.id.num8 ->buttonNumber=8
                R.id.num9 ->buttonNumber=9
                R.id.tryagain -> cleartable()
                R.id.reset -> clearscore()
            }
            if(buttonNumber!=0){
                playgame(buttonNumber,ClickedView)
            }
        }

    }

    private fun clearscore() {
        score.clear()
        p1point.text=""
        p2point.text=""
        tryagain.isEnabled=true
    }

    private fun playgame(buttonNumber: Int, clickedView: Button) {
        if (activeplayer==1){
            clickedView.text="X"
            clickedView.setBackgroundColor(Color.YELLOW)
            activeplayer=2
            firstPlayer.add(buttonNumber)
        }else if(activeplayer==2){
            clickedView.text="0"
            clickedView.setBackgroundColor(Color.RED)
            activeplayer=1
            secondPlayer.add(buttonNumber)
        }
        clickedView.isEnabled=false
        check()
    }
    private fun check() {
        var winner=0
        for (i in 0..7){
            if (firstPlayer.size>=3){
                if(wincombination[i].contains(firstPlayer[0]) &&
                    wincombination[i].contains(firstPlayer[1]) &&
                    wincombination[i].contains(firstPlayer[2])){
                    winner=1
                    score[0]++
                    p1point.text=score[0].toString()
                    if(score[0]<3){
                        Toast.makeText(this, String.format("%s won this turn", p1name.text.toString()) ,
                            Toast.LENGTH_LONG).show()
                    }
                    buttonsdisable()
                    if(score[0]==3){
                        Toast.makeText(this, String.format("%s is winner", p1name.text.toString()) ,
                            Toast.LENGTH_LONG).show()
                        tryagain.isEnabled=false
                        score[1]=0
                        score[0]=0
                    }
                }
            }
        }
        if (secondPlayer.size>=3){
            winner=2
            for (i in 0..7){
                if(wincombination[i].contains(secondPlayer[0]) &&
                    wincombination[i].contains(secondPlayer[1]) &&
                    wincombination[i].contains(secondPlayer[2])){
                    score[1]++
                    p2point.text=score[1].toString()
                    if(score[1]<3){
                        Toast.makeText(this, String.format("%s won this turn", p2name.text.toString()) ,
                            Toast.LENGTH_LONG).show()
                    }
                    buttonsdisable()
                    if (score[1]==3){
                        Toast.makeText(this, String.format("%s is winner", p2name.text.toString()) ,
                            Toast.LENGTH_LONG).show()
                        tryagain.isEnabled=false
                        score[1]=0
                        score[0]=0
                    }
                }
            }
        }
        if(firstPlayer.size+secondPlayer.size==9 && winner==0){
            Toast.makeText(this, "it's draw ", Toast.LENGTH_LONG).show()
        }

    }


    private fun cleartable() {
        firstPlayer.clear()
        secondPlayer.clear()
        num1.setBackgroundColor(Color.GREEN)
        num1.text=""
        num2.setBackgroundColor(Color.GREEN)
        num2.text=""
        num3.setBackgroundColor(Color.GREEN)
        num3.text=""
        num4.setBackgroundColor(Color.GREEN)
        num4.text=""
        num5.setBackgroundColor(Color.GREEN)
        num5.text=""
        num6.setBackgroundColor(Color.GREEN)
        num6.text=""
        num7.setBackgroundColor(Color.GREEN)
        num7.text=""
        num8.setBackgroundColor(Color.GREEN)
        num8.text=""
        num9.setBackgroundColor(Color.GREEN)
        num9.text=""
        buttonsenable()

    }

    private fun buttonsdisable() {
        num1.isEnabled=false
        num2.isEnabled=false
        num3.isEnabled=false
        num4.isEnabled=false
        num5.isEnabled=false
        num6.isEnabled=false
        num7.isEnabled=false
        num8.isEnabled=false
        num9.isEnabled=false
    }

    private fun buttonsenable() {
        num1.isEnabled=true
        num2.isEnabled=true
        num3.isEnabled=true
        num4.isEnabled=true
        num5.isEnabled=true
        num6.isEnabled=true
        num7.isEnabled=true
        num8.isEnabled=true
        num9.isEnabled=true
    }


}






