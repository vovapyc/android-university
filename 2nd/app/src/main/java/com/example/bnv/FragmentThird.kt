package com.example.bnv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fragment_third.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentThird : Fragment(), View.OnClickListener {

    private var res_num: String? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_fragment_third, container, false)
        val button1 = rootView.findViewById<Button>(R.id.add_fr3)
        button1.setOnClickListener(this)

        return rootView
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.add_fr3 -> {
                val num_view1 = num_in1.text.toString()
                val num_view2 = num_in2.text.toString()
                if (num_view1 == "" || num_view2 == "" || !check_num_in(num_view1) || !check_num_in(
                                num_view2
                        )
                ) {
                    Toast.makeText(activity, "Помилка вводу!", Toast.LENGTH_SHORT).show()
                } else {
                    val res_num = Integer.toBinaryString(
                            Integer.parseInt(num_view1, 2) + Integer.parseInt(
                                    num_view2,
                                    2
                            )
                    )

                    result.text = "Результат: " + res_num
                }
            }
        }
    }

    fun check_num_in(s: String): Boolean {
        var count: Int = 0
        for (i in s) {
            if (i == '1' || i == '0') {
                count++
            }
        }
        return count == s.length
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("res_num", res_num)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        res_num = savedInstanceState?.getString("res_num")
        if (res_num != null) result.text = "Результат: $res_num"
    }
}
