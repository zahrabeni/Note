package com.example.note.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.note.databinding.FragmentAddTodoPopupBinding
import com.google.android.material.textfield.TextInputEditText

class AddTodoPopupFragment : DialogFragment() {
    private lateinit var binding: FragmentAddTodoPopupBinding
      private lateinit var listener :DialogNextBtnClickListeners

      fun setlistener(listener: HomeFragment){
          this.listener=listener
      }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentAddTodoPopupBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEvents()
    }
    private fun registerEvents(){
        binding.todoNextBtn.setOnClickListener(){
            var todoTask= binding.todoEt.text.toString()
            if(todoTask.isNotEmpty()){
                listener.onSaveTask(todoTask , binding.todoEt)

            }else{
                Toast.makeText(context,"please type some task",Toast.LENGTH_SHORT).show()
            }
        }
        binding.todoClose.setOnClickListener{
            dismiss()
        }
    }
    interface DialogNextBtnClickListeners{
        fun onSaveTask(todo:String , TodoEt :TextInputEditText)
    }
}