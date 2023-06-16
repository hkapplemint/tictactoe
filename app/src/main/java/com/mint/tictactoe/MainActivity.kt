package com.mint.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv00: ImageView = findViewById(R.id.iv00)
        iv00.tag = "iv00"
        val iv01: ImageView = findViewById(R.id.iv01)
        iv01.tag = "iv01"
        val iv02: ImageView = findViewById(R.id.iv02)
        iv02.tag = "iv02"
        val iv10: ImageView = findViewById(R.id.iv10)
        iv10.tag = "iv10"
        val iv11: ImageView = findViewById(R.id.iv11)
        iv11.tag = "iv11"
        val iv12: ImageView = findViewById(R.id.iv12)
        iv12.tag = "iv12"
        val iv20: ImageView = findViewById(R.id.iv20)
        iv20.tag = "iv20"
        val iv21: ImageView = findViewById(R.id.iv21)
        iv21.tag = "iv21"
        val iv22: ImageView = findViewById(R.id.iv22)
        iv22.tag = "iv22"

        val btnRestart:Button = findViewById(R.id.btnRestart)

        val ivList = listOf<ImageView>(iv00, iv01, iv02, iv10, iv11, iv12, iv20, iv21, iv22)

        //setting up an empty board
        for (imageView in ivList) {
            imageView.setImageResource(R.drawable.empty)
        }

        btnRestart.setOnClickListener {
            val tv:TextView=findViewById(R.id.tv)
            tv.text = "Hi"
            for (imageView in ivList) {
                imageView.setImageResource(R.drawable.empty)
            }
            boardArray = arrayOf(
                arrayOf("Empty", "Empty", "Empty"),
                arrayOf("Empty", "Empty", "Empty"),
                arrayOf("Empty", "Empty", "Empty")
            )
            playerTurnIsCircle = true
            gameEnded = false

        }

        for (imageView in ivList){
            imageView.setOnClickListener{
                val imageViewY = imageView.tag.toString().substring(2,3).toInt()
                val imageViewX = imageView.tag.toString().substring(3).toInt()

                if(playerTurnIsCircle && !gameEnded){
                    if(boardArray[imageViewY][imageViewX] == "Empty"){
                        imageView.setImageResource(R.drawable.circle)
                        boardArray[imageViewY][imageViewX] = "Circle"
                        checkWin()
                        checkDraw()
                        playerTurnIsCircle = false
                    }
                } else if (!playerTurnIsCircle && !gameEnded) {
                    if(boardArray[imageViewY][imageViewX] == "Empty"){
                        imageView.setImageResource(R.drawable.cross)
                        boardArray[imageViewY][imageViewX] = "Cross"
                        checkWin()
                        checkDraw()
                        playerTurnIsCircle = true
                    }
                }
            }
        }
    }

    var playerTurnIsCircle = true
    var gameEnded = false

    var boardArray = arrayOf(
        arrayOf("Empty", "Empty", "Empty"),
        arrayOf("Empty", "Empty", "Empty"),
        arrayOf("Empty", "Empty", "Empty")
    )
    val boardArrayCopy = arrayOf(
        arrayOf("Empty", "Empty", "Empty"),
        arrayOf("Empty", "Empty", "Empty"),
        arrayOf("Empty", "Empty", "Empty")
    )


    fun checkWin() {
        val tv: TextView = findViewById(R.id.tv)
        //Check horizontal
        for (i in 0..2) {
            if (boardArray[i].toSet().size == 1) {
                if ("Circle" in boardArray[i]) {
                    tv.text = "Circle Wins"
                    gameEnded = true
                    break
                } else if ("Cross" in boardArray[i]) {
                    tv.text = "Cross Wins"
                    gameEnded = true
                    break
                }
            }
        }

        //Check vertical
        val firstColumn = mutableListOf<String>()
        val secondColumn = mutableListOf<String>()
        val thirdColumn = mutableListOf<String>()
        for (i in 0..2) {
            firstColumn.add(boardArray[i][0])
        }
        for (i in 0..2) {
            secondColumn.add(boardArray[i][1])
        }
        for (i in 0..2) {
            thirdColumn.add(boardArray[i][2])
        }
        val columnList = listOf(firstColumn, secondColumn, thirdColumn)
        for (column in columnList){
            if(column.toSet().size == 1){
                if("Circle" in column){
                    tv.text = "Circle Wins"
                    gameEnded = true
                    break
                } else if ("Cross" in column){
                    tv.text = "Cross Wins"
                    gameEnded = true
                    break
                }
            }
        }

        //Check Diagonals
        val firstDiagonal = mutableListOf<String>()
        for (i in 0..2){
            for (j in 0..2){
                if (i==j){
                    firstDiagonal.add(boardArray[i][j])
                }
            }
        }
        val secondDiagonal = mutableListOf<String>()
        for (i in 0..2){
            for (j in 0..2){
                if(i+j == 2){
                    secondDiagonal.add(boardArray[i][j])
                }
            }
        }
        val diagonalList = listOf(firstDiagonal, secondDiagonal)
        for (diagonal in diagonalList){
            if(diagonal.toSet().size == 1){
                if("Circle" in diagonal){
                    tv.text = "Circle Wins"
                    gameEnded = true
                    break
                } else if ("Cross" in diagonal){
                    tv.text = "Cross Wins"
                    gameEnded = true
                    break
                }
            }
        }
    }

    fun checkDraw(){
        val tv: TextView = findViewById(R.id.tv)
        var emptyFound = 0
        for (row in boardArray){
            if("Empty" in row){
                emptyFound += 1
            }
        }
        if(emptyFound==0){
            tv.text = "Draw"
            gameEnded = true
        }
    }
}