package com.example.androidapplicationdevelopment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class MessageFragment : Fragment() {

    interface OnMessageSendListener {
        fun onMessageSend(message: String)
    }

    private var listener: OnMessageSendListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMessageSendListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnMessageSendListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)

        view.findViewById<Button>(R.id.btnSend).setOnClickListener {
            listener?.onMessageSend("Hello from Fragment!")
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
