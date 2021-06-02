package com.maani.todolist

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.maani.todolist.Model.MainViewModel
import com.maani.todolist.data.NoteEntity
import com.maani.todolist.databinding.FragmentMainBinding

class MainFragment : Fragment(), NoteListAdapter.ListItemListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewmodel: MainViewModel
    private lateinit var adapter: NoteListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewmodel.notelist?.observe(viewLifecycleOwner, Observer {
            adapter = NoteListAdapter(it, this@MainFragment)
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = LinearLayoutManager(activity)

            var selectedNotes = savedInstanceState?.getParcelableArrayList<NoteEntity>(SLECTED_NOTE)
            adapter.selectedNotes.addAll(selectedNotes?: emptyList())
        })
        with(binding.recyclerview) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
        }
        binding.fbAdd.setOnClickListener{
            goEditFragment(NEW_NOTE_ID)
        }

        requireActivity().title = "Note"
        return binding.root
    }

    override fun goEditFragment(noteId: Int) {
        Log.i(TAG, "Item clicked position is: $noteId")

        val action = MainFragmentDirections.actionToEditor(noteId)
        findNavController().navigate(action)
    }

    override fun onItemSelectionChanged() {
        requireActivity().invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val item =
            if (this::adapter.isInitialized && adapter.selectedNotes.isNotEmpty()){
                R.menu.menu_delete
            }else{
                R.menu.menu_main
            }
        inflater.inflate(item,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_data -> addData()
            R.id.clear_databse -> clearDatabase()
                R.id.delete_data -> deleteData()
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun clearDatabase(): Boolean {
        viewmodel.clearDatabase()
        return true
    }

    private fun deleteData(): Boolean {
        viewmodel.deleteData(adapter.selectedNotes)
        Handler(Looper.getMainLooper()).postDelayed({
            adapter.selectedNotes.clear()
            requireActivity().invalidateOptionsMenu()
        },100)
        return true
    }

    private fun addData() : Boolean{
        viewmodel.addSampleData()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (this::adapter.isInitialized){
            outState.putParcelableArrayList(SLECTED_NOTE,adapter.selectedNotes)
        }
        super.onSaveInstanceState(outState)
    }

}