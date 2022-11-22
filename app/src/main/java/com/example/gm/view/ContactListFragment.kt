package com.example.gm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.findNavController
import androidx.fragment.app.viewModels
import com.example.gm.view.components.ContactList
import com.example.gm.viewmodel.ContatViewModel

class ContactListFragment : Fragment() {

    private val viewModel: ContatViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply{
            setContent {

                val contactL by viewModel.contactl.observeAsState(viewModel.getContact())
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ContactList(
                        navController = findNavController(), viewModel,contactL)
                }

                    }
            }
        }
    }
