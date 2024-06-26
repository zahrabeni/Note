package com.example.note.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import com.example.note.databinding.FragmentHomeBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(), AddTodoPopupFragment.DialogNextBtnClickListeners {
    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var navController: NavController
    private lateinit var Binding :FragmentHomeBinding
    private lateinit var popUpFragment: AddTodoPopupFragment




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Binding = FragmentHomeBinding.inflate(inflater , container,false)
        return Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         init(view)
         registerEvent()
    }
    private fun  registerEvent(){
        Binding.addTaskBtn.setOnClickListener{
            popUpFragment= AddTodoPopupFragment()
            popUpFragment.setlistener(this)
            popUpFragment.show(
                childFragmentManager,"Addtodofragment"
            )

        }
    }
     private  fun init(view : View){
        //navController=navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
         databaseReference = FirebaseDatabase.getInstance().reference.child("Tasks").child(auth.currentUser?.uid.toString())
    }

    override fun onSaveTask(todo: String, TodoEt: TextInputEditText) {
      databaseReference.push().setValue(todo).addOnCompleteListener{
          if(it.isSuccessful){
              Toast.makeText(context,"Todo saved successfully",Toast.LENGTH_SHORT).show()
              //todoEt.text=null
      }
          else{
              Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()
          }
    }

}}