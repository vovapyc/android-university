package com.example.bnv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fragment_first.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentFirst : Fragment(), View.OnClickListener {
    private var res_num: String? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_fragment_first, container, false)
        val button1 = rootView.findViewById<Button>(R.id.add)
        button1.setOnClickListener(this)
        return rootView
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.add -> {
                val num_view = num_in2.text.toString()
                if (num_view == "") {
                    Toast.makeText(activity, "Помилка вводу!", Toast.LENGTH_SHORT).show()
                } else {
                    val number: Int = Integer.parseInt(num_view)
                    res_num = Integer.toBinaryString(number)
                    result.text = "Результат: " + res_num
                }
            }
        }
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
