package com.example.s

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private var stringBuilder: StringBuilder? = null

    private val KEY: String = "displayText"
    private val LO: String = "leftOperand"
    private val RO: String = "rightOperand"
    private val OPERATION: String = "operation"
    private val M: String = "memory"
    private val CHECK: String = "check"

    private var leftOperand: Double = 0.0
    private var rightOperand: Double = 0.0
    private var memory: Double = 0.0
    private var check: Boolean = false

    private enum class Operation {
        plus, minus, div, mul
    }

    private var operation: Operation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stringBuilder = StringBuilder()
        if (savedInstanceState != null) {
            leftOperand = savedInstanceState.getDouble(LO);
            rightOperand = savedInstanceState.getDouble(RO)
            memory = savedInstanceState.getDouble(M)
            operation = savedInstanceState.getSerializable(OPERATION) as Operation?
            stringBuilder?.append(savedInstanceState.getString(KEY))
            textView.text = stringBuilder.toString()
            check = savedInstanceState.getBoolean(CHECK)
        }
    }

    fun append_num(num: Int) {
        check = stringBuilder.toString().startsWith("П")
        if (check) {
            stringBuilder?.delete(0, stringBuilder!!.length)
        }
        stringBuilder?.append(num)
        textView.text = stringBuilder?.toString()
    }

    fun click(view: View) {
        check = stringBuilder.toString().startsWith("П")
        when (view.getId()) {
            R.id.button0 -> append_num(0)
            R.id.button1 -> append_num(1)
            R.id.button2 -> append_num(2)
            R.id.button3 -> append_num(3)
            R.id.button4 -> append_num(4)
            R.id.button5 -> append_num(5)
            R.id.button6 -> append_num(6)
            R.id.button7 -> append_num(7)
            R.id.button8 -> append_num(8)
            R.id.button9 -> append_num(9)
            R.id.point -> {
                if (check) {
                    stringBuilder?.delete(0, stringBuilder!!.length)
                }
                var i: Boolean
                i = stringBuilder!!.toString().contains(".")
                if (!i) {
                    stringBuilder?.append(".")
                    textView.text = stringBuilder?.toString()
                }
            }
            R.id.M_plus -> {
                if (!check && stringBuilder!!.toString().length != 0 && stringBuilder!!.toString() != ".") {
                    memory += stringBuilder!!.toString().toDouble()
                }
            }
            R.id.MC -> {
                memory = 0.0
            }
            R.id.MR -> {
                textView.text = memory.toString()
                sign.text = ""
            }
            R.id.plus_minus -> {
                if (!check && stringBuilder!!.toString().length != 0 && stringBuilder!!.toString() != ".") {
                    if (stringBuilder!!.toString().toDouble() > 0) {
                        textView.text = "-" + stringBuilder!!.toString()
                    }
                    if (stringBuilder!!.toString().toDouble() < 0) {
                        var num: Double
                        num = stringBuilder!!.toString().toDouble()
                        textView.text = abs(num).toString()
                    }
                }
            }
            R.id.clear -> {
                stringBuilder!!.delete(0, stringBuilder!!.length)
                textView.text = stringBuilder?.toString()
                operation = null
                sign.text = " "
            }
            R.id.erase -> {
                if (stringBuilder!!.toString().length != 0) {
                    stringBuilder!!.delete(stringBuilder!!.length - 1, stringBuilder!!.length)
                    textView.text = stringBuilder?.toString()
                }
            }
            R.id.plus -> {
                if (!check && stringBuilder!!.toString().length != 0 && stringBuilder!!.toString() != ".") {
                    leftOperand = stringBuilder!!.toString().toDouble()
                    stringBuilder?.delete(0, stringBuilder!!.length)
                    textView.text = stringBuilder?.toString()
                    operation = Operation.plus
                    sign.text = "+"

                }
            }
            R.id.minus -> {
                if (!check && stringBuilder!!.toString().length != 0 && stringBuilder!!.toString() != ".") {

                    leftOperand = stringBuilder!!.toString().toDouble()
                    stringBuilder?.delete(0, stringBuilder!!.length)
                    textView.text = stringBuilder?.toString()
                    operation = Operation.minus
                    sign.text = "-"
                }
            }
            R.id.mul -> {
                if (!check && stringBuilder!!.toString().length != 0 && stringBuilder!!.toString() != ".") {
                    leftOperand = stringBuilder!!.toString().toDouble()
                    stringBuilder?.delete(0, stringBuilder!!.length)
                    textView.text = stringBuilder?.toString()
                    operation = Operation.mul
                    sign.text = "*"
                }
            }
            R.id.div -> {
                if (!check && stringBuilder!!.toString().length != 0 && stringBuilder!!.toString() != ".") {
                    leftOperand = stringBuilder!!.toString().toDouble()
                    stringBuilder?.delete(0, stringBuilder!!.length)
                    textView.text = stringBuilder?.toString()
                    operation = Operation.div
                    sign.text = "/"
                }
            }
            R.id.equal -> {
                if (!check) {
                    if (stringBuilder!!.toString() == "." || stringBuilder!!.toString() == "") {
                        stringBuilder?.append(0)
                    }
                    rightOperand = stringBuilder!!.toString().toDouble()
                    stringBuilder?.delete(0, stringBuilder!!.length)
                    sign.text = ""

                    when (operation) {
                        Operation.plus -> {
                            leftOperand += rightOperand
                            stringBuilder?.append(leftOperand.toString())
                            textView.text = stringBuilder?.toString()
                        }
                        Operation.minus -> {
                            leftOperand -= rightOperand
                            stringBuilder?.append(leftOperand.toString())
                            textView.text = stringBuilder?.toString()
                        }
                        Operation.mul -> {
                            leftOperand *= rightOperand
                            stringBuilder?.append(leftOperand.toString())
                            textView.text = stringBuilder?.toString()
                        }
                        Operation.div -> {
                            if (rightOperand != 0.0) {
                                leftOperand /= rightOperand
                                stringBuilder?.append(leftOperand.toString())
                            } else {
                                stringBuilder?.append("Помилка вводу")
                            }
                            textView.text = stringBuilder?.toString()
                        }
                        else -> {
                            stringBuilder?.append(rightOperand.toString())
                            textView.text = stringBuilder?.toString()
                        }
                    }
                }
            }
            else -> {
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY, stringBuilder.toString())
        outState.putDouble(LO, leftOperand)
        outState.putDouble(RO, rightOperand)
        outState.putSerializable(OPERATION, operation)
        outState.putDouble(M, memory)
        outState.putBoolean(CHECK, check)
    }
}