package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // val 再代入不可能, var 再代入可能
    var nStr : String = ""
    val nList = ArrayList<Double>() // 数式に含まれる数を保持する配列
    val oList = ArrayList<Char>() // 数式に含まれるオペレーション(四則演算)を保持する配列

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num0.setOnClickListener {
            formula.text = "${formula.text}0"
            nStr += "0"
        }
        num1.setOnClickListener {
            formula.text = "${formula.text}1"
            nStr += "1"
        }
        num2.setOnClickListener {
            formula.text = "${formula.text}2"
            nStr += "2"
        }
        num3.setOnClickListener {
            formula.text = "${formula.text}3"
            nStr += "3"
        }
        num4.setOnClickListener {
            formula.text = "${formula.text}4"
            nStr += "4"
        }
        num5.setOnClickListener {
            formula.text = "${formula.text}5"
            nStr += "5"
        }
        num6.setOnClickListener {
            formula.text = "${formula.text}6"
            nStr += "6"
        }
        num7.setOnClickListener {
            formula.text = "${formula.text}7"
            nStr += "7"
        }
        num8.setOnClickListener {
            formula.text = "${formula.text}8"
            nStr += "8"
        }
        num9.setOnClickListener {
            formula.text = "${formula.text}9"
            nStr += "9"
        }

        //小数点
        point.setOnClickListener {
            formula.text = "${formula.text}."
            nStr += "."
        }

        add.setOnClickListener {
            formula.text = "${formula.text}+"
            addList(nStr, '+')
            nStr = ""
        }

        subtract.setOnClickListener {
            formula.text = "${formula.text}-"
            addList(nStr, '-')
            nStr = ""
        }

        multiply.setOnClickListener {
            formula.text = "${formula.text}*"
            addList(nStr, '*')
            nStr = ""
        }

        devide.setOnClickListener {
            formula.text = "${formula.text}/"
            addList(nStr, '/')
            nStr = ""
        }

        // 最後の一文字を削除
        delete.setOnClickListener {
            var formulaStr = formula.text.toString()
            if (!formulaStr.isEmpty()) {
                formula.text = formulaStr.subSequence(0, formulaStr.lastIndex)
            }
            if (!nStr.isEmpty()) {
                nStr = nStr.substring(0, nStr.lastIndex)
            }
        }

        percent.setOnClickListener {
            formula.text = "${formula.text}%"
        }
        sign.setOnClickListener {

        }

        clear.setOnClickListener {
            formula.text = ""
            nStr = ""
            nList.clear()
            oList.clear()
        }

        equal.setOnClickListener {
            //if (nList.size != oList.size + 1)
            //    return

            formula.text = "${formula.text}="
            addList(nStr, '=')
            var result = calculate().toString()
            formula.text = result
            nStr = result
            nList.clear()
            oList.clear()
        }
    }

        fun addList(str : String, ope : Char){
            try{
                var num = str.toDouble()
                nList.add(num)
                if(ope != '=') oList.add(ope)
            }catch(e:Exception){
                formula.text = "Numeric error"
            }
        }

        fun calculate() : Double{
            var i = 0
            while(i < oList.size){
                if(oList.get(i)=='*' || oList.get(i)=='/'){
                    var result = if (oList.get(i)=='*') nList.get(i) * nList.get(i+1) else nList.get(i) / nList.get(i+1)
                    nList.set(i, result) // 計算に使った１つ目の要素を計算結果にする
                    nList.removeAt(i+1) // 計算に使った2つめの要素を消す
                    oList.removeAt(i)
                    i-- // リストの次の要素が一つ手前にきたのでiを一つ戻す
                }
                else if (oList.get(i)=='-'){
                    oList.set(i, '+')
                    nList.set(i+1, nList.get(i+1) * -1)
                }
                i++
            }

            var result = 0.0
            for (i in nList){
               result += i
            }

            return result
        }
    }